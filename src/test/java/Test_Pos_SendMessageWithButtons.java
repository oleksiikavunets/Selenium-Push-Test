import actions.Timer;
import actions.Verifier;
import com.selenium.ConfigTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import pageobjects.*;
import testdata.TestData;
import testrestrictions.BetaFeatures;

/**
 * Created by Oleksii on 31.07.2017.
 */
public class Test_Pos_SendMessageWithButtons extends SeleniumBaseClass {


    @Test(groups = {"send push", "active elements", "buttons"})
    public void sendMessageWithButtons() throws Exception {
        Logger Log = LogManager.getLogger(Test_Pos_SendMessageWithBigImage.class);
        LogInPage logInPage = new LogInPage(driver, wait);
        Verifier verifier  = new Verifier();
        String title = TestData.pushTitle;
        String text = TestData.pushText;

        if (BetaFeatures.verifyBetaToTest("buttonsAndBigImage")) {

            MainAdminPage mainAdminPage = logInPage.login(TestData.email, TestData.pass);
            SideBar sideBar = mainAdminPage.openSite(TestData.testSite);
            CreateCampaignPage createCampaignPage = sideBar.openCreateCampaignPage();

            createCampaignPage.setTitle(title);
            createCampaignPage.setText(text);
            CreateCampaignPage.AdditionalActiveItems buttons = createCampaignPage.openAdditionalActiveItems();

            buttons.setButtons("Button1", "https://push.gravitec.net:7700/b1", "Button2", "https://push.gravitec.net:7700/b2");
            buttons.setButton1Icon();
            buttons.setButton2Icon();
            Timer.waitSeconds(1);
            CreateCampaignPage.NotificationPreview notificationPreview = createCampaignPage.new NotificationPreview();
            verifier.assertNotNull(notificationPreview.getButton1Preview(), "Button 1 is not displayed in Notification Preview");
            if(notificationPreview.getButton1Preview() != null) {
                verifier.assertEquals(notificationPreview.getButton1Preview().getText(), "Button1", "Incorrect name of Button 1 in Notification Preview");
            }
            verifier.assertNotNull(notificationPreview.getButton2Preview(), "Button 1 is not displayed in Notification Preview");
            if(notificationPreview.getButton2Preview() != null) {
                verifier.assertEquals(notificationPreview.getButton2Preview().getText(), "Button2", "Incorrect name of Button 2 in Notification Preview");
            }
            CampaignHistoryPage campaignHistoryPage =  createCampaignPage.sendPush();

            CampaignReportPage campaignReportPage = campaignHistoryPage.openMessage(TestData.pushTitle);
            campaignReportPage.copyCampaign();

            verifier.assertEquals(notificationPreview.getTitlePreview().getText(), title, "Incorrect title in Notification Preview after copying");
            verifier.assertEquals(notificationPreview.getTextPreview().getText(), text, "Incorrect text in Notification Preview after copying");
            verifier.assertNotNull(notificationPreview.getButton1Preview(), "Button 1 is not displayed in Notification Preview after copying push");
            if(notificationPreview.getButton1Preview() != null) {
                verifier.assertEquals(notificationPreview.getButton1Preview().getText(), "Button1", "Incorrect name of Button 1 in Notification Preview after copying push");
            }
            verifier.assertNotNull(notificationPreview.getButton2Preview(), "Button 1 is not displayed in Notification Preview after copying push");
            if(notificationPreview.getButton2Preview() != null) {
                verifier.assertEquals(notificationPreview.getButton2Preview().getText(), "Button2", "Incorrect name of Button 2 in Notification Preview after copying push");
            }
            new HeaderMenu(driver, wait).logout();
        } else {
            Log.info("Test: COPY CAMPAIGN> " +
                    "Current functionality is not deployed on " + ConfigTest.iTest + " yet");
        }
        verifier.assertTestPassed();
    }
}
