import Analizer.JavaFilesDiff;
import Analizer.Logger;
import Analizer.ParserGroovy;

public class Main {

    static ParserGroovy parser = new ParserGroovy();
    public static void main(String[] args) {
        parser.initialize();
        parser.doParse();
        JavaFilesDiff.compareJavaDirs("/Users/gwolfmann/Downloads/groovy-sintactic-analizer/src/generated/");
        Logger.saveLogs();
        System.out.println("Processing completed");
    }
}
