package tests.sendcampaigntests;

import actions.Timer;
import actions.Verifier;
import common.BaseTestClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.*;
import pageutils.Navigator;
import testconfigs.testdata.TestData;
import testconfigs.testrestrictions.BetaFeatures;
import testutils.Listeners.LogListener;

import static testconfigs.baseconfiguration.TestServerConfiguretion.iTest;


@Listeners(LogListener.class)
public class Test_Pos_CopyCampaignWithAdditionalActiveItems extends BaseTestClass {


    @Test(dataProvider = "testSiteProvider",  groups = {"send push", "copy campaign", "advanced settings", "tags", "alias", "active elements", "buttons", "big image"})
    public void copyCampaignWithAdditionalActiveItemsTest(String testSiteUrl) throws Exception {
        Logger Log = LogManager.getLogger(Test_Pos_CopyCampaignWithAdditionalActiveItems.class);
        Navigator navigator = new Navigator(driver);
        Verifier verifier = new Verifier();

        String title = TestData.pushTitle;
        String text = TestData.pushText;
        String utm_source = "";
        String utm_medium = "";
        String utm_campaign = "campaign";
        String bigIMG = "";


        String url = TestData.url;

        if (BetaFeatures.verifyBetaToTest("copyCampaign")) {
            new LogInPage(driver).login(TestData.email, TestData.pass);
            if (BetaFeatures.verifyBetaToTest("UTM")) {
                SiteSettingsPage siteSettingsPage = navigator.open(SiteSettingsPage.class, testSiteUrl);
                Timer.waitSeconds(2);
                utm_source = wait.until(ExpectedConditions.visibilityOfElementLocated(siteSettingsPage.UTMsource)).getText();
                utm_medium = siteSettingsPage.UTMmedium.findElement(driver).getText();
            }
            CreateCampaignPage createCampaignPage = navigator.open(CreateCampaignPage.class, testSiteUrl)
                    .setTitle(title)
                    .setText(text)
                    .setUrlToRedirect(url);
            String newIcon = createCampaignPage.uploadIconToPush(TestData.icon);

            if (BetaFeatures.verifyBetaToTest("UTM")) {
                createCampaignPage.setUTMcampaign(utm_campaign);
                url = url + "?utm_source=" + utm_source + "&utm_medium=" + utm_medium + "&utm_campaign=" + utm_campaign;
            }

            if (BetaFeatures.verifyBetaToTest("buttonsAndBigImage")) { //not released on kyivstar yet
                CreateCampaignPage.AdditionalActiveItems activeItems = createCampaignPage.openAdditionalActiveItems()
                        .setButtons("Button 1", "http://gravitec.at.ua/b1",
                                "Button 2", "http://gravitec.at.ua/b2");
                bigIMG = activeItems.uploadBigImage(TestData.bigImage);
            }

            CampaignHistoryPage campaignHistoryPage = createCampaignPage.sendPush();

            CampaignReportPage campaignReportPage = campaignHistoryPage.openMessage(title);
            CreateCampaignPage.NotificationPreview notificationPreview = createCampaignPage.new NotificationPreview();
            for (int i = 1; i <= 3; i++) {
                campaignReportPage.copyCampaign();
                Timer.waitSeconds(0.5);

                verifier.assertEquals(notificationPreview.getTitlePreview().getText(), title, "Push title assertion failed. Loop NO# " + i);
                verifier.assertEquals(notificationPreview.getTextPreview().getText(), text, "Push text assertion failed. Loop NO# " + i);
                verifier.assertEquals(notificationPreview.getIconPreview().getAttribute("src"), newIcon, "Failed icon preview assertion on round: " + i);

                if (BetaFeatures.verifyBetaToTest("buttonsAndBigImage")) {
                    verifier.assertNotNull(notificationPreview.getBigImagePreview(), "Big image was not found in push preview. Loop NO# " + i);
                    if (notificationPreview.getBigImagePreview() != null) {
                        verifier.assertEquals(notificationPreview.getBigImagePreview().getAttribute("src"), bigIMG, "Big image assertion failed. Loop NO# " + i);
                    }
                    verifier.assertNotNull(notificationPreview.getButton1Preview(), "Button 1 was not copied! Loop NO# " + i);
                    if (notificationPreview.getButton1Preview() != null) {
                        verifier.assertEquals(notificationPreview.getButton1Preview().getText(), "Button 1", "Button 1 assertion failed. Loop NO# " + i);
                    }
                    verifier.assertNotNull(notificationPreview.getButton2Preview(), "Button 2 was not copied! Loop NO# " + i);
                    if (notificationPreview.getButton2Preview() != null) {
                        verifier.assertEquals(notificationPreview.getButton2Preview().getText(), "Button 2", "Button 1 assertion failed. Loop NO# " + i);
                    }
                }

                createCampaignPage.sendPush();
                campaignHistoryPage.openMessage(title);
                verifier.assertEquals(campaignReportPage.getRedirectURL(), url);
                if (BetaFeatures.verifyBetaToTest("UTM")) {
                    verifier.assertEquals(campaignReportPage.getUTM_source(), utm_source, "Utm_source assertion failed. Loop NO# " + i);
                    verifier.assertEquals(campaignReportPage.getUTM_medium(), utm_medium, "Utm_medium assertion failed. Loop NO# " + i);
                    verifier.assertEquals(campaignReportPage.getUTM_campaign(), utm_campaign, "Utm_campaign assertion failed. Loop NO# " + i);
                }
            }
        } else if (!BetaFeatures.verifyBetaToTest("copyCampaign")) {
            System.out.println("Current functionality is not deployed on " + iTest);
            Log.info("Test: COPY CAMPAIGN\n " +
                    "Current functionality is not deployed on " + iTest);
        }
        verifier.assertTestPassed();
    }

    @DataProvider(name = "testSiteProvider")
    public Object[] provideTestSites() {
        return TestData.provideTestSites();
    }
}
