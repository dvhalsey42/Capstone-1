package com.techelevator.view;

import com.techelevator.Gum;
import org.junit.Assert;
import org.junit.Test;

public class GumTest {
    @Test
    public void gum_test_normal_purchase(){
        double money_in_machine = 50.0;
        Gum gum = new Gum(money_in_machine);

        double result = gum.buyProduct("test String", 5.99);

        Assert.assertEquals(result, 44.01, 0.0);
    }
    @Test
    public void gum_test_no_money(){
        double money_in_machine_2 = 0;

        Gum gum_2 = new Gum(money_in_machine_2);

        double result = gum_2.buyProduct("Test String", 1.00);

        Assert.assertEquals(result, 0, 0.0);
    }
}