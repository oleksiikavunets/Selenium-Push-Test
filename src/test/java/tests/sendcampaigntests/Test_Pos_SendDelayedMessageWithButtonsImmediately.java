package tests.sendcampaigntests;

import actions.Timer;
import com.selenium.utils.RandomGenerator;
import common.BaseTestClass;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.*;
import pageutils.Navigator;
import testconfigs.testdata.TestData;

public class Test_Pos_SendDelayedMessageWithButtonsImmediately extends BaseTestClass {

    @Test(dataProvider = "testSiteProvider", groups = {"send push", "delayed push"})
    public void sendDelayedMessageWithButtonsImmediatelyTest(String testSite) {

        String title = RandomGenerator.nextString();
        String text = RandomGenerator.nextString();

        new LogInPage(driver).login(TestData.email, TestData.pass);
        new Navigator(driver).open(CreateCampaignPage.class, testSite)
                .setTitle(title)
                .setText(text)
                .setDateAndTime(10, 0, 0)
                .openAdditionalActiveItems()
                .setButtons("Button1", "https://push.gravitec.net:7700/b1",
                        "Button2", "https://push.gravitec.net:7700/b2")
                .setButton1Icon()
                .setButton2Icon();
        Timer.waitSeconds(1);

        CampaignReportPage campaignReportPage = new CreateCampaignPage(driver).sendPush()
                .openMessage(title);
        campaignReportPage.verifyMessageDelayed();
        campaignReportPage.clickEditCampaign()
                .cancelDate()
                .clickSendPush();
        Assert.assertFalse(campaignReportPage.verifyMessageDelayed(), "Message was not sent");
    }

    @DataProvider(name = "testSiteProvider")
    public Object[] provideTestSites() {
        return TestData.provideTestSites();
    }


}