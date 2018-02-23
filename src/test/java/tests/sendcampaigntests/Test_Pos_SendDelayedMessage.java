package tests.sendcampaigntests;

import utils.RandomGenerator;
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

import java.time.LocalDateTime;


/**
 * Created by Oleksii on 31.07.2017.
 */
@Listeners(LogListener.class)
public class Test_Pos_SendDelayedMessage extends BaseTestClass {


    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "getPermanentTestSites", groups = {"send push", "delayed push"})
    public void sendDelayedMessageTest(String testSiteUrl){

        LocalDateTime date = LocalDateTime.now().plusDays(10);
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        int hour = date.getHour();
        int min = 0;

        String title = "PUSH TITLE: " + RandomGenerator.nextString();
        String text = "DELAYED PUSH";
        String siteUrl = "http://" + RandomGenerator.nextString() + ".com";

        new LogInPage(driver).login(TestData.email, TestData.pass);
        CampaignReportPage campaignReportPage = new NavigationUtil(driver).open(CreateCampaignPage.class, testSiteUrl)
                .setTitle(title)
                .setText(text)
                .setUrlToRedirect(siteUrl)
                .setDateAndTime(year, month, day, hour, min) //int parameter plus days
                .sendPush()
                .openMessage(title);

        Assert.assertTrue(campaignReportPage.verifyMessageDelayed());
    }
}
