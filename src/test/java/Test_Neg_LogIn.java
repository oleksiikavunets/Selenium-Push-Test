//import actions.SoftAssert;
import actions.Timer;
import actions.Verifier;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.HeaderMenu;
import pageobjects.LogInPage;
import pageutils.Navigator;
import testdata.ErrorMessages;
import testdata.TestData;
import testutils.Listeners.LogListener;

import java.util.HashMap;
import java.util.List;

import static com.selenium.utils.TextGetter.textOf;

/**
 * Created by Oleksii on 31.07.2017.
 */

@Listeners(LogListener.class)
public class Test_Neg_LogIn extends BaseTestClass {

    @Test(groups = { "negative" })
    public void logInNegative() throws Exception {
        Navigator navigator = new Navigator(driver);
        HeaderMenu headerMenu = new HeaderMenu(driver);
        LogInPage logInPage = new LogInPage(driver);
        ErrorMessages errorMessages = new ErrorMessages();
        Verifier verifier = new Verifier();
//        SoftAssert softAssert = new SoftAssert();

        HashMap<String, String> incorrectEmail = errorMessages.getIncorrectEmail();
        HashMap<String, String> incorrectPassword = errorMessages.getIncorrectPassword();
        String siteLang;


        logInPage.login(TestData.email, TestData.pass);
        List<WebElement> langs = headerMenu.getAvailableLanguages();
        langs.get(0).click();
        driver.navigate().refresh();
        siteLang = headerMenu.checkLanguage();


        for (int i = 1; i <= langs.size(); i++) {
            headerMenu.logout();
            logInPage.setLogin(TestData.inValidEmail);
            logInPage.setPassword(TestData.invalidPass);
            logInPage.submit();
            verifier.assertEquals(textOf(logInPage.getErrorMessage()), incorrectEmail.get(siteLang));

            //checks error "Incorrect email"

            logInPage.clearLogin().setLogin(TestData.email).submit();
            Timer.waitSeconds(0.3);

            verifier.assertEquals(textOf(logInPage.getErrorMessage()), incorrectPassword.get(siteLang));

            //checks error "Incorrect password"

            if (i == langs.size()) break;
            logInPage.login(TestData.email, TestData.pass);

            headerMenu.switchLanguage(i);
            siteLang = headerMenu.checkLanguage();
        }
        verifier.assertTestPassed();
    }
}
