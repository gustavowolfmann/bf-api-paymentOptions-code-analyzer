package Analizer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.function.Predicate;

public class JavaFilesDiff {

    private static String javaProjectPath = "/Users/gwolfmann/Downloads/fury_buyingflow-payments/domain/src/main/java/com/mercadolibre/payments/domain/payment_options/wrapper/";
    static HashMap<Path, Path> generatedFiles = new HashMap<>();
    static HashMap<Path, Path> projectFiles = new HashMap<>();

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

    static void verifyInGenerated(Path p){
        if (!(generatedFiles.containsKey(p.getFileName())))
            Logger.addJavaNotFound("No se genero "+p.getFileName());
    }

    private static void verifyInProject(Path p){
        if (!(projectFiles.containsKey(p.getFileName())))
            Logger.addJavaNotFound("No existe en proyecto "+p.getFileName());
    }

}
