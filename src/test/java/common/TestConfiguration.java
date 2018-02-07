package common;

import com.selenium.enums.Server;
import com.selenium.enums.TestSitesScope;

public class TestConfiguration {

    public static Server serverToTest = Server.GRV;
    public static TestSitesScope testSitesScope = TestSitesScope.TEST_HTTPS_ONLY;
    public static boolean clickOnPush = true;
    public static int failedTestsRetryCount = 2; //ONLY FOR  TEST SUITE RUNNING


}
