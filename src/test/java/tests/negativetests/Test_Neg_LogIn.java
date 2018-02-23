package tests.negativetests;//import actions.SoftAssert;

import actions.Timer;
import actions.Verifier;
import common.BaseTestClass;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.HeaderMenu;
import pageobjects.LogInPage;
import testconfigs.testdata.ErrorMessages;
import testconfigs.testdata.TestData;
import testutils.Listeners.LogListener;

import java.util.HashMap;
import java.util.List;

import static utils.TextUtil.textOf;

/**
 * Created by Oleksii on 31.07.2017.
 */

@Listeners(LogListener.class)
public class Test_Neg_LogIn extends BaseTestClass {

    @Test(groups = { "negative" })
    public void logInNegativeTest() throws Exception {
        HeaderMenu headerMenu = new HeaderMenu(driver);
        LogInPage logInPage = new LogInPage(driver);
        ErrorMessages errorMessages = new ErrorMessages();
        Verifier verifier = new Verifier();

        HashMap<String, String> incorrectEmail = errorMessages.getIncorrectEmail();
        HashMap<String, String> incorrectPassword = errorMessages.getIncorrectPassword();
        String siteLang;


        logInPage.login(TestData.email, TestData.pass);
        List<WebElement> langs = headerMenu.getAvailableLanguages();
        headerMenu.switchFirstLanguage();
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
