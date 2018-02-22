package tests.sendcampaigntests;

import actions.Timer;
import actions.Verifier;
import com.selenium.utils.RandomGenerator;
import common.BaseTestClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.CreateCampaignPage;
import pageobjects.LogInPage;
import pageutils.NavigationUtil;
import sikuli.PushHandler;
import testconfigs.baseconfiguration.TestServerConfiguretion;
import testconfigs.testdata.TestData;
import testconfigs.testdata.TestDataProvider;
import testconfigs.testrestrictions.BetaFeatures;
import testutils.Listeners.LogListener;

@Listeners(LogListener.class)
public class Test_Pos_SendMessageWithButtons extends BaseTestClass {

    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "getPermanentTestSites", groups = {"send push", "active elements", "buttons"})
    public void sendMessageWithButtonsTest(String testSiteUrl) throws Exception {
        PushHandler pushHandler = new PushHandler(driver);
        Verifier verifier = new Verifier();
        String title = "PUSH TITLE: " + RandomGenerator.nextString();
        String text = "THERE MUST BE BUTTONS BELOW";
        String button1Name = "BTN 1";
        String button2Name = "BTN 2";
        String btnRedirect1 = testSiteUrl + "/b1";
        String btnRedirect2 = testSiteUrl + "/b2";

        if (BetaFeatures.verifyBetaToTest("buttonsAndBigImage")) {

            new LogInPage(driver).login(TestData.email, TestData.pass);
            CreateCampaignPage createCampaignPage = new NavigationUtil(driver).open(CreateCampaignPage.class, testSiteUrl)
                    .setTitle(title)
                    .setText(text);

            createCampaignPage.openAdditionalActiveItems()
                    .setButtons(button1Name, btnRedirect1,
                            button2Name, btnRedirect2)
                    .setButton1Icon()
                    .setButton2Icon();

            Timer.waitSeconds(1);

            CreateCampaignPage.NotificationPreview notificationPreview = createCampaignPage.new NotificationPreview();

            verifier.assertNotNull(notificationPreview.getButton1Preview(), "Button 1 is not displayed in Notification Preview");
            if (notificationPreview.getButton1Preview() != null) {
                verifier.assertEquals(notificationPreview.getButton1Preview().getText(), button1Name, "Incorrect name of Button 1 in Notification Preview");
            }
            verifier.assertNotNull(notificationPreview.getButton2Preview(), "Button 1 is not displayed in Notification Preview");
            if (notificationPreview.getButton2Preview() != null) {
                verifier.assertEquals(notificationPreview.getButton2Preview().getText(), button2Name, "Incorrect name of Button 2 in Notification Preview");
            }
            createCampaignPage.sendPush().openMessage(title).copyCampaign();

            /*int numOfWindows = driver.getWindowHandles().size();
            pushHandler.clickSecondButton();
            Assert.assertEquals(pushHandler.getPushRedirectUrl(numOfWindows), btnRedirect2);

            Timer.waitSeconds(5);
            */
            verifier.assertEquals(notificationPreview.getTitlePreview().getText(), title, "Incorrect title in Notification Preview after copying");
            verifier.assertEquals(notificationPreview.getTextPreview().getText(), text, "Incorrect text in Notification Preview after copying");
            verifier.assertNotNull(notificationPreview.getButton1Preview(), "Button 1 is not displayed in Notification Preview after copying push");
            if (notificationPreview.getButton1Preview() != null) {
                verifier.assertEquals(notificationPreview.getButton1Preview().getText(), button1Name, "Incorrect name of Button 1 in Notification Preview after copying push");
            }
            verifier.assertNotNull(notificationPreview.getButton2Preview(), "Button 1 is not displayed in Notification Preview after copying push");
            if (notificationPreview.getButton2Preview() != null) {
                verifier.assertEquals(notificationPreview.getButton2Preview().getText(), button2Name, "Incorrect name of Button 2 in Notification Preview after copying push");
            }
        } else {
            System.out.println("Test: COPY CAMPAIGN> " +
                    "Current functionality is not deployed on " + TestServerConfiguretion.iTest + " yet");
        }
        verifier.assertTestPassed();
    }
}
