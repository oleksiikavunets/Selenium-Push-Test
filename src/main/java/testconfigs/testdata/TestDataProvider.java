package testconfigs.testdata;

import com.selenium.enums.TestSitesScope;
import com.selenium.utils.RandomGenerator;
import org.testng.annotations.DataProvider;
import testconfigs.baseconfiguration.TestCofiguration;

import static com.selenium.enums.Protocol.HTTP;
import static com.selenium.enums.Protocol.HTTPS;
import static com.selenium.enums.Server.UBR;
import static testconfigs.baseconfiguration.TestServerConfiguretion.iTest;
import static testconfigs.testdata.TestData.newHttpSitePattern;
import static testconfigs.testdata.TestData.newHttpsSitePattern;
import static testconfigs.testdatamanagers.TestSiteManager.getOldTestSiteUrl;

public class TestDataProvider {


    @DataProvider(name = "testSiteProvider")
    public static Object[] provideTestSites() {
        return iTest == UBR ? new Object[]{getOldTestSiteUrl(HTTPS)} :
                TestCofiguration.testSitesScope == TestSitesScope.TEST_BOTH ?
                        new Object[]{getOldTestSiteUrl(HTTPS), getOldTestSiteUrl(HTTP)} :
                        TestCofiguration.testSitesScope == TestSitesScope.TEST_HTTPS_ONLY ?
                                new Object[]{getOldTestSiteUrl(HTTPS)} :
                                TestCofiguration.testSitesScope == TestSitesScope.TEST_HTTP_ONLY ?
                                        new Object[]{getOldTestSiteUrl(HTTP)} : null;
    }

    @DataProvider(name = "getRandomSiteNames")
    public static Object[] getRandomSiteNames(){
        String httpName = newHttpSitePattern + RandomGenerator.nextString() + ".com";
        String httpsName = newHttpsSitePattern + RandomGenerator.nextString() + ".com";

        return iTest == UBR ? new Object[]{httpsName} :
                TestCofiguration.testSitesScope == TestSitesScope.TEST_BOTH ?
                        new Object[]{httpsName, httpName} :
                        TestCofiguration.testSitesScope == TestSitesScope.TEST_HTTPS_ONLY ?
                                new Object[]{httpsName} :
                                TestCofiguration.testSitesScope == TestSitesScope.TEST_HTTP_ONLY ?
                                        new Object[]{httpName} : null;
    }
}
