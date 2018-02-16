package tests.usersitetests;

import actions.UserActions;
import com.selenium.enums.TestSitesScope;
import common.BaseTestClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.HeaderMenu;
import pageobjects.SiteManagerPage;
import testconfigs.baseconfiguration.TestCofiguration;
import testutils.Listeners.LogListener;

import static com.selenium.utils.NameGenerator.generateNewHttpSiteName;
import static testconfigs.testdata.TestData.testEmail;
import static testconfigs.testdata.TestData.testPass;
import static testconfigs.testdatamanagers.TestSiteManager.getHttpSiteNumber;
import static testconfigs.testdatamanagers.TestSiteManager.setHttpSite;

@Listeners(LogListener.class)
public class Test_Pos_CreateHttpSite extends BaseTestClass {

    @Test
    public void createHttpSiteTest() throws Exception {
        if(!(TestCofiguration.testSitesScope == TestSitesScope.TEST_HTTPS_ONLY)) {
            int siteNumber = Integer.valueOf(getHttpSiteNumber());
            UserActions userActions = new UserActions(driver);
            SiteManagerPage siteManagerPage = new SiteManagerPage(driver);

            String siteUrl = generateNewHttpSiteName(siteNumber);

            siteManagerPage.createNewSite(siteUrl);
            String script = userActions.createSite(siteUrl);
            System.out.println("Site Script: " + script);
            setHttpSite(siteUrl, siteNumber, testEmail, testPass);
            new HeaderMenu(driver).clickLogo().verifySitePresent(siteUrl);
            userActions.checkCreateSiteMail(siteUrl);

            siteManagerPage.setSiteDatas(siteUrl, script);
            driver.get(siteUrl);
        }else {
            System.out.println("CURRENT TEST IS OUT OF SCOPE.............................");
        }
    }
}
