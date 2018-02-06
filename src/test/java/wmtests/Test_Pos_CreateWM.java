package wmtests1;

import common.BaseTestClass;
import actions.Timer;
import actions.UserActions;
import actions.Verifier;
import com.selenium.utils.RandomGenerator;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.CreateWMPage;
import pageobjects.SideBar;
import pageobjects.WelcomeMessagePage;
import pageutils.Navigator;
import testdata.TestData;
import testutils.Listeners.LogListener;

/**
 * Created by Oleksii on 31.07.2017.
 */

@Listeners(LogListener.class)
public class Test_Pos_CreateWM extends BaseTestClass {


    @Test(groups = {"WM"})
    public void createWMTest() throws Exception {
        Navigator navigator = new Navigator(driver);
        SideBar sideBar = new SideBar(driver);
        UserActions userActions = new UserActions(driver);
        Verifier verifier = new Verifier();
        String siteUrl = TestData.newHttpSitePattern + RandomGenerator.nextString() + ".com";


        userActions.createSite(TestData.email, TestData.pass, siteUrl);
        for (int i = 0; i <= 10; i++) {
            CreateWMPage createWMPage = navigator.open(WelcomeMessagePage.class, siteUrl)
                    .switchWM()
                    .clickCreateNewWM()
                    .setTitle(TestData.welcomeMessageTitle)
                    .setText(TestData.welcomeMessageText);

            verifier.assertEquals(createWMPage.getTitlePreview().getText(), TestData.welcomeMessageTitle, "Incorrect title on preview");
            verifier.assertEquals(createWMPage.getTextPreview().getText(), TestData.welcomeMessageText, "Incorrect text on preview");

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
        WelcomeMessagePage welcomeMessagePage = new WelcomeMessagePage(driver);
        verifier.assertTrue(welcomeMessagePage.getWMTitle().isDisplayed(), "Welcome message is not displayed on page");


        if (welcomeMessagePage.isDisabledWM()) {
            welcomeMessagePage.enableWM();
            verifier.assertTrue(welcomeMessagePage.isEnabledWM(), "Could not enable welcome message");
        }
        welcomeMessagePage.deleteWM().switchWM();
        Timer.waitSeconds(1);

        userActions.deleteSite(siteUrl);
        verifier.assertTestPassed();
    }
}
