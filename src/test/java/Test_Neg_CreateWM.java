import actions.UserActions;
import actions.Verifier;
import testutils.Listeners.LogListener;
import com.selenium.utils.RandomGenerator;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.CreateWMPage;
import pageobjects.HeaderMenu;
import pageobjects.SideBar;
import pageobjects.WelcomeMessagePage;
import testdata.ErrorMessages;
import testdata.TestData;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Oleksii on 27.06.2017.
 */
@Listeners(LogListener.class)
public class Test_Neg_CreateWM extends SeleniumBaseClass {


    @Test(groups = { "negative", "WM" })
    public void createWMnegative() throws Exception {
        HeaderMenu headerMenu = new HeaderMenu(driver, wait);
        SideBar sideBar = new SideBar(driver, wait);

        ErrorMessages errorMessages = new ErrorMessages();
        UserActions userActions = new UserActions(driver, wait);
        Verifier verifier = new Verifier();
        String siteUrl = "http://" + RandomGenerator.nextString() + ".com";
        String siteLang;

        HashMap<String, String> requiredField = errorMessages.getRequiredField();
        HashMap<String, String> invalidLinkFormat = errorMessages.getInvalidLinkFormat();

        userActions.createSite(TestData.email, TestData.pass, siteUrl);
        WelcomeMessagePage welcomeMessagePage = sideBar.openWelcomeMessagePage();
        List<WebElement> langs = headerMenu.getAvailableLanguages();
        langs.get(0).click();
        //driver.navigate().refresh();

        welcomeMessagePage.switchWM();

        CreateWMPage createWMPage = welcomeMessagePage.clickCreateNewWM();
        createWMPage.setURL("111");
        createWMPage.saveWM();

        for (int i = 1; i <= langs.size(); i++) {
            siteLang = headerMenu.checkLanguage();
            verifier.assertEquals(createWMPage.getRequiredTitleError().getText(), requiredField.get(siteLang));
            verifier.assertEquals(createWMPage.getRequiredTextError().getText(), requiredField.get(siteLang));
            verifier.assertEquals(createWMPage.getInvalidLinkError().getText(), invalidLinkFormat.get(siteLang));

            if (i == langs.size()) break;

            headerMenu.switchLanguage(i);
        }
        userActions.deleteSite(siteUrl);
        headerMenu.logout();
        verifier.assertTestPassed();

    }

}
