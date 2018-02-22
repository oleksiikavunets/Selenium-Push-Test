package common;

import com.selenium.enums.Server;
import com.selenium.enums.TestSitesScope;

public class TestParameters {

    public static Server serverToTest = Server.GRV_7700;
    public static TestSitesScope testSitesScope = TestSitesScope.TEST_BOTH;
    public static boolean clickOnPush = false;
    public static int failedTestsRetryCount = 2; //ONLY FOR  TEST SUITE RUNNING


}
