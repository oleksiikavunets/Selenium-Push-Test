
import actions.SiteManager;
import actions.Timer;
import actions.UserActions;
import com.selenium.ConfigTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.HeaderMenu;
import pageobjects.MainAdminPage;
import testutils.Listeners.LogListener;


/**
 * Created by Oleksii on 31.07.2017.
 */

@Listeners(LogListener.class)
public class Test_Pos_CreateHTTPSite extends SeleniumBaseClass {

    @Parameters("browser")
    @Test
    public void createHttpSitePos(@Optional("chrome") String browser) throws Exception {
        ConfigTest configTest = new ConfigTest();
        int siteNumber = Integer.valueOf(configTest.getHttpSiteNumber());
        MainAdminPage mainAdminPage = new MainAdminPage(driver, wait);
        HeaderMenu headerMenu = new HeaderMenu(driver, wait);
        UserActions userActions = new UserActions(driver, wait);
//        String siteUrl = TestData.newSitePattern + RandomGenerator.nextString() + ".com";
        String siteUrl = "http://" + String.valueOf(configTest.iTest).toLowerCase() + siteNumber + SiteManager.siteDomain;
        System.out.println(siteUrl + "New Site");
        SiteManager.createNewSite(siteUrl);
        String script = userActions.createSite(siteUrl);
        userActions.checkCreateSiteMail(siteUrl, browser);
        SiteManager.setScript(siteUrl, script);
        driver.get(siteUrl);
        Timer.waitSeconds(5);
        configTest.setHttpSite(siteUrl, ++siteNumber);

//        headerMenu.clickLogo();
//        mainAdminPage.verifySitePresent(siteUrl);
//        userActions.deleteSite(siteUrl);
    }
}
