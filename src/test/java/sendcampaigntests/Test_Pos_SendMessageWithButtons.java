package sendcampaigntests.scopehttp;

import actions.Timer;
import actions.Verifier;
import com.selenium.ConfigTest;
import common.BaseTestClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.CreateCampaignPage;
import pageobjects.LogInPage;
import pageutils.Navigator;
import testdata.TestData;
import testrestrictions.BetaFeatures;
import testutils.Listeners.LogListener;

@Listeners(LogListener.class)
public class Test_Pos_SendMessageWithButtons extends BaseTestClass {

    @Test(dataProvider = "testSiteProvider", groups = {"send push", "active elements", "buttons"})
    public void sendMessageWithButtonsTest(String testSiteUrl) throws Exception {
        Verifier verifier = new Verifier();
        String title = TestData.pushTitle;
        String text = TestData.pushText;

        if (BetaFeatures.verifyBetaToTest("buttonsAndBigImage")) {

            new LogInPage(driver).login(TestData.email, TestData.pass);
            CreateCampaignPage createCampaignPage = new Navigator(driver).open(CreateCampaignPage.class, testSiteUrl)
                    .setTitle(title)
                    .setText(text);

            createCampaignPage.openAdditionalActiveItems()
                    .setButtons("Button1", "https://push.gravitec.net:7700/b1",
                            "Button2", "https://push.gravitec.net:7700/b2")
                    .setButton1Icon()
                    .setButton2Icon();

            Timer.waitSeconds(1);

            CreateCampaignPage.NotificationPreview notificationPreview = createCampaignPage.new NotificationPreview();

            verifier.assertNotNull(notificationPreview.getButton1Preview(), "Button 1 is not displayed in Notification Preview");
            if (notificationPreview.getButton1Preview() != null) {
                verifier.assertEquals(notificationPreview.getButton1Preview().getText(), "Button1", "Incorrect name of Button 1 in Notification Preview");
            }
            verifier.assertNotNull(notificationPreview.getButton2Preview(), "Button 1 is not displayed in Notification Preview");
            if (notificationPreview.getButton2Preview() != null) {
                verifier.assertEquals(notificationPreview.getButton2Preview().getText(), "Button2", "Incorrect name of Button 2 in Notification Preview");
            }
            createCampaignPage.sendPush().openMessage(TestData.pushTitle).copyCampaign();

            verifier.assertEquals(notificationPreview.getTitlePreview().getText(), title, "Incorrect title in Notification Preview after copying");
            verifier.assertEquals(notificationPreview.getTextPreview().getText(), text, "Incorrect text in Notification Preview after copying");
            verifier.assertNotNull(notificationPreview.getButton1Preview(), "Button 1 is not displayed in Notification Preview after copying push");
            if (notificationPreview.getButton1Preview() != null) {
                verifier.assertEquals(notificationPreview.getButton1Preview().getText(), "Button1", "Incorrect name of Button 1 in Notification Preview after copying push");
            }
            verifier.assertNotNull(notificationPreview.getButton2Preview(), "Button 1 is not displayed in Notification Preview after copying push");
            if (notificationPreview.getButton2Preview() != null) {
                verifier.assertEquals(notificationPreview.getButton2Preview().getText(), "Button2", "Incorrect name of Button 2 in Notification Preview after copying push");
            }
        } else {
            System.out.println("Test: COPY CAMPAIGN> " +
                    "Current functionality is not deployed on " + ConfigTest.iTest + " yet");
        }
        verifier.assertTestPassed();
    }

    @DataProvider(name = "testSiteProvider")
    public Object[] provideTestSites() {
        return TestData.provideTestSites();
    }

}
