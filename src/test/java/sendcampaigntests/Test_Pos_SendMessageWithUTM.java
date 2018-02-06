package sendcampaigntests;

import actions.Verifier;
import common.BaseTestClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.CampaignReportPage;
import pageobjects.CreateCampaignPage;
import pageobjects.LogInPage;
import pageobjects.SiteSettingsPage;
import pageutils.Navigator;
import testdata.TestData;
import testrestrictions.BetaFeatures;
import testutils.Listeners.LogListener;

@Listeners(LogListener.class)
public class Test_Pos_SendMessageWithUTM extends BaseTestClass {

    @Test(dataProvider = "testSiteProvider", groups = {"send push", "UTM"})
    public void sendMessageWithUTMTest(String testSiteUrl) throws InterruptedException {
        Navigator navigator = new Navigator(driver);

        Verifier verifier = new Verifier();
        String title = TestData.pushTitle;
        String utm_campaign = "campaign";

        if (BetaFeatures.verifyBetaToTest("UTM")) {
            new LogInPage(driver).login(TestData.email, TestData.pass);

            SiteSettingsPage siteSettingsPage = navigator.open(SiteSettingsPage.class, testSiteUrl);

            String utm_source = siteSettingsPage.getUtm_source();
            String utm_medium = siteSettingsPage.getUtm_medium();

            CampaignReportPage campaignReportPage = navigator.open(CreateCampaignPage.class, testSiteUrl)
                    .setTitle(title)
                    .setText("UTM test")
                    .setUTMcampaign("campaign")
                    .sendPush()
                    .openMessage(title);
            verifier.assertEquals(campaignReportPage.getUTM_source(), utm_source);
            verifier.assertEquals(campaignReportPage.getUTM_medium(), utm_medium);
            verifier.assertEquals(campaignReportPage.getUTM_campaign(), utm_campaign);
        }
        verifier.assertTestPassed();
    }

    @DataProvider(name = "testSiteProvider")
    public Object[] provideTestSites() {
        return TestData.provideTestSites();
    }

}

