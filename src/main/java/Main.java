import Analizer.FilesNavigation;
import Analizer.ParserGroovy;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Function;

public class Main {

    static ParserGroovy parser = new ParserGroovy();
    public static void main(String[] args) {
        parser.initialize();
        parser.doParse();
    }
}
