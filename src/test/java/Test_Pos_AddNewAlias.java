import actions.UserActions;
import com.selenium.utils.Listener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.*;
import testdata.TestData;

@Listeners(Listener.class)
public class Test_Pos_AddNewAlias extends SeleniumBaseClass {

    @Test (groups = {"subscription", "advanced settings", "alias"})
    public void addNewAlias() throws Exception {
        LogInPage logInPage = new LogInPage(driver, wait);
        CreateCampaignPage createCampaignPage = new CreateCampaignPage(driver, wait);
        UserActions userActions = new UserActions(driver, wait);
        String testSite = TestData.testSite;
        String alias = TestData.newAlias;

        driver.manage().deleteAllCookies();

        userActions.addNewAlias(testSite, alias);
        MainAdminPage mainAdminPage = logInPage.login(TestData.email, TestData.pass);
        SideBar sideBar = mainAdminPage.openSite(testSite);
        sideBar.openCreateCampaignPage();
        CreateCampaignPage.AdvancedOptions advancedOptions = createCampaignPage.openAdvancedOptions();
        advancedOptions.addAliasToCampaign(alias);
        new HeaderMenu(driver, wait).logout();
    }
}