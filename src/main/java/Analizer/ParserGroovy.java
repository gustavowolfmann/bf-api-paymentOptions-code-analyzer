package Analizer;


import java.io.IOException;

import java.nio.charset.StandardCharsets;

import com.github.javaparser.utils.Pair;
import groovyjarjarantlr.collections.impl.LList;
import lombok.Data;
import lombok.Singular;
import org.codehaus.groovy.ast.*;
import org.codehaus.groovy.ast.builder.AstBuilder;
import org.codehaus.groovy.ast.expr.AnnotationConstantExpression;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.expr.ListExpression;
import org.codehaus.groovy.control.CompilePhase;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Data
public class ParserGroovy {

    private FileProcessor filesToProcess = new FileProcessor();

    public void initialize(){
        filesToProcess.initialize();
    }

    public void doParse(){
        Optional<ClassToParse> classToParse = filesToProcess.getNext();
        while (classToParse.isPresent()) {
            Pair<ParsedClass, List<String>> parsedClassResult = parsing(classToParse.get().getPath()+classToParse.get().getName());
            ParsedClass parsedClass = parsedClassResult.a;
            parsedClass.generateJavaSource();
            filesToProcess.markDone(classToParse.get());
            Logger.addProcessed("Codigo generado para "+parsedClass.getName());
            List<String> toCall = parsedClass.getNoNativeAttribs();
            toCall.addAll(parsedClassResult.b);  // add subtypes if exists
            filesToProcess.addFiles(toCall);
            classToParse = filesToProcess.getNext();
        }
    }

    public Pair<ParsedClass,List<String>> parsing(String fileName){
        System.out.println("Parsing file "+fileName);
        ParsedClass parsedClass = new ParsedClass();
        List<String> subTypes = new ArrayList<>();
        try {
            final String sourceContents = Files.readString(Path.of(fileName), StandardCharsets.UTF_8);
            final AstBuilder astBuilder = new AstBuilder();
            final List<ASTNode> nodes = astBuilder.buildFromString(CompilePhase.CONVERSION,true,sourceContents);

            for (final ASTNode node : nodes) {
                if (node instanceof ClassNode) {
                    String name = ((ClassNode) node).getNameWithoutPackage();
                    if (!(name.toLowerCase(Locale.ROOT).contains("builder"))) {
                        parsedClass.setAnnotation(Optional.of("Data"));
                        parsedClass.setName(name);
                        if (((ClassNode) node).isPrimaryClassNode()){
                            List<FieldNode> fields = ((ClassNode) node).getFields();
                            for (final FieldNode f : fields) {
                                Attrib attrib = new Attrib();
                                attrib.setName(f.getName());
                                attrib.setType(f.getType().getName());
                                if (null != f.getType().getGenericsTypes()) {
                                    attrib.setGenericType(Optional.of(f.getType().getGenericsTypes()[0].getType().getName()));
                                }
                                parsedClass.getAttribs().add(attrib);
                            }
                        }
                        if (((ClassNode) node).isInterface()) {
                            List<MethodNode> methods = ((ClassNode) node).getMethods();
                            for (final MethodNode m : methods) {
                                Attrib attrib = new Attrib();
                                attrib.setName(m.getName().replace("get",""));
                                attrib.setType(m.getReturnType().getName());
                                if (null != m.getReturnType().getGenericsTypes()) {
                                    attrib.setGenericType(Optional.of(m.getReturnType().getGenericsTypes()[0].getType().getName()));
                                }
                                parsedClass.getAttribs().add(attrib);
                            }

                            if (((ClassNode) node).getAnnotations().size() > 0) {  // has subtypes
                                List<Expression> expressionList =  ((ListExpression)((ClassNode) node).getAnnotations().get(1).getMembers().get("value")).getExpressions();
                                for(Expression e :expressionList) {
                                    subTypes.add(((AnnotationNode)((ConstantExpression) e).getValue()).getMembers().get("value").getText());
                                }
                                //String subtype = ((AnnotationNode)((ConstantExpression) ((ListExpression)((ClassNode) node).getAnnotations().get(1).getMembers().get("value")).getExpressions().get(0)).getValue()).getMembers().get("value").getText();
                                //ConstantExpression expressions = ((ConstantExpression) ((ListExpression)((ClassNode) node).getAnnotations().get(1).getMembers().get("value")).getExpressions().get(0));
                                //        .getValue());
                            }
                        }
                    }
                }
            }
            parsedClass.setOutputPath("/Users/gwolfmann/Downloads/groovy-sintactic-analizer/src/generated/");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Pair<>(parsedClass, subTypes);
    }

}
