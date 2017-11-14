import actions.UserActions;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import testutils.Listeners.LogListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.*;
import testdata.TestData;

@Listeners(LogListener.class)
public class Test_Pos_AddNewAlias extends SeleniumBaseClass {

    @Parameters("browser")
    @Test (groups = {"subscription", "advanced settings", "alias"})
    public void addNewAlias(@Optional("chrome") String browser) throws Exception {
        LogInPage logInPage = new LogInPage(driver, wait);
        CreateCampaignPage createCampaignPage = new CreateCampaignPage(driver, wait);
        UserActions userActions = new UserActions(driver, wait);
        String testSite = TestData.testSite;
        String alias = TestData.newAlias;

        driver.manage().deleteAllCookies();

        userActions.addNewAlias(browser, testSite, alias);
        MainAdminPage mainAdminPage = logInPage.login(TestData.email, TestData.pass);
        SideBar sideBar = mainAdminPage.openSite(testSite);
        sideBar.openCreateCampaignPage();
        CreateCampaignPage.AdvancedOptions advancedOptions = createCampaignPage.openAdvancedOptions();
        advancedOptions.addAliasToCampaign(alias);
        new HeaderMenu(driver, wait).logout();
    }
}