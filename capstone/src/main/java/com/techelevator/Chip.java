package com.techelevator;

public class Chip extends Purchase implements Purchasable{
    private double totalMoneyInMachine;

    public Chip(double totalMoney2) {
        this.totalMoneyInMachine = totalMoney2;
    }
    //checks to see if user has enough money to complete transaction,
    //dispenses item if so. returns remaining money
    @Override
    public double buyProduct(String product, double price) {
        if(totalMoneyInMachine >= price){
            System.out.println(product + ": " + currencyFormat.format(price));
            System.out.println("Money Remaining: " +
                    currencyFormat.format(totalMoneyInMachine - price));
            System.out.println("Crunch Crunch, Yum!");
            return Math.round((totalMoneyInMachine-price)*100.0) /100.0;
        }
        else{
            System.out.println("Not enough money");
            return totalMoneyInMachine;
        }
    }
}
