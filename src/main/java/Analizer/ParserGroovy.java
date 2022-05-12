package Analizer;


import java.io.IOException;

import java.nio.charset.StandardCharsets;

import lombok.Data;
import lombok.Singular;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.groovy.ast.builder.AstBuilder;
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
        ClassToParse classToParse = filesToProcess.getNext();
        ParsedClass parsedClass = parsing(classToParse.getPath()+classToParse.getName());
        parsedClass.generateJavaSource();
        filesToProcess.markDone(classToParse);
        System.out.println("Codigo generado para "+parsedClass.getName());

        List<String> toCall = parsedClass.getNoNativeAttribs();
        filesToProcess.addFiles(toCall);
        /*
        System.out.println("toCall de "+parsedClass.getName()+" tiene "+ String.valueOf(toCall.size())+" no nativos");
        toCall.stream().forEach(s -> {System.out.println(s);});
         */
    }

    public ParsedClass parsing(String fileName){
        System.out.println("Parsing file "+fileName);
        ParsedClass parsedClass = new ParsedClass();
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
                }
            }
            parsedClass.setOutputPath("/Users/gwolfmann/Downloads/groovy-sintactic-analizer/src/generated/");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return parsedClass;
    }

}
