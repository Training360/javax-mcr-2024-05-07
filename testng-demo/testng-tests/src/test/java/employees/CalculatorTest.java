package employees;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CalculatorTest {

    @Test
    public void add() {
        Assert.assertEquals(5, 2 + 3);
    }
}
