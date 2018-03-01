package tests.sendcampaigntests;

import common.BaseTestClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.CampaignReportPage;
import pageobjects.CreateCampaignPage;
import pageobjects.LogInPage;
import pageutils.NavigationUtil;
import testconfigs.testdata.TestData;
import testconfigs.testdata.TestDataProvider;
import utils.RandomGenerator;

public class Test_Pos_SendDelayedMessageWithButtonsImmediately extends BaseTestClass {

    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "getPermanentTestSites", groups = {"send push", "delayed push"})
    public void sendDelayedMessageWithButtonsImmediatelyTest(String testSite) {

        String title = "PUSH TITLE: " + RandomGenerator.nextString();
        String text = "DELAYED PUSH WITH BUTTONS";

        new LogInPage(driver).login(TestData.email, TestData.pass);
        new NavigationUtil(driver).open(CreateCampaignPage.class, testSite)
                .setDateAndTime(10, 0, 0)
                .setTitle(title)
                .setText(text)
        .openAdditionalActiveItems()
                .setButtons("Button1", "https://push.gravitec.net:7700/b1",
                        "Button2", "https://push.gravitec.net:7700/b2")
                .setButton1Icon()
                .setButton2Icon();
        CampaignReportPage campaignReportPage = new CreateCampaignPage(driver).sendPush()
                .openMessage(title);
        campaignReportPage.verifyMessageDelayed();
        campaignReportPage.clickEditCampaign()
                .cancelDate()
                .clickSendPush();
        Assert.assertFalse(campaignReportPage.verifyMessageDelayed(), "Message was not sent");
    }
}
