package com.company.tests;

import com.company.Money;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
    private Money m1;
    private Money m2;
    private Money m3;

    @Before
    public void beforeTests(){
        // denne køre før hver test
        m1 = new Money(100, "dkk");
        m2 = new Money(200, "sek");
        m3 = new Money(100, "dkk");
    }

    @After
    public void afterTests(){
        // denne køre efter hver test
    }

    @Ignore
    @Test
    public void createMoney(){
        Assert.assertEquals(100,m1.getAmount());
        Assert.assertEquals(200,m2.getAmount());
    }


    @Test(timeout = 1000)
    public void compareMoney(){


        Assert.assertNotEquals(m1,m2);
        Assert.assertEquals(m1,m3);

    }

    @Test (expected = IllegalArgumentException.class)
    public void requireCurrency(){
        /* should fail - require a currency*/
        Money m1 = new Money(100, null);
    }
}
