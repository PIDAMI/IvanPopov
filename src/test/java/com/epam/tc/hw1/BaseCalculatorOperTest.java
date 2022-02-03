package com.epam.tc.hw1;

import com.epam.tat.module4.Calculator;
import org.testng.annotations.BeforeMethod;

public class BaseCalculatorOperTest {
    public Calculator calculator;
    public final double DOUBLE_OFFSET = 1e-5;

    @BeforeMethod
    public void setUp(){
        calculator = new Calculator();
    }

}
