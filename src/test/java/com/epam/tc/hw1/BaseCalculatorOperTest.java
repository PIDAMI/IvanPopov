package com.epam.tc.hw1;

import com.epam.tat.module4.Calculator;
import org.testng.annotations.BeforeMethod;

public class BaseCalculatorOperTest {
    protected Calculator calculator;
    public final double doubleOffset = 1e-5;

    @BeforeMethod
    public void setUp() {
        calculator = new Calculator();
    }

}
