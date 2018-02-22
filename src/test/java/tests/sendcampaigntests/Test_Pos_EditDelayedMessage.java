package tests.sendcampaigntests;

import actions.Verifier;
import com.selenium.utils.RandomGenerator;
import common.BaseTestClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.CampaignReportPage;
import pageobjects.CreateCampaignPage;
import pageobjects.LogInPage;
import pageobjects.SiteSettingsPage;
import pageutils.NavigationUtil;
import testconfigs.testdata.TestData;
import testconfigs.testdata.TestDataProvider;
import testutils.Listeners.LogListener;

@Listeners(LogListener.class)
public class Test_Pos_EditDelayedMessage extends BaseTestClass {

    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "getPermanentTestSites", groups = {"send push", "delayed push"})
    public void editDelayedMessageTest(String testSiteUrl)  {
        NavigationUtil navigationUtil = new NavigationUtil(driver);
        Verifier verifier = new Verifier();
        String title = "PUSH TITLE: " + RandomGenerator.nextString();
        String text = "DELAYED PUSH TO BE EDITED.";
        String utm_campaign = TestData.utm_campaign;

        String edit = "new";
        String newTitle = title + edit;
        String newText = text + edit;
        String urlToRedirect = testSiteUrl + "/" + edit;


        new LogInPage(driver).login(TestData.email, TestData.pass);

        SiteSettingsPage siteSettingsPage = navigationUtil.open(SiteSettingsPage.class, testSiteUrl);

        String utm_source = siteSettingsPage.getUtm_source();
        String utm_medium = siteSettingsPage.getUtm_medium();

        CampaignReportPage campaignReportPage = navigationUtil.open(CreateCampaignPage.class, testSiteUrl)
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
