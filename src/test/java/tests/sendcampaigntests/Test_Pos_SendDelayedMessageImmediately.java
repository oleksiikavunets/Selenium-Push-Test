package tests.sendcampaigntests;

import com.selenium.utils.RandomGenerator;
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
import testutils.Listeners.LogListener;

@Listeners(LogListener.class)
public class Test_Pos_SendDelayedMessageImmediately extends BaseTestClass {

    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "getPermanentTestSites", groups = {"send push", "delayed push"})
    public void sendDelayedMessageImmediatelyTest(String testSiteUrl)  {

        String title = "PUSH TITLE: " + RandomGenerator.nextString();
        String text = "DELAYED PUSH TO BE SENT NOW";

        new LogInPage(driver).login(TestData.email, TestData.pass);
        CampaignReportPage campaignReportPage = new NavigationUtil(driver).open(CreateCampaignPage.class, testSiteUrl)
                .setTitle(title)
                .setText(text)
                .setDateAndTime(10, 0, 0)
                .sendPush()
                .openMessage(title);
        Assert.assertTrue(campaignReportPage.verifyMessageDelayed(), "Message is not delayed");
        campaignReportPage.clickEditCampaign()
                .cancelDate()
                .clickSendPush();
        Assert.assertFalse(campaignReportPage.verifyMessageDelayed(), "Message was not sent immediately");
    }
}
