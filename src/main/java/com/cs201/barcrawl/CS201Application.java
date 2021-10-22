package com.cs201.barcrawl;

import com.cs201.barcrawl.models.Business;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;

//@SpringBootApplication
public class CS201Application {

    public static void main(String[] args) {

//        SpringApplication.run(CS201Application.class, args);

        BufferedReader br = null;
        int count = 0;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader("/Users/Alpha/Documents/GitHub/CS201/src/main/resources/original_business.json"));

            ObjectMapper objectMapper = new ObjectMapper();
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("target/result.json", true)));

            while ((sCurrentLine = br.readLine()) != null) {
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                Business business = objectMapper.readValue(sCurrentLine, Business.class);

                if(business.getCategories() == null) {
                    continue;
                }

                if (business.getCategories().contains("Bar")) {
//                    objectMapper.writeValue(new File("target/result.json"), business);
//                    objectMapper.writeValue(out, business);
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
