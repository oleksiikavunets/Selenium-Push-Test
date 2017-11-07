package testutils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
public class TestRetryAnalyzer implements IRetryAnalyzer {

    int count=0;
    private int iterationTimes = 1; // These many number of times Test methods will be re executed

    @Override
    public boolean retry(ITestResult result) {
        System.out.println("running retry logic for  '"
                + result.getName()
                + "' in class " + result.getTestClass().getRealClass().getSimpleName());
        if (count < iterationTimes) {
            count++;
            return true;
        }
        return false;
    }
}
