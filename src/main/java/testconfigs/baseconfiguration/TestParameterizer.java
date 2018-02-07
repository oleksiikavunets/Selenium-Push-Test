package testconfigs.baseconfiguration;

import com.selenium.enums.Server;
import com.selenium.enums.TestSitesScope;


public class TestParameterizer {

    public static Server testServer ;
    public static TestSitesScope testSitesScope;
    public static boolean clickOnPush;
    public static int failedTestsRetryCount;


    public static void setTestServer(Server serverToTest){
        testServer = serverToTest;
    }

    public static void setTestSitesScope(TestSitesScope scope){
        testSitesScope = scope;
    }

    public static void setClickOnPush(boolean click){
        clickOnPush = click;
    }

    public static void setFailedTestsRetryCount(int count){
        failedTestsRetryCount = count;
    }


}
