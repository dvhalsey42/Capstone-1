package com.techelevator;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Purchase extends Import{
    private double totalMoney;

    private Log log = new Log();

    public double getTotalMoney() {return totalMoney;}
    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }
    public void addMoney(double amountToAdd){
        setTotalMoney(getTotalMoney() + amountToAdd);
    }

    public void purchaseMenu(Map<String, List<String>> vendingCategories, Map<String, Integer> vendingStock) {


        while (true) {
            System.out.println("(1) Feed Money");
            System.out.println("(2) Select Product");
            System.out.println("(3) Finish Transaction");
            System.out.println("\n" + "Current Money Provided: $" + getTotalMoney());

            Scanner input = new Scanner(System.in);

            String inputString = input.nextLine();

            if (inputString.equals("1")) {
                System.out.println("Please enter dollar value of bill to feed into vending machine");
                String inputString2 = input.nextLine();

                addMoney(Double.valueOf(inputString2));
                log.logMessage(inputString2 + " " + String.valueOf(totalMoney));

            } else if (inputString.equals("2")) {



                for(Map.Entry<String, List<String>> item:vendingCategories.entrySet()){
                    if(item.getKey().contains("*")){
                        System.out.println("SOLD OUT: " + item.getValue().get(0));
                    }
                    else {
                        System.out.println(item.getKey() + ": " + item.getValue().get(0) +
                                " " + item.getValue().get(1));
                    }
                }

                System.out.println("Please select your item");

                String selection = input.nextLine();
                for (Map.Entry<String, List<String>> product : vendingCategories.entrySet()) {
                    if (selection.equals(product.getKey()) && selection.contains("A")) {

                        Chip buyChip = new Chip(totalMoney);
                        totalMoney = buyChip.buyProduct(product.getValue().get(0),
                                Double.parseDouble(product.getValue().get(1)));

                    }
                    else if(selection.equals(product.getKey()) && selection.contains("B")) {

                        Candy buyCandy = new Candy(totalMoney);
                        totalMoney = buyCandy.buyProduct(product.getValue().get(0),
                                Double.parseDouble(product.getValue().get(1)));

                    }
                    else if(selection.equals(product.getKey()) && selection.contains("C")) {

                        Drink buyDrink = new Drink(totalMoney);
                        totalMoney = buyDrink.buyProduct(product.getValue().get(0),
                                Double.parseDouble(product.getValue().get(1)));

                    }
                    else if(selection.equals(product.getKey()) && selection.contains("D")) {

                        Gum buyGum = new Gum(totalMoney);
                        totalMoney = buyGum.buyProduct(product.getValue().get(0),
                                Double.parseDouble(product.getValue().get(1)));

                    }
                }

                log.logMessage(vendingCategories.get(selection).get(0) + " " + selection + " " +
                        String.valueOf(Double.parseDouble(vendingCategories.get(selection).get(1)) + totalMoney) +
                        " " + String.valueOf(totalMoney));

                for(Map.Entry<String, Integer> inventory:vendingStock.entrySet()){
                    if(inventory.getKey().equals(selection)){
                        inventory.setValue(inventory.getValue() - 1);
                    }
                }

                if(vendingStock.get(selection)==0) {
                    vendingCategories.put("*" + selection, vendingCategories.get(selection));
                    vendingCategories.remove(selection);
                }

            }

            else if (inputString.equals("3")) {
                returnMoney();
                break;
            }

        }
    }

    public void returnMoney(){
        final double quarter = .25;
        final double dime = .1;
        final double nickle = .05;

        int quarters = (int)(totalMoney / quarter);
        totalMoney -= quarters * quarter;

        int dimes = (int)(totalMoney / dime);
        totalMoney -= dimes * dime;

        int nickles = (int)(totalMoney / nickle);
        totalMoney = 0.0;

        System.out.println("Dispensing " + quarters + " Quarters");
        System.out.println("Dispensing " + dimes + " Dimes");
        System.out.println("Dispensing " + nickles + " Nickles");
    }

}