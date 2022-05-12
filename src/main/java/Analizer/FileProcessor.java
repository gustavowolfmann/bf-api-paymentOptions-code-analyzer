package Analizer;


import lombok.Data;

import java.nio.file.Path;
import java.util.*;

@Data
public class FileProcessor {

    private Map<String,Boolean> toParseDict = new HashMap<String, Boolean>();
    private List<ClassToParse> toParseList = new ArrayList<ClassToParse>();
    private List<ClassToParse> parsedList = new ArrayList<ClassToParse>();

    public void initialize(){
        toParseList.add(new ClassToParse("PaymentOptionsResponse.groovy",
                "/Users/gwolfmann/Downloads/groovy-sintactic-analizer/src/main/java/Analizer/",
                Boolean.FALSE));
        toParseDict.put("PaymentOptionsResponse",Boolean.TRUE);
    }

    public ClassToParse getNext(){
        return toParseList.get(0);
        //parsing(classToParse.getPath()+classToParse.getName());
    }

    public void addFiles(List<String> fileNames){
        fileNames.stream().forEach(this::addFile);
    }

    private void addFile(String fileName) {
        if (!(toParseDict.containsKey(fileName))) {
            Optional<Path> filePath = findPath(fileName + ".groovy");
            if (filePath.isPresent()) {
                updateFilesToProcess(fileName, filePath.get());
            } else {
                System.out.printf(fileName + " no encontrado");
            }
        }
    }
    private void updateFilesToProcess(String fileName, Path p) {
        String pathOfFile = p.toString()+"/";
        toParseDict.put(fileName,Boolean.TRUE);
        toParseList.add(new ClassToParse(fileName+".groovy", pathOfFile, Boolean.FALSE));
        System.out.println("se agrego en el dict "+fileName+ " con el path " + pathOfFile);
    }

    private Optional<Path> findPath(String fileName) {

     return FilesNavigation.findFileInPath(fileName ,"/Users/gwolfmann/Downloads/buyingflow-api/target/work/plugins/buyingflow-commons-1.317.0/src/groovy/buyingflow/dto/payment")
        .map(Path::getParent);
     /*
            .map(f -> fileName+" esta en "+String.valueOf(f)+"/")
            .orElse(fileName+" no esta"));
        return "";

      */
    }


    public void markDone(ClassToParse classToParse){
        toParseList.remove(classToParse);
        parsedList.add(classToParse);
    }

}
