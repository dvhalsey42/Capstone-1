package com.techelevator.view;

import com.techelevator.Candy;
import org.junit.Assert;
import org.junit.Test;

public class CandyTest {
    @Test
    public void candy_test_normal_purchase(){
        double money_in_machine = 50.0;
        Candy candy = new Candy(money_in_machine);

        double result = candy.buyProduct("test String", 5.99);

        Assert.assertEquals(result, 44.01, 0.0);
    }
    @Test
    public void candy_test_no_money(){
        double money_in_machine_2 = 0;

        Candy candy_2 = new Candy(money_in_machine_2);

        double result = candy_2.buyProduct("Test String", 1.00);

        Assert.assertEquals(result, 0, 0.0);
    }
}


