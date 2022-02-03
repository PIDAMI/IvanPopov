import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorMultTest extends BaseCalculatorOperTest{

    @DataProvider(name = "dataForMultLong")
    public Object[][] getDataForMultLong(){
        return new Object[][]{
                { 7, 12, 84 },
                {20, 1, 20},
                { 9, 0, 0 },
                { -4, -60, 240 },
                {-1, 22, -22},
        };
    }

    @DataProvider(name = "dataForMultDouble")
    public Object[][] getDataForSumDouble(){
        return new Object[][]{
                { 0., 9., 0. },
                {1., 1_000_000., 1_000_000.},
                {-1., 34.33, -34.33},
                {1.12, 10, 11.2},
                {-1.5, 45, -67.5},
        };
    }

    @Test(dataProvider = "dataForMultLong")
    public void testMultLong(long x, long y, long expected) {
        long actual = calculator.mult(x,y);
        assertThat(actual).isEqualTo(expected);
    }

    @Test(dataProvider = "dataForMultLong")
    public void testMultCommutativityLong(long x,
                                          long y, long expected) {
        long oneWay = calculator.sum(x,y);
        long otherWay = calculator.sum(y,x);
        assertThat(oneWay).isEqualTo(otherWay);
    }


    @Test(dataProvider = "dataForMultDouble")
    public void testMultDouble(double x, double y, double expected) {
        double actual = calculator.mult(x,y);
        assertThat(actual).isEqualTo(expected);
    }

    @Test(dataProvider = "dataForMultDouble")
    public void testMultCommutativityDouble(double x,
                                            double y, double expected) {
        double oneWay = calculator.sum(x,y);
        double otherWay = calculator.sum(y,x);
        assertThat(oneWay).isEqualTo(otherWay);
    }

}
