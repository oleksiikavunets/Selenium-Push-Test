import actions.Timer;
import actions.UserActions;
import actions.Verifier;
import testutils.Listeners.LogListener;
import com.selenium.utils.RandomGenerator;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.CreateWMPage;
import pageobjects.HeaderMenu;
import pageobjects.SideBar;
import pageobjects.WelcomeMessagePage;
import testdata.TestData;

/**
 * Created by Oleksii on 31.07.2017.
 */

@Listeners(LogListener.class)
public class Test_Pos_CreateWM extends SeleniumBaseClass {


    @Test(groups = {"WM"})
    public void createWM() throws Exception {
        SideBar sideBar = new SideBar(driver, wait);
        UserActions userActions = new UserActions(driver, wait);
        WelcomeMessagePage welcomeMessagePage = new WelcomeMessagePage(driver, wait);
        CreateWMPage createWMPage = new CreateWMPage(driver, wait);
        Verifier verifier = new Verifier();
        String siteUrl = "http://" + RandomGenerator.nextString() + ".com";


        userActions.createSite(TestData.email, TestData.pass, siteUrl);
        for (int i = 0; i <= 10; i++) {
            sideBar.openWelcomeMessagePage();

            welcomeMessagePage.switchWM();
            welcomeMessagePage.clickCreateNewWM();
            createWMPage.setTitle(TestData.welcomeMessageTitle);
            createWMPage.setText(TestData.welcomeMessageText);

            verifier.assertEquals(createWMPage.getTitlePreview().getText(), TestData.welcomeMessageTitle, "Incorrect title on preview");
            verifier.assertEquals(createWMPage.getTextPreview().getText(), TestData.welcomeMessageText, "Incorrect text on preview");

            createWMPage.saveWM();
            Boolean siteIdError = userActions.handleErrorPopUp();
            if (siteIdError) {
                String currentUrl = driver.getCurrentUrl();
                String siteId = currentUrl.split("sites")[1].split("welcome-messages")[0];
                verifier.assertNotEquals(siteId, "//", "No siteId in current URL: " + currentUrl);
                sideBar.openSiteSettingsPage();
            }else{
                break;
            }
        }
        verifier.assertTrue(welcomeMessagePage.getWMTitle().isDisplayed(), "Welcome message is not displayed on page");


        if (welcomeMessagePage.isDisabledWM()) {
            welcomeMessagePage.enableWM();
            verifier.assertTrue(welcomeMessagePage.isEnabledWM(), "Could not enable welcome message");
        }
        welcomeMessagePage.deleteWM();
        welcomeMessagePage.switchWM();
        Timer.waitSeconds(1);

        userActions.deleteSite(siteUrl);
        new HeaderMenu(driver, wait).logout();
        verifier.assertTestPassed();
    }
}
