import actions.Timer;
import actions.UserActions;
import actions.Verifier;
import com.selenium.ConfigTest;
import com.selenium.utils.Log;
import com.selenium.utils.RandomGenerator;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.CreateWMPage;
import pageobjects.SideBar;
import pageobjects.WelcomeMessagePage;
import testdata.TestData;
import testrestrictions.BetaFeatures;
import testutils.Listeners.LogListener;

@Listeners(LogListener.class)
public class Test_Pos_CreateWMWithButtons extends BaseTestClass {

    @Test(groups = {"WM"})
    public void createWMWithButtonsPos() throws Exception {
        UserActions userActions = new UserActions(driver);
        WelcomeMessagePage welcomeMessagePage = new WelcomeMessagePage(driver);
        SideBar sideBar = new SideBar(driver);
        Verifier verifier = new Verifier();
        String testSite = TestData.newSitePattern + RandomGenerator.nextString() + ".com";

        if (BetaFeatures.verifyBetaToTest("WMwithButtonsAndBigImage")) {
            userActions.createSite(TestData.email, TestData.pass, testSite);
            for (int i = 0; i <= 10; i++) {

                sideBar.openWelcomeMessagePage().switchWM();
                CreateWMPage createWMPage = welcomeMessagePage.clickCreateNewWM()
                        .setTitle(TestData.welcomeMessageTitle).setText(TestData.welcomeMessageText);

                createWMPage.openAdditionalActiveItems()
                        .setButtons(TestData.button1Name, TestData.testSite, TestData.button2Name, TestData.testSite);
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
                    sideBar.openSiteSettingsPage();
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
            Log.info(this.getClass().getSimpleName() + ": Current funtionality is not deployed on " + ConfigTest.iTest);
            throw new SkipException("Skipped");
        }
    }
}

