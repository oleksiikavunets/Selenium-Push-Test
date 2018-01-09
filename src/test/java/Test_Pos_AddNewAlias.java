import actions.UserActions;
import testdatamanagers.TestDataManager;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.CreateCampaignPage;
import pageobjects.LogInPage;
import pageobjects.MainAdminPage;
import pageobjects.SideBar;
import testdata.TestData;
import testutils.Listeners.LogListener;

@Listeners(LogListener.class)
public class Test_Pos_AddNewAlias extends BaseTestClass {

    @Parameters("browser")
    @Test (groups = {"subscription", "advanced settings", "alias"})
    public void addNewAlias(@Optional("chrome") String browser) throws Exception {
        LogInPage logInPage = new LogInPage(driver);
        CreateCampaignPage createCampaignPage = new CreateCampaignPage(driver);
        UserActions userActions = new UserActions(driver, wait);
        String testSite = TestData.testSite;
        String alias = TestData.newAlias;

        driver.manage().deleteAllCookies();

        userActions.addNewAlias(browser, testSite, alias);

        TestDataManager testDataManager = new TestDataManager();
        testDataManager.setSite(testSite);
        testDataManager.setAlias(alias);

        MainAdminPage mainAdminPage = logInPage.login(TestData.email, TestData.pass);
        SideBar sideBar = mainAdminPage.openSite(testSite);
        sideBar.openCreateCampaignPage();
        CreateCampaignPage.AdvancedOptions advancedOptions = createCampaignPage.openAdvancedOptions();
        advancedOptions.addAliasToCampaign(alias);
    }
}