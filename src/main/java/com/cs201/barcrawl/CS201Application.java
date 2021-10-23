package com.cs201.barcrawl;

import com.cs201.barcrawl.models.BusinessJsonObject;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;

@SpringBootApplication
public class CS201Application {

    public static void main(String[] args) {
        prepareBusinessJson();
        SpringApplication.run(CS201Application.class, args);

    }

    public static void prepareBusinessJson() {
        BufferedReader br;
        int count = 0;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader("src/main/resources/original_business.json"));

            ObjectMapper objectMapper = new ObjectMapper();
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("target/result.json", true)));

            while ((sCurrentLine = br.readLine()) != null) {
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                BusinessJsonObject businessJsonObject = objectMapper.readValue(sCurrentLine, BusinessJsonObject.class);

                if(businessJsonObject.getCategories() == null) {
                    continue;
                }

                if (businessJsonObject.getCategories().contains("Bar")) {
                    out.write(sCurrentLine);
                    out.write("\n");
                    count += 1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(count);
    }
}
