package tests.usersitetests;

import actions.UserActions;
import common.BaseTestClass;
import testconfigs.testdatamanagers.TestServerConfiguretionSiteManager;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.HeaderMenu;
import pageobjects.SiteManagerPage;
import pageobjects.SiteSettingsPage;

import static com.selenium.utils.NameGenerator.generateNewHttpsSiteName;
import static testconfigs.testdata.TestData.testEmail;
import static testconfigs.testdata.TestData.testPass;
import static testconfigs.testdatamanagers.TestServerConfiguretionSiteManager.setHttpsSite;

public class Test_Pos_CreateHttpsSite extends BaseTestClass {

    @Parameters("browser")
    @Test
    public void createHttpsSiteTest(@Optional("chrome") String browser) throws Exception{

        TestServerConfiguretionSiteManager testSiteManager = new TestServerConfiguretionSiteManager();
        int siteNumber = Integer.valueOf(testSiteManager.getHttpsSiteNumber());
        UserActions userActions = new UserActions(driver);
        SiteManagerPage siteManagerPage = new SiteManagerPage(driver);
        SiteSettingsPage siteSettingsPage = new SiteSettingsPage(driver);
        String siteUrl = generateNewHttpsSiteName(siteNumber);
        siteManagerPage.createNewSite(siteUrl);
        String script = userActions.createSite(siteUrl);
        String siteSDK = siteSettingsPage.getSDKlink();
        setHttpsSite(siteUrl, siteNumber, testEmail, testPass);
        new HeaderMenu(driver).clickLogo().verifySitePresent(siteUrl);
        userActions.checkCreateSiteMail(siteUrl, browser);
        siteManagerPage.setSiteDatas(siteUrl, script, siteSDK);
    }
}
