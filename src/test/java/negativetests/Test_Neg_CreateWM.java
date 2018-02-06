import actions.UserActions;
import actions.Verifier;
import com.selenium.utils.RandomGenerator;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.CreateWMPage;
import pageobjects.HeaderMenu;
import pageobjects.WelcomeMessagePage;
import pageutils.Navigator;
import testdata.ErrorMessages;
import testdata.TestData;
import testutils.Listeners.LogListener;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Oleksii on 27.06.2017.
 */
@Listeners(LogListener.class)
public class Test_Neg_CreateWM extends BaseTestClass {


    @Test(groups = { "negative", "WM" })
    public void createWMnegativeTest() throws Exception {
        HeaderMenu headerMenu = new HeaderMenu(driver);
//        SideBar sideBar = new SideBar(driver);
        Navigator navigator = new Navigator(driver);


        ErrorMessages errorMessages = new ErrorMessages();
        UserActions userActions = new UserActions(driver);
        Verifier verifier = new Verifier();
        String siteUrl = TestData.newHttpSitePattern  + RandomGenerator.nextString() + ".com";
        String siteLang;

        HashMap<String, String> requiredField = errorMessages.getRequiredField();
        HashMap<String, String> invalidLinkFormat = errorMessages.getInvalidLinkFormat();

        userActions.createSite(TestData.email, TestData.pass, siteUrl);
        WelcomeMessagePage welcomeMessagePage = navigator.open(WelcomeMessagePage.class, siteUrl);
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

        verifier.assertTestPassed();

    }

}
