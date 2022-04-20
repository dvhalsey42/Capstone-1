package com.techelevator;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Purchase extends Import{
    private double totalMoney;
    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);

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
            System.out.println("\n" + "Current Money Provided: " + currencyFormat.format(getTotalMoney()));

            Scanner input = new Scanner(System.in);

            String inputString = input.nextLine();

            //Allows user to enter dollar bill value and adds amount to
            //total available money in vending machine; logs input.
            if (inputString.equals("1")) {
                System.out.println("Please enter dollar value of bill to feed into vending machine");

                String inputString2 = input.nextLine();
                try {
                    double billValue = Double.valueOf(inputString2);

                    if (billValue == 1 || billValue == 2 || billValue == 5 || billValue == 10
                            || billValue == 20 || billValue == 50 || billValue == 100) {
                        addMoney(billValue);
                        log.logMessage("Feed Money: " + currencyFormat.format(billValue) + " " +
                                currencyFormat.format(totalMoney));
                    } else {
                        System.out.println("Please enter a valid dollar bill amount");
                        continue;
                    }
                } catch (Exception e){
                    System.out.println("Invalid Input");
                }
                //Allows user to select items, "dispenses" item to user and updates
                //inventory of item; logs activity.
            } else if (inputString.equals("2")) {
                boolean purchased = false;

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

                double moneyBefore = totalMoney;

                for (Map.Entry<String, List<String>> product : vendingCategories.entrySet()) {

                    if (selection.equals(product.getKey()) && selection.contains("A")) {

                        Chip buyChip = new Chip(totalMoney);
                        totalMoney = buyChip.buyProduct(product.getValue().get(0),
                                Double.parseDouble(product.getValue().get(1)));
                        purchased = true;

                    }
                    else if(selection.equals(product.getKey()) && selection.contains("B")) {

                        Candy buyCandy = new Candy(totalMoney);
                        totalMoney = buyCandy.buyProduct(product.getValue().get(0),
                                Double.parseDouble(product.getValue().get(1)));
                        purchased = true;


                    }
                    else if(selection.equals(product.getKey()) && selection.contains("C")) {

                        Drink buyDrink = new Drink(totalMoney);
                        totalMoney = buyDrink.buyProduct(product.getValue().get(0),
                                Double.parseDouble(product.getValue().get(1)));
                        purchased = true;


                    }
                    else if(selection.equals(product.getKey()) && selection.contains("D")) {

                        Gum buyGum = new Gum(totalMoney);
                        totalMoney = buyGum.buyProduct(product.getValue().get(0),
                                Double.parseDouble(product.getValue().get(1)));
                        purchased = true;

                    }
                }
                if(!purchased){
                    System.out.println("Invalid Selection");
                    continue;
                }

                if(totalMoney == moneyBefore && purchased){
                    log.logMessage("Not enough money");
                    continue;
                }
                if(purchased) {
                    log.logMessage(vendingCategories.get(selection).get(0) + " " + selection + " " +
                            currencyFormat.format(Double.parseDouble(vendingCategories.get(selection).get(1))) +
                            " " + currencyFormat.format(totalMoney));

                    for (Map.Entry<String, Integer> inventory : vendingStock.entrySet()) {
                        if (inventory.getKey().equals(selection)) {
                            inventory.setValue(inventory.getValue() - 1);
                        }
                    }

                    if (vendingStock.get(selection) == 0) {
                        vendingCategories.put("*" + selection, vendingCategories.get(selection));
                        vendingCategories.remove(selection);
                    }
                }
            }

            //returns user to main menu and dispenses money.
            else if (inputString.equals("3")) {
                returnMoney();
                break;
            }
            else{
                System.out.println("Invalid Input");
                continue;
            }
        }
    }
    //Divides remaining money into the largest change possible and
    //displays results to user, sets totalMoney to 0
    public void returnMoney(){
        final double QUARTER = .25;
        final double DIME = .1;
        final double NICKLE = .05;

        log.logMessage("Give Change: " + currencyFormat.format(totalMoney) + " $0.00");

        int quarters = (int)(totalMoney / QUARTER);
        totalMoney -= quarters * QUARTER;

        int dimes = (int)(totalMoney / DIME);
        totalMoney -= dimes * DIME;

        int nickles = (int)(totalMoney / NICKLE);
        totalMoney = 0.0;

        System.out.println("Dispensing " + quarters + " Quarters");
        System.out.println("Dispensing " + (dimes) + " Dimes");
        System.out.println("Dispensing " + (nickles) + " Nickles");
    }

}
