import actions.Verifier;
import testutils.Listeners.LogListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.*;
import testdata.TestData;

import java.awt.*;

@Listeners(LogListener.class)
public class Test_Pos_EditDelayedMessage extends SeleniumBaseClass {

    @Test(groups = {"send push", "delayed push"})
    public void editDelayedMessage() throws AWTException {
        Verifier verifier = new Verifier();

        LogInPage logInPage = new LogInPage(driver, wait);

        String title = TestData.pushTitle;
        String text = TestData.pushText;
        String utm_campaign = TestData.utm_campaign;

        String edit = "new";
        String newTitle = title + edit;
        String newText = text + edit;
        String urlToRedirect = TestData.testSite + "/" + edit;

         
        MainAdminPage mainAdminPage = logInPage.login(TestData.email, TestData.pass);
        SideBar sideBar = mainAdminPage.openSite(TestData.testSite);
        SiteSettingsPage siteSettingsPage = sideBar.openSiteSettingsPage();

        String utm_source = siteSettingsPage.getUtm_source();
        String utm_medium = siteSettingsPage.getUtm_medium();
        CreateCampaignPage createCampaignPage = sideBar.openCreateCampaignPage();

        createCampaignPage.setTitle(title);
        createCampaignPage.setText(text);
        createCampaignPage.setUTMcampaign(utm_campaign);
        createCampaignPage.setDateAndTime(10, 0, 0);
        CampaignHistoryPage campaignHistoryPage = createCampaignPage.sendPush();

        CampaignReportPage campaignReportPage = campaignHistoryPage.openMessage(title);
        campaignReportPage.verifyMessageDelayed();
        campaignReportPage.clickEditCampaign();
        createCampaignPage.clearAllInputs();
        createCampaignPage.setTitle(newTitle);
        createCampaignPage.setText(newText);
        createCampaignPage.setUrlToRedirect(urlToRedirect);
        createCampaignPage.setUTMcampaign(edit);
        createCampaignPage.clickSendPush();

        String newUrl = urlToRedirect + "?utm_source=" + utm_source + "&utm_medium=" + utm_medium + "&utm_campaign=" + utm_campaign + edit;


        verifier.assertEquals(campaignReportPage.getPushTitle(), newTitle, "Title was not edited");
        verifier.assertEquals(campaignReportPage.getPushText(), newText, "Text was not edited");
        verifier.assertEquals(campaignReportPage.getRedirectURL(), newUrl, "Redirect URL was not edited");
        verifier.assertEquals(campaignReportPage.getUTM_campaign(), utm_campaign + edit, "utm_campaign was not edited");
        verifier.assertTestPassed();

    }
}
