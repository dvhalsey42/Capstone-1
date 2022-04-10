package com.techelevator;

public class Chip extends Purchase implements Purchasable{
    private double totalMoney2;

    public Chip(double totalMoney2) {
        this.totalMoney2 = totalMoney2;
    }

    public double buyProduct(String product, double price) {
        if(totalMoney2 >= price){
            System.out.println(product + ": " + price);
            System.out.println("Money Remaining: " + (totalMoney2 - price));
            System.out.println("Crunch Crunch, Yum!");
            return totalMoney2-price;
        }
        else if(totalMoney2 < price){
            System.out.println(price);
            System.out.println(totalMoney2);
            System.out.println("Not enough money");
            return totalMoney2;
        }
        else {
            return totalMoney2;
        }
    }
}
