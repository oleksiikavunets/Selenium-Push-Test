package common;

import com.selenium.enums.Server;
import com.selenium.enums.TestSitesScope;

public class TestParameters {

    public static Server serverToTest = Server.P2B;
    public static TestSitesScope testSitesScope = TestSitesScope.TEST_BOTH;
    public static boolean clickOnPush = true;
    public static int failedTestsRetryCount = 2; //ONLY FOR  TEST SUITE RUNNING


}