import Analizer.ParserGroovy;

public class Main {

    static ParserGroovy parser = new ParserGroovy();
    public static void main(String[] args) {
        parser.initialize();
        parser.doParse();
    }
}
