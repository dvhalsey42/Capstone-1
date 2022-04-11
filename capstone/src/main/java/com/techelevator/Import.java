package com.techelevator;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Import {

    private String filePath = "capstone/vendingmachine.csv";

    private File inputFile = new File(filePath);

    private Map<String, List<String>> vendingCategories = new HashMap<>();

    public Map<String, List<String>> getVendingCategories() {
        return vendingCategories;
    }

    public void setVendingCategories(Map<String, List<String>> vendingCategories) {
        this.vendingCategories = vendingCategories;
    }

    public void stockVendingMachine() {
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

    }


}
