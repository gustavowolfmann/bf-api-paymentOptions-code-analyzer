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
"/Users/gwolfmann/Downloads/buyingflow-api/target/work/plugins/buyingflow-commons-1.324.0/src/groovy/buyingflow/dto/response/",
//                "/Users/gwolfmann/Downloads/groovy-sintactic-analizer/src/main/java/Analizer/",
                Boolean.FALSE));
        toParseDict.put("PaymentOptionsResponse",Boolean.TRUE);
    }

    public Optional<ClassToParse> getNext(){
        Optional<ClassToParse> result = Optional.empty();
        if (toParseList.size()>0)
            result = Optional.of(toParseList.get(0));
        return result;
    }

    public void addFiles(List<String> fileNames, String fromSource){
        fileNames.stream().forEach(s -> addFile(s,fromSource));
    }

    private void addFile(String fileName, String fromSource) {
        if (!(toParseDict.containsKey(fileName))) {  // to avoid process more than once
            Optional<Path> filePath = findPath(fileName + ".groovy");
            if (filePath.isPresent()) {
                updateFilesToProcess(fileName, fromSource, filePath.get());
            } else {
                Logger.addNotFound(fileName + " no encontrado");
            }
        }
    }
    private void updateFilesToProcess(String fileName, String fromSource, Path p) {
        String pathOfFile = p.toString()+"/";
        toParseDict.put(fileName,Boolean.TRUE);
        toParseList.add(new ClassToParse(fileName+".groovy", pathOfFile, Boolean.FALSE));
        Logger.addToProcess("se agrego en el dict "+fileName+ " desde "+fromSource+ " con el path " + pathOfFile);
    }

    private Optional<Path> findPath(String fileName) {

     return FilesNavigation.findFileInPath(fileName ,"/Users/gwolfmann/Downloads/buyingflow-api/target/work/plugins/buyingflow-commons-1.324.0/src/groovy/buyingflow/")
        .map(Path::getParent);
    }


    public void markDone(ClassToParse classToParse){
        toParseList.remove(classToParse);
        parsedList.add(classToParse);
    }

}
