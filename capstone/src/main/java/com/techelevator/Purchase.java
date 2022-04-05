package com.techelevator;

import java.util.Scanner;

public class Purchase {
    private double totalMoney;

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public void addMoney(double amountToAdd){
        setTotalMoney(getTotalMoney() + amountToAdd);
    }


    Scanner input = new Scanner(System.in);

    public void purchaseMenu() {
        while (true) {
            System.out.println("(1) Feed Money");
            System.out.println("(2) Select Product");
            System.out.println("(3) Finish Transaction");
            System.out.println("\n" + "Current Money Provided: $" + totalMoney);

            Scanner input = new Scanner(System.in);

            String inputString = input.nextLine();

            if (inputString.equals("1")) {
                System.out.println("Please enter dollar value of bill to feed into vending machine");
                addMoney(Double.valueOf(input.nextLine()));
            } else if (input.equals("2")) {
                //select product
            } else if (input.equals("3")) {
                break;
            }

        }
    }
}