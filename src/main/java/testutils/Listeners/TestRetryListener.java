package testutils.Listeners;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import testconfigs.testrunconfigurations.TestRetryAnalyzer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class TestRetryListener implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation testAnnotation, Class testClass, Constructor testConstructor,
                          Method testMethod) {

        // This will set retryAnalyzer to the test methods if not set in class level
        if (testAnnotation.getRetryAnalyzer() == null) {

            testAnnotation.setRetryAnalyzer(TestRetryAnalyzer.class);
        }
    }
}
