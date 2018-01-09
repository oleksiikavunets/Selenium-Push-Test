import actions.UserActions;
import testdatamanagers.TestSiteManager;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.HeaderMenu;
import pageobjects.SiteManagerPage;
import pageobjects.SiteSettingsPage;

import static com.selenium.utils.NameGenerator.generateNewHttpsSiteName;

public class Test_Pos_CreateHttpsSite extends BaseTestClass{

    @Parameters("browser")
    @Test
    public void createHttpsSitePos(@Optional("chrome") String browser) throws Exception{

        TestSiteManager testSiteManager = new TestSiteManager();
        int siteNumber = Integer.valueOf(testSiteManager.getHttpsSiteNumber());
        UserActions userActions = new UserActions(driver);
        SiteManagerPage siteManagerPage = new SiteManagerPage(driver);
        SiteSettingsPage siteSettingsPage = new SiteSettingsPage(driver);
        String siteUrl = generateNewHttpsSiteName(siteNumber);
        siteManagerPage.createNewSite(siteUrl);
        String script = userActions.createSite(siteUrl);
        String siteSDK = siteSettingsPage.getSDKlink();
        testSiteManager.setHttpsSite(siteUrl, ++siteNumber);
        new HeaderMenu(driver).clickLogo().verifySitePresent(siteUrl);
        userActions.checkCreateSiteMail(siteUrl, browser);
        siteManagerPage.setSiteDatas(siteUrl, script, siteSDK);
    }
}
