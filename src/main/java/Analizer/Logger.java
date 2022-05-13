package Analizer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Logger {

    private static List<String> toProcess = new ArrayList<>();
    private static List<String> processed = new ArrayList<>();
    private static List<String> notFound = new ArrayList<>();

    public static void addToProcess(String msg){
        toProcess.add(msg);
    }

    public static void addProcessed(String msg){
        processed.add(msg);
    }

    public static void addNotFound(String msg){
        notFound.add(msg);
    }

    public static void saveLogs(){
        try {
            PrintWriter fileWriter = new PrintWriter(new FileWriter("./logAnalyzer.txt"));
            fileWriter.println("----- Candidate Classes -----");
            toProcess.stream().sorted().forEach(fileWriter::println);
            fileWriter.println("");
            fileWriter.println("----- Processed Classes -----");
            processed.stream().sorted().forEach(fileWriter::println);
            fileWriter.println("");
            fileWriter.println("----- Not Found Classes -----");
            notFound.stream().sorted().forEach(fileWriter::println);
            fileWriter.println("----- End of log -----");
            fileWriter.close();
            System.out.println("Log Successfully wrote.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


}
