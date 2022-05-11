package Analizer;


import lombok.Data;
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
        fileNames.stream().forEach(s -> addFile(s));
    }

    private void addFile(String fileName){
        if (!(toParseDict.containsKey(fileName))) {
            toParseDict.put(fileName,Boolean.TRUE);
            toParseList.add(new ClassToParse(fileName+".groovy", "", Boolean.FALSE));
        }
    }

    public void markDone(ClassToParse classToParse){
        toParseList.remove(classToParse);
        parsedList.add(classToParse);
    }

}
