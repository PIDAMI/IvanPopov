package com.epam.tc.hw1;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.data.Offset;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class CalculatorSumTest extends BaseCalculatorOperTest {

    @DataProvider(name = "dataForSumDouble")
    public Object[][] getDataForSumDouble() {
        return new Object[][]{
                { 0., 9., 9. },
                {1.3, 2.2, 3.5},
                {-1012.1024, 1000, -12.1024},
                {Integer.MAX_VALUE, 1., 2_147_483_648.0},
        };
    }

    @DataProvider(name = "dataForSumLong")
    public Object[][] getDataForSumLong() {
        return new Object[][]{
                { 7, 12, 19 },
                { 0, 9, 9 },
                { -4, -66, -70 },
                {-390, 125, -265},
                {Integer.MAX_VALUE, 1, 2_147_483_648L},
                {Long.MAX_VALUE, Long.MIN_VALUE, -1L}
        };
    }

    @Test(dataProvider = "dataForSumLong")
    public void testSumLong(long x, long y, long expected) {
        long actual = calculator.sum(x, y);
        assertThat(actual).isEqualTo(expected);
    }

    @Test(dataProvider = "dataForSumLong")
    public void testSumCommutativityLong(long x, long y, long expected) {
        long oneWay = calculator.sum(x, y);
        long otherWay = calculator.sum(y, x);
        assertThat(oneWay).isEqualTo(otherWay);
    }

    @Test(dataProvider = "dataForSumDouble")
    public void testSumDouble(double x, double y, double expected) {
        double actual = calculator.sum(x, y);
        assertThat(actual).isCloseTo(expected,
                Offset.offset(this.doubleOffset));
    }

    @Test(dataProvider = "dataForSumDouble")
    public void testSumCommutativityDouble(double x,
                                           double y, double expected) {
        double oneWay = calculator.sum(x, y);
        double otherWay = calculator.sum(y, x);
        assertThat(oneWay).isCloseTo(otherWay,
                Offset.offset(this.doubleOffset));
    }
}
