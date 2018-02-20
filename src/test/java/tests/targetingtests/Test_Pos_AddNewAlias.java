package tests.targetingtests;

import actions.UserActions;
import common.BaseTestClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.CreateCampaignPage;
import pageobjects.LogInPage;
import pageutils.NavigationUtil;
import testconfigs.testdata.TestData;
import testconfigs.testdata.TestDataProvider;
import testconfigs.testdatamanagers.TestDataManager;
import testutils.Listeners.LogListener;

import static testconfigs.testdata.TestData.newAlias;

@Listeners(LogListener.class)
public class Test_Pos_AddNewAlias extends BaseTestClass {

    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "getPermanentTestSites",
            groups = {"subscription", "advanced settings", "alias"})
    public void addNewAliasTest(String testSite) throws Exception {
        NavigationUtil navigationUtil = new NavigationUtil(driver);
        UserActions userActions = new UserActions(driver, wait);
        String alias = newAlias;

        driver.manage().deleteAllCookies();

        userActions.addNewAlias(testSite, alias);

        TestDataManager testDataManager = new TestDataManager();
        testDataManager.setSite(testSite);
        testDataManager.setAlias(alias);

        new LogInPage(driver).login(TestData.email, TestData.pass);
        CreateCampaignPage.AdvancedOptions advancedOptions = navigationUtil.open(CreateCampaignPage.class, testSite)
                .openAdvancedOptions();
        advancedOptions.addAliasToCampaign(alias);
    }
}