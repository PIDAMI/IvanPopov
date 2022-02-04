package com.epam.tc.hw1;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.data.Offset;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class CalculatorSubtractTest extends BaseCalculatorOperTest {

    @DataProvider(name = "dataForSubtractLong")
    public Object[][] getDataForSubtractLong() {
        return new Object[][] {
                { 7, 12, -5 },
                {20, 1, 19},
                { 9, 0, 9 },
                { -4, -64, 60 },
                {Integer.MAX_VALUE, -1, 2_147_483_648L},
                {Long.MIN_VALUE, -Long.MAX_VALUE, -1L},
        };
    }

    @DataProvider(name = "dataForSubtractDouble")
    public Object[][] getDataForSubtractDouble() {
        return new Object[][] {
                { 7., 12., -5. },
                {20., 1., 19.},
                { 9., 0., 9. },
                { -4.12, -2., -2.12 },
                {Integer.MAX_VALUE, -1., 2_147_483_648.}
        };
    }

    @Test(dataProvider = "dataForSubtractLong")
    public void testSubtractLong(long x, long y, long expected) {
        long actual = calculator.sub(x, y);
        assertThat(actual).isEqualTo(expected);
    }

    @Test(dataProvider = "dataForSubtractLong")
    public void testSubtractAnticommutativityLong(long x,
                                                  long y, long expected) {
        long oneWay = calculator.sub(x, y);
        long otherWay = calculator.sub(y, x);
        assertThat(oneWay).isEqualTo(-otherWay);
    }

    @Test(dataProvider = "dataForSubtractDouble")
    public void testSubtractDouble(double x, double y, double expected) {
        double actual = calculator.sub(x, y);
        assertThat(actual).isCloseTo(expected,
                Offset.offset(this.doubleOffset));
    }

    @Test(dataProvider = "dataForSubtractDouble")
    public void testSubtractAnticommutativityDouble(double x,
                                                    double y, double expected) {
        double oneWay = calculator.sub(x, y);
        double otherWay = calculator.sub(y, x);
        assertThat(oneWay).isCloseTo(-otherWay,
                Offset.offset(this.doubleOffset));
    }

}
