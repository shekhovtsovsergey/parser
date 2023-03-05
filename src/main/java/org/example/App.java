package org.example;

import org.example.service.Parser;
import org.example.service.ParserImpl;

import java.io.File;

public class App {

    public static void main( String[] args ) {

        File file = new File("src/main/resources/json/out.json");
        Parser parser = new ParserImpl();
        parser.jsonParser(file);

        //File file = new File("src/main/resources/csv/out.csv");
        //Parser parser = new ParserImpl();
        //parser.csvParser(file);

    }
}
