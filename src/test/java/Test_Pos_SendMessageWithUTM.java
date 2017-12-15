import actions.Verifier;
import com.selenium.ConfigTest;
import testutils.Listeners.LogListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.*;
import testdata.TestData;
import testrestrictions.BetaFeatures;

@Listeners(LogListener.class)
public class Test_Pos_SendMessageWithUTM extends BaseTestClass {

    @Test(groups = {"send push", "UTM"})
    public void sendMessageWithUTM() throws InterruptedException {

        Verifier verifier = new Verifier();
        String title = TestData.pushTitle;
        String utm_campaign = "campaign";

        if (BetaFeatures.verifyBetaToTest("UTM")) {
            SiteSettingsPage siteSettingsPage = new LogInPage(driver).login(TestData.email, TestData.pass)
                    .openSite(new ConfigTest().getTestSiteUrl())
                    .openSiteSettingsPage();

            String utm_source = siteSettingsPage.getUtm_source();
            String utm_medium = siteSettingsPage.getUtm_medium();

            CampaignReportPage campaignReportPage = new SideBar(driver).openCreateCampaignPage()
                    .setTitle(title).setText("UTM test").setUTMcampaign("campaign")
                    .sendPush().openMessage(title);
            verifier.assertEquals(campaignReportPage.getUTM_source(), utm_source);
            verifier.assertEquals(campaignReportPage.getUTM_medium(), utm_medium);
            verifier.assertEquals(campaignReportPage.getUTM_campaign(), utm_campaign);
        }
        verifier.assertTestPassed();
    }
}

