package tests.sendcampaigntests;

import actions.Verifier;
import utils.RandomGenerator;
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
import testconfigs.testrestrictions.BetaFeatures;
import testutils.Listeners.LogListener;

@Listeners(LogListener.class)
public class Test_Pos_SendMessageWithUTM extends BaseTestClass {

    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "getPermanentTestSites", groups = {"send push", "UTM"})
    public void sendMessageWithUTMTest(String testSiteUrl) throws InterruptedException {
        NavigationUtil navigationUtil = new NavigationUtil(driver);

        Verifier verifier = new Verifier();
        String title = "PUSH TITLE: " + RandomGenerator.nextString();
        String text = "UTM";
        String utm_campaign = "campaign";

        if (BetaFeatures.verifyBetaToTest("UTM")) {
            new LogInPage(driver).login(TestData.email, TestData.pass);

            SiteSettingsPage siteSettingsPage = navigationUtil.open(SiteSettingsPage.class, testSiteUrl);

            String utm_source = siteSettingsPage.getUtm_source();
            String utm_medium = siteSettingsPage.getUtm_medium();

            CampaignReportPage campaignReportPage = navigationUtil.open(CreateCampaignPage.class, testSiteUrl)
                    .setTitle(title)
                    .setText(text)
                    .setUTMcampaign("campaign")
                    .sendPush()
                    .openMessage(title);
            verifier.assertEquals(campaignReportPage.getUTM_source(), utm_source);
            verifier.assertEquals(campaignReportPage.getUTM_medium(), utm_medium);
            verifier.assertEquals(campaignReportPage.getUTM_campaign(), utm_campaign);
        }
        verifier.assertTestPassed();
    }
}

