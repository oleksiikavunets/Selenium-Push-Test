import actions.Verifier;
import com.selenium.ConfigTest;
import com.selenium.utils.Listener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.*;
import testdata.TestData;
import testrestrictions.BetaFeatures;

@Listeners(Listener.class)
public class Test_Pos_SendMessageWithUTM extends SeleniumBaseClass{

    @Test(groups = {"send push", "UTM"})
    public void sendMessageWithUTM() throws InterruptedException {
        LogInPage logInPage = new LogInPage(driver, wait);
        Verifier verifier = new Verifier();
        String title = TestData.pushTitle;
        String utm_campaign = "campaign";

        if (BetaFeatures.verifyBetaToTest("UTM")) {
            MainAdminPage mainAdminPage = logInPage.login(TestData.email, TestData.pass);
            SideBar sideBar = mainAdminPage.openSite(new ConfigTest().getTestSiteUrl());
            SiteSettingsPage siteSettingsPage = sideBar.openSiteSettingsPage();

            String utm_source = wait.until(ExpectedConditions.visibilityOfElementLocated(siteSettingsPage.UTMsource)).getText() ;
            String utm_medium = siteSettingsPage.UTMmedium.findElement(driver).getText();

            CreateCampaignPage createCampaignPage = sideBar.openCreateCampaignPage();
            createCampaignPage.setTitle(title);

            createCampaignPage.setText("UTM test");
            createCampaignPage.setUTMcampaign("campaign");
            CampaignHistoryPage campaignHistoryPage = createCampaignPage.sendPush();
            CampaignReportPage campaignReportPage = campaignHistoryPage.openMessage(title);
            verifier.assertEquals(campaignReportPage.getUTM_source(), utm_source);
            verifier.assertEquals(campaignReportPage.getUTM_medium(), utm_medium);
            verifier.assertEquals(campaignReportPage.getUTM_campaign(), utm_campaign);
            new HeaderMenu(driver, wait).logout();
        }
        verifier.assertTestPassed();
    }
}

