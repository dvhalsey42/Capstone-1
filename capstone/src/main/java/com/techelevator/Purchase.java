package com.techelevator;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Purchase extends Import{
    private double totalMoney;

    public double getTotalMoney() {return totalMoney;}
    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }
    public void addMoney(double amountToAdd){
        setTotalMoney(getTotalMoney() + amountToAdd);
    }

    public void purchaseMenu() {
        stockVendingMachine();
        populateVendingStocked();

        while (true) {
            System.out.println("(1) Feed Money");
            System.out.println("(2) Select Product");
            System.out.println("(3) Finish Transaction");
            System.out.println("\n" + "Current Money Provided: $" + getTotalMoney());

            Scanner input = new Scanner(System.in);

            String inputString = input.nextLine();

            if (inputString.equals("1")) {
                System.out.println("Please enter dollar value of bill to feed into vending machine");
                addMoney(Double.valueOf(input.nextLine()));

            } else if (inputString.equals("2")) {

                Map<String, List<String>> vendingItems = getVendingStocked();
                for(Map.Entry<String, List<String>> item:vendingItems.entrySet()){
                    System.out.println(item.getKey() + ": " + item.getValue().get(0) +
                            " " + item.getValue().get(1));
                }

                System.out.println("Please select your item");

                String selection = input.nextLine();
                for (Map.Entry<String, List<String>> product : vendingItems.entrySet()) {
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
            }

            else if (inputString.equals("3")) {
                int quarters = (int)(totalMoney / .25);
                totalMoney -= quarters * .25;

                int dimes = (int)(totalMoney / .10);
                totalMoney -= dimes * .10;

                int nickles = (int)(totalMoney / .05);
                totalMoney -= nickles * .05;

                System.out.println("Dispensing " + quarters + " Quarters");
                System.out.println("Dispensing " + dimes + " Dimes");
                System.out.println("Dispensing " + nickles + " Nickles");

                break;
            }

        }
    }

}