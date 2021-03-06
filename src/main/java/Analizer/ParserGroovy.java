package Analizer;


import java.io.IOException;

import java.nio.charset.StandardCharsets;

import com.github.javaparser.utils.Pair;
import lombok.Data;
import org.codehaus.groovy.ast.*;
import org.codehaus.groovy.ast.builder.AstBuilder;
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
            filesToProcess.addFiles(toCall,classToParse.get().getName());
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
                            parsedClass.setIsEnum(((ClassNode) node).isEnum());
                            List<FieldNode> fields = ((ClassNode) node).getFields();
                            for (final FieldNode f : fields) {
                                if (!(isEnumInternal(f.getName()))){
                                    Attrib attrib = new Attrib();
                                    attrib.setName(f.getName());
                                    attrib.setType(f.getType().getName());
                                    if (null != f.getType().getGenericsTypes()) {
                                        attrib.setFirstGenericType(Optional.of(f.getType().getGenericsTypes()[0].getType().getName()));
                                        if (f.getType().getName().equalsIgnoreCase("map")){
                                            attrib.setSecondGenericType(Optional.of(f.getType().getGenericsTypes()[1].getType().getName()));
                                        }
                                    }
                                    parsedClass.getAttribs().add(attrib);
                                }
                            }
                        }
                        if (((ClassNode) node).isInterface()) {
                            List<MethodNode> methods = ((ClassNode) node).getMethods();
                            for (final MethodNode m : methods) {
                                Attrib attrib = new Attrib();
                                attrib.setName(m.getName().replace("get",""));
                                attrib.setType(m.getReturnType().getName());
                                if (null != m.getReturnType().getGenericsTypes()) {
                                    attrib.setFirstGenericType(Optional.of(m.getReturnType().getGenericsTypes()[0].getType().getName()));
                                    if (m.getReturnType().getName().equalsIgnoreCase("map")){
                                        attrib.setSecondGenericType(Optional.of(m.getReturnType().getGenericsTypes()[1].getType().getName()));
                                    }
                                }
                                parsedClass.getAttribs().add(attrib);
                            }

                            if (((ClassNode) node).getAnnotations().size() > 0) {  // has subtypes
                                //String subtype = ((AnnotationNode)((ConstantExpression) ((ListExpression)((ClassNode) node).getAnnotations().get(1).getMembers().get("value")).getExpressions().get(0)).getValue()).getMembers().get("value").getText();
                                List<Expression> expressionList =  ((ListExpression)((ClassNode) node).getAnnotations().get(1).getMembers().get("value")).getExpressions();
                                for(Expression e :expressionList) {
                                    subTypes.add(((AnnotationNode)((ConstantExpression) e).getValue()).getMembers().get("value").getText());
                                }
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

    private boolean isEnumInternal(String fieldName){
        switch (fieldName.toLowerCase(Locale.ROOT)) {
            case "$values" :
            case "max_value":
            case "min_value": {return Boolean.TRUE;}
            default: {return Boolean.FALSE;}
        }
    }

}
