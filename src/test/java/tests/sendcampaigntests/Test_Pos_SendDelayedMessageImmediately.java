package tests.sendcampaigntests;

import com.selenium.utils.RandomGenerator;
import common.BaseTestClass;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.CampaignReportPage;
import pageobjects.CreateCampaignPage;
import pageobjects.LogInPage;
import pageutils.Navigator;
import testconfigs.testdata.TestData;
import testutils.Listeners.LogListener;

import java.awt.*;

@Listeners(LogListener.class)
public class Test_Pos_SendDelayedMessageImmediately extends BaseTestClass {

    @Test(dataProvider = "testSiteProvider", groups = {"send push", "delayed push"})
    public void sendDelayedMessageImmediatelyTest(String testSiteUrl) throws AWTException {

        String title = RandomGenerator.nextString();
        String text = RandomGenerator.nextString();

        new LogInPage(driver).login(TestData.email, TestData.pass);
        CampaignReportPage campaignReportPage = new Navigator(driver).open(CreateCampaignPage.class, testSiteUrl)
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

    @DataProvider(name = "testSiteProvider")
    public Object[] provideTestSites() {
        return TestData.provideTestSites();
    }


}
