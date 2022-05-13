package Analizer;


import lombok.Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class FilesNavigation {

    public static List<Path> listFiles(String initialPath){
        Path start = Paths.get(initialPath);
        List<Path> pathList = new ArrayList<>();
        try (Stream<Path> stream = Files.walk(start, Integer.MAX_VALUE)) {
            pathList = stream
                    .filter(Predicate.not(Files::isDirectory))
                    .collect(Collectors.toList());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return pathList;
    }

    public static Optional<Path> findFileInPath(String fileName, String rootPath){
        List<Path> pathList = listFiles(rootPath);
        return pathList.stream()
                .filter(p -> p.getFileName().toString().equalsIgnoreCase(fileName))
                .findFirst();
    }

}
