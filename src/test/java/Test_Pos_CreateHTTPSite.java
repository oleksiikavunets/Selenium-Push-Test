
import actions.UserActions;


import testutils.Listeners.LogListener;
import com.selenium.utils.RandomGenerator;

import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.HeaderMenu;
import pageobjects.MainAdminPage;


/**
 * Created by Oleksii on 31.07.2017.
 */

@Listeners(LogListener.class)
public class Test_Pos_CreateHTTPSite extends SeleniumBaseClass {

    @Parameters("browser")
    @Test
    public void createHttpSitePos(@Optional("chrome") String browser) throws Exception {
        MainAdminPage mainAdminPage = new MainAdminPage(driver, wait);
        HeaderMenu headerMenu = new HeaderMenu(driver, wait);
        UserActions userActions = new UserActions(driver, wait);
        String siteUrl = "http://" + RandomGenerator.nextString() + ".com";
        userActions.createSite(siteUrl);
        userActions.checkCreateSiteMail(siteUrl, browser);

        headerMenu.clickLogo();
        mainAdminPage.verifySitePresent(siteUrl);
        userActions.deleteSite(siteUrl);
        headerMenu.logout();
    }
}
