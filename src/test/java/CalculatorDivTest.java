import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorDivTest extends BaseCalculatorOperTest{
    @DataProvider(name = "dataForDivLong")
    public Object[][] getDataForDivLong(){
        return new Object[][]{
                { 7, 12, 0 },
                {20, 1, 20},
                { 0, 12312, 0 },
                {12, 0, 0},
                { -65, -5, 13 }
        };
    }

    @Test(dataProvider = "dataForDivLong")
    public void testDivLong(long x, long y, long expected) {
        long actual = calculator.div(x,y);
        assertThat(actual).isEqualTo(expected);
    }
}
