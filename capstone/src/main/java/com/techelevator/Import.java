package com.techelevator;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Import {

    String filePath = "C:\\Users\\dvhal\\Desktop\\meritamerica\\repos\\module-1-capstone\\capstone\\vendingmachine.csv";

    File inputFile = new File(filePath);
    Map<String, List<String>> vendingCategories = new HashMap<>();


    public Map<String, List<String>> stockVendingMachine() {
        try (Scanner fileInput = new Scanner(inputFile)) {
            while (fileInput.hasNextLine()) {

                String lineOfText = fileInput.nextLine();

                String[] inputItems = lineOfText.split("\\|");


                List<String> vendingItems = new ArrayList<>();


            for (int i = 1; i < 4; i++) {
                    vendingItems.add(inputItems[i]);
                }

                vendingCategories.put(inputItems[0], vendingItems);

            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e.getMessage());
        }

        return vendingCategories;
    }
}
