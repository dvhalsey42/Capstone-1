package com.techelevator;

public class Drink extends Purchase implements Purchasable{
    private double totalMoney2;

    public Drink(double totalMoney2) {
        this.totalMoney2 = totalMoney2;
    }

    @Override
    public double buyProduct(String product, double price) {
        if(totalMoney2 >= price){
            System.out.println(product + ": " + price);
            System.out.println("Money Remaining: " +
                    currencyFormat.format(totalMoney2 - price));
            System.out.println("Glug, Glug, Yum!");
            return totalMoney2-price;
        }
        else{
            System.out.println("Not enough money");
            return totalMoney2;
        }
    }
}

