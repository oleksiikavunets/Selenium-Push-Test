
import actions.UserActions;
import common.BaseTestClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.HeaderMenu;
import pageobjects.SiteManagerPage;
import testutils.Listeners.LogListener;

import static com.selenium.utils.NameGenerator.generateNewHttpSiteName;
import static testdata.TestData.testEmail;
import static testdata.TestData.testPass;
import static testdatamanagers.TestSiteManager.getHttpSiteNumber;
import static testdatamanagers.TestSiteManager.setHttpSite;

@Listeners(LogListener.class)
public class Test_Pos_CreateHttpSite extends BaseTestClass {

    @Parameters("browser")
    @Test
    public void createHttpSiteTest(@Optional("chrome") String browser) throws Exception {
        int siteNumber = Integer.valueOf(getHttpSiteNumber());
        UserActions userActions = new UserActions(driver);
        SiteManagerPage siteManagerPage = new SiteManagerPage(driver);

        String siteUrl = generateNewHttpSiteName(siteNumber);

        siteManagerPage.createNewSite(siteUrl);
        String script = userActions.createSite(siteUrl);
        System.out.println("Site Script: " + script);
        setHttpSite(siteUrl, siteNumber, testEmail, testPass);
        new HeaderMenu(driver).clickLogo().verifySitePresent(siteUrl);
        userActions.checkCreateSiteMail(siteUrl, browser);

        siteManagerPage.setSiteDatas(siteUrl, script);
        driver.get(siteUrl);
    }
}
