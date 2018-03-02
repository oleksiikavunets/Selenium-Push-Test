package tests.usersitetests;

import actions.UserActions;
import com.selenium.enums.TestSitesScope;
import common.BaseTestClass;
import org.testng.annotations.Test;
import pageobjects.HeaderMenu;
import pageobjects.SiteManagerPage;
import pageobjects.SiteSettingsPage;
import testconfigs.baseconfiguration.TestCofiguration;
import testconfigs.testdatamanagers.TestSiteManager;
import testconfigs.testdatamanagers.TestUserManager;

import static utils.NameGenerator.generateNewHttpsSiteName;

public class Test_Pos_CreateHttpsSite extends BaseTestClass {

    @Test
    public void createHttpsSiteTest() throws Exception{

        if(!(TestCofiguration.testSitesScope == TestSitesScope.TEST_HTTP_ONLY)) {

            TestSiteManager testSiteManager = new TestSiteManager();
            int siteNumber = Integer.valueOf(testSiteManager.getHttpsSiteNumber());
            UserActions userActions = new UserActions(driver);
            SiteManagerPage siteManagerPage = new SiteManagerPage(driver);
            SiteSettingsPage siteSettingsPage = new SiteSettingsPage(driver);
            String siteUrl = generateNewHttpsSiteName(siteNumber);
            siteManagerPage.createNewSite(siteUrl);
            String script = userActions.createSite(siteUrl);
            String siteSDK = siteSettingsPage.getSDKlink();
            new TestSiteManager().setHttpsSite(siteUrl, siteNumber, new TestUserManager().getEmail(), new TestUserManager().getPassword());
            new HeaderMenu(driver).clickLogo().verifySitePresent(siteUrl);
            userActions.checkCreateSiteMail(siteUrl);
            siteManagerPage.setSiteDatas(siteUrl, script, siteSDK);
        }else {
            System.out.println("CURRENT TEST OFF SCOPE.............................");
        }
    }
}
