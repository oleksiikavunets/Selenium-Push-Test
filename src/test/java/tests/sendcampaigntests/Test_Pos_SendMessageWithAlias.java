package tests.sendcampaigntests;

import actions.UserActions;
import common.BaseTestClass;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.CampaignReportPage;
import pageobjects.CreateCampaignPage;
import pageobjects.LogInPage;
import pageutils.NavigationUtil;
import testconfigs.testdata.TestData;
import testconfigs.testdata.TestDataProvider;
import testconfigs.testdatamanagers.TestDataManager;
import testutils.Listeners.LogListener;
import utils.RandomGenerator;

/**
 * Created by Oleksii on 31.07.2017.
 */
@Listeners(LogListener.class)
public class Test_Pos_SendMessageWithAlias extends BaseTestClass {


    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "getPermanentTestSites", groups = {"send push", "advanced settings", "alias"})
    public void sendMessageWithAliasTest(String testSiteUrl) throws Exception {

        String title = "PUSH TITLE: " + RandomGenerator.nextString();
        String text = "ALIAS";
        String alias =  new TestDataManager().getAlias();

        new UserActions(driver).addNewAlias(testSiteUrl, alias);

        new LogInPage(driver).login(TestData.email, TestData.pass);
        CreateCampaignPage.AdvancedOptions advancedOptions = new NavigationUtil(driver).open(CreateCampaignPage.class, testSiteUrl)
                .setTitle(title)
                .setText(text)
                .openAdvancedOptions();
        System.out.println("title: " + title);
        advancedOptions.addAliasToCampaign(alias);

        CampaignReportPage campaignReportPage = new CreateCampaignPage(driver).sendPush().openMessage(title);
        Assert.assertEquals(alias, campaignReportPage.getSentAlias().getText());
    }
}
