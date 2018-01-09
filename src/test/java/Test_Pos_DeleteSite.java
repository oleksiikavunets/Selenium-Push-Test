import actions.UserActions;
import com.selenium.utils.RandomGenerator;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.MainAdminPage;
import testdata.TestData;
import testutils.Listeners.LogListener;

/**
 * Created by Oleksii on 31.07.2017.
 */
@Listeners(LogListener.class)
public class Test_Pos_DeleteSite extends BaseTestClass {

    @Parameters("browser")
    @Test
    public void deleteSiteTest(@Optional("chrome") String browser) throws Exception {
        UserActions userActions = new UserActions(driver);

        String siteUrl = TestData.newHttpSitePattern + RandomGenerator.nextString() + ".com";
        userActions.createSite(siteUrl);
        userActions.checkCreateSiteMail(siteUrl, browser);

        MainAdminPage main = userActions.deleteSite(siteUrl);
        main.verifySiteToBeDeleted(siteUrl);
    }
}
