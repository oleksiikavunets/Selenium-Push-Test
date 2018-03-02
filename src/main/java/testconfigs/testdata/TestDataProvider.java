package testconfigs.testdata;

import com.selenium.enums.TestSitesScope;
import org.testng.annotations.DataProvider;
import testconfigs.baseconfiguration.TestCofiguration;
import testconfigs.testdatamanagers.TestSiteManager;
import utils.RandomGenerator;

import static com.selenium.enums.Protocol.HTTP;
import static com.selenium.enums.Protocol.HTTPS;
import static com.selenium.enums.Server.UBR;
import static testconfigs.baseconfiguration.TestServerConfiguretion.iTest;
import static testconfigs.testdata.TestData.newHttpSitePattern;
import static testconfigs.testdata.TestData.newHttpsSitePattern;

public class TestDataProvider {


    @DataProvider(name = "getPermanentTestSites")
    public static Object[] getPermanentTestSites() {
        return configureSiteData(new TestSiteManager().getOldTestSiteUrl(HTTPS), new TestSiteManager().getOldTestSiteUrl(HTTP));
    }

    @DataProvider(name = "getRandomSiteNames")
    public static Object[] getRandomSiteNames(){
        return configureSiteData(newHttpsSitePattern + RandomGenerator.nextString() + ".com",
                newHttpSitePattern + RandomGenerator.nextString() + ".com");
    }

    @DataProvider(name = "getNewlyCreatedSites")
    public static Object[] getNewlyCreatedSites(){
        return configureSiteData( new TestSiteManager().getNewTestSiteUrl(HTTPS),  new TestSiteManager().getNewTestSiteUrl(HTTP));
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
