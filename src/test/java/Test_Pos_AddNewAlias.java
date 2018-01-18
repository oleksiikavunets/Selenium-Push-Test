import actions.UserActions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.CreateCampaignPage;
import pageobjects.LogInPage;
import pageutils.Navigator;
import testdata.TestData;
import testdatamanagers.TestDataManager;
import testutils.Listeners.LogListener;

import static testdata.TestData.newAlias;
import static testdata.TestData.testSite;

@Listeners(LogListener.class)
public class Test_Pos_AddNewAlias extends BaseTestClass {

    @Parameters("browser")
    @Test(groups = {"subscription", "advanced settings", "alias"})
    public void addNewAlias(@Optional("chrome") String browser) throws Exception {
        Navigator navigator = new Navigator(driver);
        UserActions userActions = new UserActions(driver, wait);
        String alias = newAlias;

        driver.manage().deleteAllCookies();

        userActions.addNewAlias(browser, testSite, alias);

        TestDataManager testDataManager = new TestDataManager();
        testDataManager.setSite(testSite);
        testDataManager.setAlias(alias);

        new LogInPage(driver).login(TestData.email, TestData.pass);
        CreateCampaignPage.AdvancedOptions advancedOptions = navigator.open(CreateCampaignPage.class, testSite)
                .openAdvancedOptions();
        advancedOptions.addAliasToCampaign(alias);
    }
}