package testutils.Listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class LogListener implements ITestListener {
    Logger Log = LogManager.getLogger(LogListener.class);


    @Override
    public void onTestStart(ITestResult result) {
        Log.info("***** STARTING TEST METHOD: " + getTestMethodName(result) + " *****");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Log.info("TEST METHOD " + getTestMethodName(result) + ": PASSED\r");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Log.info("TEST METHOD " + getTestMethodName(result) + ": FAILED\r");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Log.info("TEST METHOD " + getTestMethodName(result) + ": SKIPPED\r");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        Log.info("test failed but within success % " + getTestMethodName(result));
    }

    @Override
    public void onStart(ITestContext context) {
//        Log.info("on start of test " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
//        Log.info("on finish of test " + context.getName());
    }

    private static String getTestMethodName(ITestResult result) {
        return result.getMethod().getConstructorOrMethod().getName();
    }
}
