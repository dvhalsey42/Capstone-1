package com.techelevator.view;

import com.techelevator.Candy;
import com.techelevator.Chip;
import org.junit.Assert;
import org.junit.Test;

public class ChipTest {
    @Test
    public void chip_test_normal_purchase(){
        double money_in_machine = 50.0;
        Chip chip = new Chip(money_in_machine);

        double result = chip.buyProduct("test String", 5.99);

        Assert.assertEquals(result, 44.01, 0.0);
    }
    @Test
    public void chip_test_no_money(){
        double money_in_machine_2 = 0;

        Chip chip_2 = new Chip(money_in_machine_2);

        double result = chip_2.buyProduct("Test String", 1.00);

        Assert.assertEquals(result, 0, 0.0);
    }
}