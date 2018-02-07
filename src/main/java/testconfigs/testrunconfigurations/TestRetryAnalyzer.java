package testconfigs.testrunconfigurations;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import testconfigs.baseconfiguration.TestParameterazer;

public class TestRetryAnalyzer implements IRetryAnalyzer {

    int  count=0;
    private static int iterationTimes = TestParameterazer.failedTestsRetryCount; // A number of times Test methods to be re executed

    @Override
    public boolean retry(ITestResult result) {
        System.out.println("RUNNING RETRY LOGIC FOR  '"
                + result.getName()
                + "' IN CLASS " + result.getTestClass().getRealClass().getSimpleName() +
        ".........................................................................");
        if (count < iterationTimes) {
            count++;
            return true;
        }
        return false;
    }

    public static void setRetryCount(int retryCount) {
        iterationTimes = retryCount;
    }
}
