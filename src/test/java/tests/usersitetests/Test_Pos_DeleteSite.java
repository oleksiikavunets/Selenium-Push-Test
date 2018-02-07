package tests.usersitetests;

import actions.UserActions;
import common.BaseTestClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.MainAdminPage;
import testconfigs.testdata.TestDataProvider;
import testutils.Listeners.LogListener;

/**
 * Created by Oleksii on 31.07.2017.
 */
@Listeners(LogListener.class)
public class Test_Pos_DeleteSite extends BaseTestClass {

    @Parameters("browser")
    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "getRandomSiteNames")
    public void deleteSiteTest( String siteUrl) throws Exception {
        UserActions userActions = new UserActions(driver);

        userActions.createSite(siteUrl);
        userActions.checkCreateSiteMail(siteUrl);

        MainAdminPage main = userActions.deleteSite(siteUrl);
        main.verifySiteToBeDeleted(siteUrl);
    }
}
