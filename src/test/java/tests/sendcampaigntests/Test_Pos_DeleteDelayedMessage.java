package tests.sendcampaigntests;

import utils.RandomGenerator;
import common.BaseTestClass;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.CampaignHistoryPage;
import pageobjects.CampaignReportPage;
import pageobjects.CreateCampaignPage;
import pageobjects.LogInPage;
import pageutils.NavigationUtil;
import testconfigs.testdata.TestData;
import testconfigs.testdata.TestDataProvider;
import testutils.Listeners.LogListener;

@Listeners(LogListener.class)
public class Test_Pos_DeleteDelayedMessage extends BaseTestClass {

    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "getPermanentTestSites", groups = {"send push", "delayed push"})
    public void deleteDelayedMessageTest(String testSiteUrl) {
        NavigationUtil navigationUtil = new NavigationUtil(driver);

        String title = "PUSH TITLE: " + RandomGenerator.nextString();
        String text = "DELAYED PUSH TO BE DELETED.";

        new LogInPage(driver).login(TestData.email, TestData.pass);
        CampaignReportPage campaignReportPage = navigationUtil.open(CreateCampaignPage.class, testSiteUrl)
                .setTitle(title)
                .setText(text)
                .setDateAndTime(10, 0, 0)
                .sendPush()
                .openMessage(title);
        campaignReportPage.verifyMessageDelayed();
        CampaignHistoryPage campaignHistoryPage = campaignReportPage.cancelCampaign();

        Assert.assertFalse(campaignHistoryPage.verifyMessageExists(title), "Message was not deleted");
    }
}
