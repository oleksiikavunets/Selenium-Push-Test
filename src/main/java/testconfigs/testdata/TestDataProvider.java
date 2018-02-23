package testconfigs.testdata;

import com.selenium.enums.TestSitesScope;
import utils.RandomGenerator;
import org.testng.annotations.DataProvider;
import testconfigs.baseconfiguration.TestCofiguration;

import static com.selenium.enums.Protocol.HTTP;
import static com.selenium.enums.Protocol.HTTPS;
import static com.selenium.enums.Server.UBR;
import static testconfigs.baseconfiguration.TestServerConfiguretion.iTest;
import static testconfigs.testdata.TestData.newHttpSitePattern;
import static testconfigs.testdata.TestData.newHttpsSitePattern;
import static testconfigs.testdatamanagers.TestSiteManager.getNewTestSiteUrl;
import static testconfigs.testdatamanagers.TestSiteManager.getOldTestSiteUrl;

public class TestDataProvider {


    @DataProvider(name = "getPermanentTestSites")
    public static Object[] getPermanentTestSites() {
        return configureSiteData(getOldTestSiteUrl(HTTPS), getOldTestSiteUrl(HTTP));
    }

    @DataProvider(name = "getRandomSiteNames")
    public static Object[] getRandomSiteNames(){
        return configureSiteData(newHttpsSitePattern + RandomGenerator.nextString() + ".com",
                newHttpSitePattern + RandomGenerator.nextString() + ".com");
    }

    @DataProvider(name = "getNewlyCreatedSites")
    public static Object[] getNewlyCreatedSites(){
        return configureSiteData(getNewTestSiteUrl(HTTPS), getNewTestSiteUrl(HTTP));
    }

    private static Object[] configureSiteData(String https, String http){
        return iTest == UBR ? new Object[]{https} :
                TestCofiguration.testSitesScope == TestSitesScope.TEST_BOTH ?
                        new Object[]{https, http} :
                        TestCofiguration.testSitesScope == TestSitesScope.TEST_HTTPS_ONLY ?
                                new Object[]{https} :
                                TestCofiguration.testSitesScope == TestSitesScope.TEST_HTTP_ONLY ?
                                        new Object[]{http} : null;
    }
}
