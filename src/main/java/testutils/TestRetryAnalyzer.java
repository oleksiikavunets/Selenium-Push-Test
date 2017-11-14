package testutils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
public class TestRetryAnalyzer implements IRetryAnalyzer {

    int count=0;
    private int iterationTimes = 1; // A number of times Test methods to be re executed

    @Override
    public boolean retry(ITestResult result) {
        System.out.println("Running retry logic for  '"
                + result.getName()
                + "' in class " + result.getTestClass().getRealClass().getSimpleName());
        if (count < iterationTimes) {
            count++;
            return true;
        }
        return false;
    }
}
