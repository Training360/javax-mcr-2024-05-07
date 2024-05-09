package employees;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;

public class TestNgRunnerMain {

    public static void main(String[] args) {
        TestListenerAdapter tla = new TestListenerAdapter();
        TestNG testng = new TestNG();
        testng.setTestClasses(new Class[] { CalculatorTest.class });
        testng.addListener(tla);
        testng.run();

        var tests = tla.getPassedTests();
        for (var test: tests) {
            System.out.println(test);
        }
    }
}
