package Analizer;


import java.io.IOException;

import java.nio.charset.StandardCharsets;

import lombok.Data;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.groovy.ast.builder.AstBuilder;
import org.codehaus.groovy.control.CompilePhase;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Data
public class ParserGroovy {
    public static void parsing(String fileName){
        System.out.println("Parsing file "+fileName);
        try {
            final String sourceContents = Files.readString(Path.of(fileName), StandardCharsets.UTF_8);
            final AstBuilder astBuilder = new AstBuilder();
            final List<ASTNode> nodes = astBuilder.buildFromString(CompilePhase.CONVERSION,true,sourceContents);
            ParsedClass parsedClass = new ParsedClass();

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
            parsedClass.generateJavaSource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
