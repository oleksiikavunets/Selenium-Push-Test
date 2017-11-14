import actions.UserActions;
import testdata.TestData;
import testutils.Listeners.LogListener;
import com.selenium.utils.RandomGenerator;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
public class Test_Pos_DeleteSite extends SeleniumBaseClass {

    @Parameters("browser")
    @Test
    public void deleteSiteTest(@Optional("chrome") String browser) throws Exception {
        MainAdminPage main = new MainAdminPage(driver, wait);
        UserActions userActions = new UserActions(driver, wait);

        String siteUrl = TestData.newSitePattern + RandomGenerator.nextString() + ".com";
        userActions.createSite(siteUrl);
        userActions.checkCreateSiteMail(siteUrl, browser);

        userActions.deleteSite(siteUrl);
        wait.until(ExpectedConditions.visibilityOfElementLocated(main.siteList));
        main.verifySiteToBeDeleted(siteUrl);
        new HeaderMenu(driver, wait).logout();
    }
}
