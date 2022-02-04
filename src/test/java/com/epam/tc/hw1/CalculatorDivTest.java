package com.epam.tc.hw1;


import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.data.Offset;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CalculatorDivTest extends BaseCalculatorOperTest {

    @DataProvider(name = "dataForDivLong")
    public Object[][] getDataForDivLong() {
        return new Object[][]{
                { 7, 12, 0 },
                {20, 1, 20},
                { 0, 12312, 0 },
                { -65, -5, 13 }
        };
    }

    @DataProvider(name = "dataForDivDouble")
    public Object[][] getDataForDivDouble() {
        return new Object[][]{
                { 7., -13., -0.5384615384615385},
                { -7., -13., 0.5384615384615385},
                {20., 1.4, 14.285714285714286},
                { 0., 12312.321, 0. },
                {2.718, 0., Double.POSITIVE_INFINITY}
        };
    }

    @DataProvider(name = "dataForZeroDivLong")
    public Object[][] getDataForZeroDivLong() {
        return new Object[][]{
                { 3312 },
                { 0 },
                { -1 }
        };
    }

    @Test(dataProvider = "dataForDivLong")
    public void testDivLong(long x, long y, long expected) {
        long actual = calculator.div(x, y);
        assertThat(actual).isEqualTo(expected);
    }

    @Test(dataProvider = "dataForDivDouble")
    public void testDivDouble(double x, double y, double expected) {
        double actual = calculator.div(x, y);
        assertThat(actual).isCloseTo(expected,
                Offset.offset(this.doubleOffset));
    }

    @Test(dataProvider = "dataForZeroDivLong",
            expectedExceptions = {RuntimeException.class})
    public void testZeroDivLong(long x) {
        calculator.div(x, 0L);
    }



}
