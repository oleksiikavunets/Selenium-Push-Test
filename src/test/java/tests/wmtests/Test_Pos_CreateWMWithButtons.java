package tests.wmtests;

import actions.Timer;
import actions.UserActions;
import actions.Verifier;
import utils.Log;
import common.BaseTestClass;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.CreateWMPage;
import pageobjects.SiteSettingsPage;
import pageobjects.WelcomeMessagePage;
import pageutils.NavigationUtil;
import testconfigs.baseconfiguration.TestServerConfiguretion;
import testconfigs.testdata.TestData;
import testconfigs.testdata.TestDataProvider;
import testconfigs.testrestrictions.BetaFeatures;
import testutils.Listeners.LogListener;

@Listeners(LogListener.class)
public class Test_Pos_CreateWMWithButtons extends BaseTestClass {

    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "getRandomSiteNames", groups = {"WM"})
    public void createWMWithButtonsTest(String testSite) throws Exception {
        NavigationUtil navigationUtil = new NavigationUtil(driver);
        UserActions userActions = new UserActions(driver);
        WelcomeMessagePage welcomeMessagePage = new WelcomeMessagePage(driver);
        Verifier verifier = new Verifier();

        if (BetaFeatures.verifyBetaToTest("WMwithButtonsAndBigImage")) {
            userActions.createSite(TestData.email, TestData.pass, testSite);
            for (int i = 0; i <= 10; i++) {

                CreateWMPage createWMPage = navigationUtil.open(WelcomeMessagePage.class, testSite)
                        .clickCreateNewWM()
                        .setTitle(TestData.welcomeMessageTitle)
                        .setText(TestData.welcomeMessageText);

                createWMPage.openAdditionalActiveItems()
                        .setButtons(TestData.button1Name, TestData.testSite, TestData.button2Name, testSite);
                verifier.assertEquals(createWMPage.getTitlePreview().getText(), TestData.welcomeMessageTitle, "Incorrect title on preview");
                verifier.assertEquals(createWMPage.getTextPreview().getText(), TestData.welcomeMessageText, "Incorrect text on preview");
                verifier.assertEquals(createWMPage.getButton1Preview().getText(), TestData.button1Name, "Incorrect button1 name on preview");
                verifier.assertEquals(createWMPage.getButton2Preview().getText(), TestData.button2Name, "Incorrect button2 name on preview");
                createWMPage.saveWM();
                Boolean siteIdError = userActions.handleErrorPopUp();
                if (siteIdError) {
                    String currentUrl = driver.getCurrentUrl();
                    String siteId = currentUrl.split("sites")[1].split("welcome-messages")[0];
                    verifier.assertNotEquals(siteId, "//", "No siteId in current URL: " + currentUrl);
                    navigationUtil.open(SiteSettingsPage.class, testSite);
                } else {
                    break;
                }
            }
            verifier.assertTrue(welcomeMessagePage.getWMTitle().isDisplayed(), "Welcome message is not displayed on page");
            if (welcomeMessagePage.isDisabledWM()) {
                welcomeMessagePage.enableWM();
                verifier.assertTrue(welcomeMessagePage.isEnabledWM(), "Could not enable welcome message");
            }
            welcomeMessagePage.switchWM();
            Timer.waitSeconds(1);

            userActions.deleteSite(testSite);
            verifier.assertTestPassed();
        } else {
            Log.info(this.getClass().getSimpleName() + ": Current funtionality is not deployed on " + TestServerConfiguretion.iTest);
            throw new SkipException("Skipped");
        }
    }
}

