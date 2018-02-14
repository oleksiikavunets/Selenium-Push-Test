package tests.sendcampaigntests;

import com.selenium.utils.RandomGenerator;
import common.BaseTestClass;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.CampaignHistoryPage;
import pageobjects.CampaignReportPage;
import pageobjects.CreateCampaignPage;
import pageobjects.LogInPage;
import pageutils.Navigator;
import testconfigs.testdata.TestData;
import testconfigs.testdata.TestDataProvider;
import testutils.Listeners.LogListener;

@Listeners(LogListener.class)
public class Test_Pos_DeleteDelayedMessage extends BaseTestClass {

    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "getPermanentTestSites", groups = {"send push", "delayed push"})
    public void deleteDelayedMessageTest(String testSiteUrl) {
        Navigator navigator = new Navigator(driver);

        String title = RandomGenerator.nextString();
        String text = RandomGenerator.nextString();

        new LogInPage(driver).login(TestData.email, TestData.pass);
        CampaignReportPage campaignReportPage = navigator.open(CreateCampaignPage.class, testSiteUrl)
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
