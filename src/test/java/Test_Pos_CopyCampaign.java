import actions.Timer;
import actions.Verifier;
import com.selenium.ConfigTest;
import testutils.Listeners.LogListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.*;
import testdata.TestData;
import testrestrictions.BetaFeatures;

/**
 * Created by Oleksii on 31.07.2017.
 */

@Listeners(LogListener.class)
public class Test_Pos_CopyCampaign extends BaseTestClass {


    @Test(groups = {"send push", "copy campaign", "advanced settings", "tags", "alias", "active elements", "buttons", "big image"})
    public void copyCampain() throws Exception {
        Logger Log = LogManager.getLogger(Test_Pos_CopyCampaign.class);
        LogInPage logInPage = new LogInPage(driver);
        Verifier verifier = new Verifier();

        String testSite = TestData.testSite;
        String title = TestData.pushTitle;
        String text = TestData.pushText;
        String utm_source = "";
        String utm_medium = "";
        String utm_campaign = "campaign";
        String bigIMG = "";


        String url = TestData.url;
        String tag = TestData.tag;
        String browser = TestData.browser;
        String alias = TestData.alias;
        String country = TestData.country;
        String city = TestData.city;


        if (BetaFeatures.verifyBetaToTest("copyCampaign")) {
            MainAdminPage mainAdminPage = logInPage.login(TestData.email, TestData.pass);
            SideBar sideBar = mainAdminPage.openSite(testSite);

            if (BetaFeatures.verifyBetaToTest("UTM")) {
                SiteSettingsPage siteSettingsPage = sideBar.openSiteSettingsPage();
                Timer.waitSeconds(2);
                utm_source = wait.until(ExpectedConditions.visibilityOfElementLocated(siteSettingsPage.UTMsource)).getText();
                utm_medium = siteSettingsPage.UTMmedium.findElement(driver).getText();
            }
            CreateCampaignPage createCampaignPage = sideBar.openCreateCampaignPage();
            createCampaignPage.setTitle(title);
            createCampaignPage.setText(text);
            createCampaignPage.setUrlToRedirect(url);
            String newIcon = createCampaignPage.uploadIconToPush(TestData.icon);

            if (BetaFeatures.verifyBetaToTest("UTM")) {
                createCampaignPage.setUTMcampaign(utm_campaign);
                url = url + "?utm_source=" + utm_source + "&utm_medium=" + utm_medium + "&utm_campaign=" + utm_campaign;
            }

            if (BetaFeatures.verifyBetaToTest("buttonsAndBigImage")) { //not released on kyivstar yet
                CreateCampaignPage.AdditionalActiveItems activeItems = createCampaignPage.openAdditionalActiveItems();
                activeItems.setButtons("Button 1", "http://gravitec.at.ua/b1", "Button 2", "http://gravitec.at.ua/b2");
                bigIMG = activeItems.uploadBigImage(TestData.bigImage);
            }
            CreateCampaignPage.AdvancedOptions advancedOptions = createCampaignPage.openAdvancedOptions();
            advancedOptions.addTagToCampaign(tag);
            advancedOptions.addBrowserToCampaign(browser);//set browser before alias
            advancedOptions.addAliasToCampaign(alias);
            advancedOptions.addCountryToCampaign(country);
            advancedOptions.addCityToCampaign(city);
            CampaignHistoryPage campaignHistoryPage =  createCampaignPage.sendPush();

            CampaignReportPage campaignReportPage = campaignHistoryPage.openMessage(title);
            CreateCampaignPage.NotificationPreview notificationPreview = createCampaignPage.new NotificationPreview();
            for (int i = 1; i <= 3; i++) {
                campaignReportPage.copyCampaign();
                System.out.println("PUSH COPY " + i);
                Timer.waitSeconds(0.5);

                verifier.assertEquals(notificationPreview.getTitlePreview().getText(), title, "Failed on round: " + i);
                verifier.assertEquals(notificationPreview.getTextPreview().getText(), text, "Failed on round: " + i);
                verifier.assertEquals(notificationPreview.getIconPreview().getAttribute("src"), newIcon, "Failed icon preview assert on round: " + i);


                if (BetaFeatures.verifyBetaToTest("buttonsAndBigImage")) { //not released on kyivstar yet
                    verifier.assertNotNull(notificationPreview.getBigImagePreview(), "Failed big image preview on round: " + i);
                    if (notificationPreview.getBigImagePreview() != null) {
                        verifier.assertEquals(notificationPreview.getBigImagePreview().getAttribute("src"), bigIMG, "Failed on round: " + i);
                    }
                    verifier.assertNotNull(notificationPreview.getButton1Preview(), "Button 1 was not copied!" + "Failed on round: " + i);
                    if (notificationPreview.getButton1Preview() != null) {
                        verifier.assertEquals(notificationPreview.getButton1Preview().getText(), "Button 1", "Failed on round: " + i);
                    }
                    verifier.assertNotNull(notificationPreview.getButton2Preview(), "Button 2 was not copied!" + "Failed on round: " + i);
                    if (notificationPreview.getButton2Preview() != null) {
                        verifier.assertEquals(notificationPreview.getButton2Preview().getText(), "Button 2", "Failed on round: " + i);
                    }
                }

                //TO DO - finish encapsulation in CreateCampaign class
                /**
                verifier.assertEquals(createCampaignPage.tagsToSend.findElement(driver).getText(), tag, "Failed on round: " + i);
                verifier.assertEquals(createCampaignPage.aliasToSend.findElement(driver).getText(), alias, "Failed on round: " + i);
                verifier.assertEquals(createCampaignPage.browserToSend.findElement(driver).getText(), browser.toUpperCase(), "Failed on round: " + i);
                verifier.assertEquals(createCampaignPage.countryTosend.findElement(driver).getText(), country, "Failed on round: " + i);
                verifier.assertEquals(createCampaignPage.cityToSend.findElement(driver).getText(), city, "Failed on round: " + i);
                */

                createCampaignPage.sendPush();
                campaignHistoryPage.openMessage(title);
                verifier.assertEquals(campaignReportPage.getRedirectURL(), url);
                if (BetaFeatures.verifyBetaToTest("UTM")) {
                    verifier.assertEquals(campaignReportPage.getUTM_source(), utm_source, "Failed on round: " + i);
                    verifier.assertEquals(campaignReportPage.getUTM_medium(), utm_medium, "Failed on round: " + i);
                    verifier.assertEquals(campaignReportPage.getUTM_campaign(), utm_campaign, "Failed on round: " + i);
                }
            }
        } else if (!BetaFeatures.verifyBetaToTest("copyCampaign")) {
            System.out.println("Current funtionality is not deployed on " + ConfigTest.iTest);
            Log.info("Test: COPY CAMPAIGN\n " +
                    "Current funtionality is not deployed on " + ConfigTest.iTest);
        }
        verifier.assertTestPassed();
    }
}
