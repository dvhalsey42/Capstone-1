package com.techelevator.view;

import com.techelevator.Drink;
import org.junit.Assert;
import org.junit.Test;

public class DrinkTest {
    @Test
    public void drink_test_normal_purchase(){
        double money_in_machine = 50.0;
        Drink drink = new Drink(money_in_machine);

        double result = drink.buyProduct("test String", 5.99);

        Assert.assertEquals(result, 44.01, 0.0);
    }
    @Test
    public void drink_test_no_money(){
        double money_in_machine_2 = 0;

        Drink drink_2 = new Drink(money_in_machine_2);

        double result = drink_2.buyProduct("Test String", 1.00);

        Assert.assertEquals(result, 0, 0.0);
    }
}