package wmtests1;

import actions.Timer;
import actions.UserActions;
import actions.Verifier;
import com.selenium.ConfigTest;
import com.selenium.utils.Log;
import com.selenium.utils.RandomGenerator;
import common.BaseTestClass;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.CreateWMPage;
import pageobjects.HeaderMenu;
import pageobjects.SiteSettingsPage;
import pageobjects.WelcomeMessagePage;
import pageutils.Navigator;
import testdata.TestData;
import testrestrictions.BetaFeatures;
import testutils.Listeners.LogListener;

import static testdata.TestData.welcomeMessageText;
import static testdata.TestData.welcomeMessageTitle;

@Listeners(LogListener.class)
public class Test_Pos_CreateWMWithBigImage extends BaseTestClass {

    @Test(groups = {"WM"})
    public void createWMWithBigImageTest() throws Exception {
        Navigator navigator = new Navigator(driver);
        UserActions userActions = new UserActions(driver);
        WelcomeMessagePage welcomeMessagePage = new WelcomeMessagePage(driver);
        Verifier verifier = new Verifier();
        String testSite = TestData.newHttpSitePattern + RandomGenerator.nextString() + ".com";

        if (BetaFeatures.verifyBetaToTest("WMwithButtonsAndBigImage")) {
            userActions.createSite(TestData.email, TestData.pass, testSite);

            for (int i = 0; i <= 10; i++) {
                CreateWMPage createWMPage = navigator.open(WelcomeMessagePage.class, testSite)
                        .switchWM()
                        .clickCreateNewWM()
                        .setTitle(welcomeMessageTitle)
                        .setText(welcomeMessageText);

                createWMPage.openAdditionalActiveItems()
                        .uploadBigImage(TestData.bigImage);

                verifier.assertTrue(createWMPage.getBigImagePreview().isDisplayed(), "Big image not found in notification preview");
                createWMPage.saveWM();
                Boolean siteIdError = userActions.handleErrorPopUp();
                if (siteIdError) {
                    String currentUrl = driver.getCurrentUrl();
                    String siteId = currentUrl.split("sites")[1].split("welcome-messages")[0];
                    verifier.assertNotEquals(siteId, "//", "No siteId in current URL: " + currentUrl);
                    navigator.open(SiteSettingsPage.class, testSite);
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
            new HeaderMenu(driver).logout();
            verifier.assertTestPassed();
        } else {
            Log.info(this.getClass().getSimpleName() + ": Current funtionality is not deployed on " + ConfigTest.iTest);
            throw new SkipException("Skipped");
        }
    }
}

