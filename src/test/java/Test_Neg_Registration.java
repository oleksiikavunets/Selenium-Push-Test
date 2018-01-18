import actions.Verifier;
import pageutils.Navigator;
import testutils.Listeners.LogListener;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.HeaderMenu;
import pageobjects.LogInPage;
import pageobjects.RegistrationPage;
import testdata.ErrorMessages;
import testdata.TestData;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Oleksii on 31.07.2017.
 */

@Listeners(LogListener.class)
public class Test_Neg_Registration extends BaseTestClass {


    @Test(groups = {"negative"})
    public void registrationNegative() throws Exception {
        Navigator navigator = new Navigator(driver);
        HeaderMenu headerMenu = new HeaderMenu(driver);
        LogInPage logInPage = new LogInPage(driver);
        ErrorMessages errorMessages = new ErrorMessages();
        Verifier verifier = new Verifier();
        HashMap<String, String> invalidEmailFormat = errorMessages.getInvalidEmailFormat();
        HashMap<String, String> smallPasword = errorMessages.getSmallPasword();
        HashMap<String, String> passNotMatch = errorMessages.getPassNotMatch();
        HashMap<String, String> emailxists = errorMessages.getEmailxists();

        String siteLang;

        logInPage.login(TestData.email, TestData.pass);
        List<WebElement> langs = headerMenu.getAvailableLanguages();
        langs.get(0).click();
        driver.navigate().refresh();
        siteLang = headerMenu.checkLanguage();


        for (int i = 1; i <= langs.size(); i++) {
            headerMenu.logout();
            RegistrationPage registrationPage = navigator.open(RegistrationPage.class)
                    .setEmail("admin@")
                    .setPass("1111");
            verifier.assertEquals(wait.until(ExpectedConditions.presenceOfElementLocated(registrationPage.invalidFormat)).getText(), invalidEmailFormat.get(siteLang));
            //int emailNumber = Integer.valueOf((String) prop.get("emailNumber"));
            registrationPage.clearEmail()
                    .setEmail(TestData.email);

            verifier.assertEquals(wait.until(ExpectedConditions.presenceOfElementLocated(registrationPage.invalidFormatPass)).getText(), smallPasword.get(siteLang));
            //checks error "Your password must be at least 8 characters long, must contain Latin symbols"

            registrationPage.clearPass()
                    .setPass(TestData.pass)
                    .repeatPass(TestData.pass2);
            verifier.assertEquals(wait.until(ExpectedConditions.presenceOfElementLocated(registrationPage.passDNTMatch)).getText(), passNotMatch.get(siteLang));
            //checks error "Passwords don't match"

            registrationPage.clearEmail()
                    .setEmail(TestData.email)
                    .clearPass()
                    .setPass(TestData.pass)
                    .clearRepeatPass()
                    .repeatPass(TestData.pass)
                    .submit();
            verifier.assertEquals(wait.until(ExpectedConditions.presenceOfElementLocated(registrationPage.errorExists)).getText(), emailxists.get(siteLang));
            //checks error "User email exists"
            if (i == langs.size()) break;
            logInPage.login(TestData.email, TestData.pass);

            headerMenu.switchLanguage(i);
            siteLang = headerMenu.checkLanguage();

        }
        verifier.assertTestPassed();
    }
}
