
import actions.UserActions;
import com.selenium.ConfigTest;
import com.selenium.TestSiteManager;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.HeaderMenu;
import pageobjects.SiteManagerPage;
import testutils.Listeners.LogListener;


/**
 * Created by Oleksii on 31.07.2017.
 */
// mpstestdepartment@gmail.com
@Listeners(LogListener.class)
public class Test_Pos_CreateHTTPSite extends BaseTestClass {

    @Parameters("browser")
    @Test
    public void createHttpSitePos(@Optional("chrome") String browser) throws Exception {
        TestSiteManager testSiteManager = new TestSiteManager();
        int siteNumber = Integer.valueOf(testSiteManager.getHttpSiteNumber());
        UserActions userActions = new UserActions(driver);
        SiteManagerPage siteManagerPage = new SiteManagerPage(driver);

        String siteUrl = generateNewHttpSiteName(siteNumber);

        siteManagerPage.createNewSite(siteUrl);
        String script = userActions.createSite(siteUrl);
        testSiteManager.setHttpSite(siteUrl, ++siteNumber);
        new HeaderMenu(driver).clickLogo().verifySitePresent(siteUrl);
        userActions.checkCreateSiteMail(siteUrl, browser);

        siteManagerPage.setScript(siteUrl, script);
        driver.get(siteUrl);
    }

    private String generateNewHttpSiteName(int siteNumber) {
        String server = String.valueOf(ConfigTest.iTest).toLowerCase().replace("_", "");
        return "http://" + server + siteNumber + SiteManagerPage.siteDomain;
    }
}
