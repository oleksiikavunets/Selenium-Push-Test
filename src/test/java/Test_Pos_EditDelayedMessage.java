import actions.Verifier;
import pageutils.Navigator;
import testutils.Listeners.LogListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.*;
import testdata.TestData;

import java.awt.*;

@Listeners(LogListener.class)
public class Test_Pos_EditDelayedMessage extends BaseTestClass {

    @Test(groups = {"send push", "delayed push"})
    public void editDelayedMessage() throws AWTException {
        Navigator navigator = new Navigator(driver);
        Verifier verifier = new Verifier();
        String title = TestData.pushTitle;
        String text = TestData.pushText;
        String utm_campaign = TestData.utm_campaign;

        String edit = "new";
        String newTitle = title + edit;
        String newText = text + edit;
        String urlToRedirect = TestData.testSite + "/" + edit;


        new LogInPage(driver).login(TestData.email, TestData.pass);

        SiteSettingsPage siteSettingsPage = navigator.open(SiteSettingsPage.class, TestData.testSite);

        String utm_source = siteSettingsPage.getUtm_source();
        String utm_medium = siteSettingsPage.getUtm_medium();

        CampaignReportPage campaignReportPage = navigator.open(CreateCampaignPage.class, TestData.testSite)
                .setTitle(title)
                .setText(text)
                .setUTMcampaign(utm_campaign)
                .setDateAndTime(10, 0, 0)
                .sendPush()
                .openMessage(title);

        campaignReportPage.verifyMessageDelayed();
        campaignReportPage.clickEditCampaign()
                .clearAllInputs()
                .setTitle(newTitle)
                .setText(newText)
                .setUrlToRedirect(urlToRedirect)
                .setUTMcampaign(edit)
                .clickSendPush();

        String newUrl = urlToRedirect + "?utm_source=" + utm_source + "&utm_medium=" + utm_medium + "&utm_campaign=" + utm_campaign + edit;

        verifier.assertEquals(campaignReportPage.getPushTitle(), newTitle, "Title was not edited");
        verifier.assertEquals(campaignReportPage.getPushText(), newText, "Text was not edited");
        verifier.assertEquals(campaignReportPage.getRedirectURL(), newUrl, "Redirect URL was not edited");
        verifier.assertEquals(campaignReportPage.getUTM_campaign(), utm_campaign + edit, "utm_campaign was not edited");
        verifier.assertTestPassed();

    }
}
