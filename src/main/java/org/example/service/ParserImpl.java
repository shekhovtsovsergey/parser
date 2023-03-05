package org.example.service;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.example.model.ParsingObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


public class ParserImpl implements Parser{


    @Override
    public void jsonParser(File file){
        try (JsonParser jParser = new JsonFactory().createParser(file)) {
            if (jParser.nextToken() == JsonToken.START_ARRAY) {
              while (jParser.nextToken() != JsonToken.END_ARRAY) {
                    ParsingObject object = new ParsingObject();
                    while (jParser.nextToken() != JsonToken.END_OBJECT) {

                        String fieldname = jParser.getCurrentName();
                        if ("group".equals(fieldname)) {
                            jParser.nextToken();
                            object.setGroup(jParser.getText());
                        }
                        if ("type".equals(fieldname)) {
                            jParser.nextToken();
                            object.setType(jParser.getText());
                        }
                        if ("number".equals(fieldname)) {
                            jParser.nextToken();
                            object.setNumber(jParser.getLongValue());
                        }
                        if ("weight".equals(fieldname)) {
                            jParser.nextToken();
                            object.setWeight(BigInteger.valueOf(jParser.getLongValue()));
                        }
                    }
                    System.out.println("----------------");
                    System.out.println(object);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
      }
  }


    @Override
    public void csvParser(File file) {

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(file.getPath()) , StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splited = line.split(",");
                if (splited[0].equals("group")){
                    continue;
                }
                String group = splited[0];
                String type = splited[1];
                long number = Long.parseLong(splited[2]);
                BigInteger weight = BigInteger.valueOf(Long.parseLong(splited[3]));

                System.out.println("----------------");
                System.out.println(group);
                System.out.println(type);
                System.out.println(number);
                System.out.println(weight);


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
