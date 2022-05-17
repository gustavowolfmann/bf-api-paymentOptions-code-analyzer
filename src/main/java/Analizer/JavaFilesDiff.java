package Analizer;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.utils.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class JavaFilesDiff {

    private static String javaProjectPath = "/Users/gwolfmann/Downloads/fury_buyingflow-payments/domain/src/main/java/com/mercadolibre/payments/domain/payment_options/wrapper/";
    static HashMap<Path, Path> generatedFiles = new HashMap<>();
    static HashMap<Path, Path> projectFiles = new HashMap<>();
    static HashMap<String, String> generatedFields = new HashMap<>();
    static HashMap<String, String> projectFields = new HashMap<>();

    static public void compareJavaDirs(String javaGeneratedPath){

        Path generatedPath = Paths.get(javaGeneratedPath);
        Path projectPath = Paths.get(javaProjectPath);
        try {
            Files.walk(generatedPath, Integer.valueOf(1))
                    .filter(Predicate.not(Files::isDirectory))
                    .forEach(path -> {generatedFiles.put(path.getFileName(),path);});
            Files.walk(projectPath, Integer.valueOf(1))
                    .filter(Predicate.not(Files::isDirectory))
                    .forEach(path -> {projectFiles.put(path.getFileName(),path);});
        } catch (IOException e) {
            e.printStackTrace();
        }
        generatedFiles.keySet().forEach(path -> verifyInProject(path));
        projectFiles.keySet().forEach(path -> verifyInGenerated(path));
    }

    public static void compareFiles(){
        List<Pair<Path,Path>> toScanAttribs = listOfBoth();
        toScanAttribs.forEach(pair ->scanPair(pair));
    }

    private static void verifyInGenerated(Path p){
        if (!(generatedFiles.containsKey(p.getFileName())))
            Logger.addJavaNotFound("No se genero "+p.getFileName());
    }

    private static void verifyInProject(Path p){
        if (!(projectFiles.containsKey(p.getFileName())))
            Logger.addJavaNotFound("No existe en proyecto "+p.getFileName());
    }

    private static List listOfBoth(){
        return generatedFiles.keySet().stream()
                .filter(projectFiles::containsKey)
                .map(p->new Pair(generatedFiles.get(p),projectFiles.get(p.getFileName())))
                .collect(Collectors.toList());
    }

    private static void scanPair(Pair<Path,Path> pair){
        generatedFields.clear();
        projectFields.clear();
        String fileName = pair.a.getFileName().toString();
        System.out.println("Comparando "+fileName);
        try {
            CompilationUnit cuGenerated = StaticJavaParser.parse(pair.a);
            CompilationUnit cuProject = StaticJavaParser.parse(pair.b);
            Optional<ClassOrInterfaceDeclaration> clsGenerated = cuGenerated.findFirst(ClassOrInterfaceDeclaration.class);
//                    .orElseThrow(() -> new NoSuchElementException("Compilation unit doesn't contain a class or interface declaration!"));
            clsGenerated.ifPresent(g -> g.findAll(FieldDeclaration.class).forEach(fd -> generatedFields.put(fd.getVariable(0).getNameAsString(),
                    fd.getVariable(0).getType().toString())));
            Optional<ClassOrInterfaceDeclaration> clsProject = cuProject.findFirst(ClassOrInterfaceDeclaration.class);
                    //.orElseThrow(() -> new NoSuchElementException("Compilation unit doesn't contain a class or interface declaration!"));
            clsProject.ifPresent(p->p.findAll(FieldDeclaration.class).forEach(fd -> projectFields.put(fd.getVariable(0).getNameAsString(),
                    fd.getVariable(0).getType().toString())));

        } catch (IOException e) {
            e.printStackTrace();
        }
        generatedFields.keySet().forEach(varname -> verifyInProFile(varname, fileName));
        projectFields.keySet().forEach(varname -> verifyInGenFile(varname, fileName));
    }

    private static void verifyInProFile(String varName, String fileName){
        if (!(projectFields.containsKey(varName))) {
            Logger.addJavaNotFound("No existe en proyecto el Field " + varName + " en " + fileName);
        } else {
            if (!(projectFields.get(varName).equals(generatedFields.get(varName)))) {
                Logger.addJavaNotFound(varName+" es de tipo "+projectFields.get(varName)+
                        " en proyecto y de tipo "+generatedFields.get(varName)+" en generado de "+fileName);
            }
        }
    }

    private static void verifyInGenFile(String varName, String fileName){
        if (!(generatedFields.containsKey(varName))) {
            Logger.addJavaNotFound("No existe en proyecto el Field " + varName + " en " + fileName);
        } else {
            if (!(generatedFields.get(varName).equals(projectFields.get(varName)))) {
                Logger.addJavaNotFound(varName+" es de tipo "+generatedFields.get(varName)+
                        " en generado y de tipo "+projectFields.get(varName)+" en proyecto de " + fileName);
            }
        }
    }

}
