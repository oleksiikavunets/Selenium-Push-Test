package tests.sendcampaigntests;

import actions.BrowserMaster;
import common.BaseTestClass;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.CampaignHistoryPage;
import pageobjects.CreateCampaignPage;
import pageobjects.LogInPage;
import pageutils.NavigationUtil;
import sikuli.PushHandler;
import testconfigs.testdata.TestData;
import testconfigs.testdata.TestDataProvider;
import testutils.Listeners.LogListener;

import static org.testng.Assert.assertEquals;

/**
 * Created by Oleksii on 03.07.2017.
 */
@Listeners(LogListener.class)
public class Test_Pos_SendMessage extends BaseTestClass {


    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "getPermanentTestSites", groups = {"send push"})
    public void sendMessageTest(String testSite) {

        BrowserMaster browser = new BrowserMaster(driver);
        PushHandler pushHandler = new PushHandler(driver);
        String title = "SIMPLE PUSH";
        String text = "SIMPLE PUSH";

        new LogInPage(driver).login(TestData.email, TestData.pass);
        CampaignHistoryPage campaignHistoryPage = new NavigationUtil(driver).open(CreateCampaignPage.class, testSite)
                .setTitle(title)
                .setText(text)
                .sendPush();

        Assert.assertTrue(campaignHistoryPage.verifyMessageExists(title), "Could not find sent message");
        Assert.assertNotNull(pushHandler.verifyPushReceived());
        int numOfWindows = browser.getNumberOfWindows();
        pushHandler.clickOnPush();
        assertEquals(pushHandler.getPushRedirectUrl(numOfWindows), testSite + "/");
    }
}
