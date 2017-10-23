import actions.UserActions;
import com.selenium.utils.RandomGenerator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import pageobjects.HeaderMenu;
import pageobjects.MainAdminPage;

/**
 * Created by Oleksii on 31.07.2017.
 */
public class Test_Pos_DeleteSite extends SeleniumBaseClass {


    @Test
    public void deleteSiteTest() throws Exception {
        MainAdminPage main = new MainAdminPage(driver, wait);
        UserActions userActions = new UserActions(driver, wait);

        String siteUrl = "http://" + RandomGenerator.nextString() + ".com";
        userActions.createSite(siteUrl);
        userActions.checkCreateSiteMail(siteUrl);
        userActions.deleteSite(siteUrl);
        wait.until(ExpectedConditions.visibilityOfElementLocated(main.siteList));
        main.verifySiteToBeDeleted(siteUrl);
        new HeaderMenu(driver, wait).logout();
    }
}
