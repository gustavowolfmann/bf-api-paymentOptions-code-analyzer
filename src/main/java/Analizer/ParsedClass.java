package Analizer;


import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.printer.DefaultPrettyPrinter;
import com.github.javaparser.printer.configuration.*;
import lombok.Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class ParsedClass {

    private String name;
    private List<Attrib> attribs = new ArrayList<>();
    private String outputPath;
    private Optional<String> annotation = Optional.empty() ;
    private Boolean isEnum = Boolean.FALSE;

    public void generateJavaSource(){
        CompilationUnit compilationUnit = new CompilationUnit();
        if (this.getIsEnum()) {
            EnumDeclaration enumDeclaration = compilationUnit.addEnum(name);
            enumDeclaration.setName(name);
            enumDeclaration.setPublic(true);
            for (final Attrib attrib : attribs) {
                enumDeclaration.addEnumConstant(attrib.getJavaName());
            }
        } else {
            ClassOrInterfaceDeclaration javaClass = compilationUnit.addClass(name);
            javaClass.addAnnotation(getJavaAnnotation());
            javaClass.setName(name);
            javaClass.setPublic(true);
            for (final Attrib attrib : attribs) {
                javaClass.addPrivateField(attrib.getJavaType(), attrib.getJavaName());
            }
        }
        compilationUnit.setPackageDeclaration("com.mercadolibre.payments.domain.payment_options.wrapper");
        compilationUnit.addImport("lombok.Data");
        DefaultPrinterConfiguration conf = new DefaultPrinterConfiguration();
        conf.addOption(new DefaultConfigurationOption(DefaultPrinterConfiguration.ConfigOption.INDENTATION, new Indentation(Indentation.IndentType.SPACES, 4)));
        DefaultPrettyPrinter defaultPrettyPrinter = new DefaultPrettyPrinter(conf);
        try {
            Files.writeString(Paths.get(getOutputFileName()), defaultPrettyPrinter.print(compilationUnit) , StandardOpenOption.CREATE, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getOutputFileName(){
        return outputPath+name+".java";
    }

    public String getJavaAnnotation() {
        return annotation.isPresent() ? annotation.get() : "";
    }

    public List<String> getNoNativeAttribs(){
        List <String> result = new ArrayList<>();
        for(final Attrib attrib: attribs){
            if (!(attrib.isNative()))
                result.add(attrib.getNameOfClass());
        }
        return result;
    }
}
