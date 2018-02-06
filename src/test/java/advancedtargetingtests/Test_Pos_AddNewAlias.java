package advancedtargetingtests;

import actions.UserActions;
import common.BaseTestClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.CreateCampaignPage;
import pageobjects.LogInPage;
import pageutils.Navigator;
import testdata.TestData;
import testdatamanagers.TestDataManager;
import testutils.Listeners.LogListener;

import static testdata.TestData.newAlias;

@Listeners(LogListener.class)
public class Test_Pos_AddNewAlias extends BaseTestClass {

    @Test(dataProvider = "testSiteProvider", groups = {"subscription", "advanced settings", "alias"})
    public void addNewAliasTest(String testSite) throws Exception {
        Navigator navigator = new Navigator(driver);
        UserActions userActions = new UserActions(driver, wait);
        String alias = newAlias;

        driver.manage().deleteAllCookies();

        userActions.addNewAlias(testSite, alias);

        TestDataManager testDataManager = new TestDataManager();
        testDataManager.setSite(testSite);
        testDataManager.setAlias(alias);

        new LogInPage(driver).login(TestData.email, TestData.pass);
        CreateCampaignPage.AdvancedOptions advancedOptions = navigator.open(CreateCampaignPage.class, testSite)
                .openAdvancedOptions();
        advancedOptions.addAliasToCampaign(alias);
    }

    @DataProvider(name = "testSiteProvider")
    public Object[] provideTestSites() {
        return TestData.provideTestSites();
    }
}