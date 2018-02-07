package tests.sendcampaigntests;

import common.BaseTestClass;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.CampaignHistoryPage;
import pageobjects.CreateCampaignPage;
import pageobjects.LogInPage;
import pageutils.Navigator;
import sikuli.PushHandler;
import testconfigs.testdata.TestData;
import testconfigs.testdata.TestDataProvider;
import testutils.Listeners.LogListener;

import static actions.Timer.waitSeconds;

/**
 * Created by Oleksii on 03.07.2017.
 */
@Listeners(LogListener.class)
public class Test_Pos_SendMessage extends BaseTestClass {


    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "testSiteProvider", groups = {"send push"})
    public void sendMessageTest(String testSite){

        PushHandler pushHandler = new PushHandler();
        String title = "PUSH TITLE";
        String text = "PUSH TEXT";

        new LogInPage(driver).login(TestData.email, TestData.pass);
        CampaignHistoryPage campaignHistoryPage = new Navigator(driver).open(CreateCampaignPage.class, testSite)
                .setTitle(title)
                .setText(text)
                .sendPush();

        Assert.assertTrue(campaignHistoryPage.verifyMessageExists(title), "Could not find sent message");
        Assert.assertNotNull(pushHandler.verifyPushReceived());
        pushHandler.clickOnPush();
        waitSeconds(5);
    }
}
