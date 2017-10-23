
import actions.UserActions;


import com.selenium.utils.RandomGenerator;

import org.testng.annotations.Test;
import pageobjects.HeaderMenu;
import pageobjects.MainAdminPage;


/**
 * Created by Oleksii on 31.07.2017.
 */
public class Test_Pos_CreateHTTPSite extends SeleniumBaseClass {

    @Test
    public void createHttpSitePos() throws Exception {
        MainAdminPage mainAdminPage = new MainAdminPage(driver, wait);
        HeaderMenu headerMenu = new HeaderMenu(driver, wait);
        UserActions userActions = new UserActions(driver, wait);
        String siteUrl = "http://" + RandomGenerator.nextString() + ".com";
        userActions.createSite(siteUrl);
        userActions.checkCreateSiteMail(siteUrl);
        headerMenu.clickLogo();
        mainAdminPage.verifySitePresent(siteUrl);
        userActions.deleteSite(siteUrl);
        headerMenu.logout();
    }
}
