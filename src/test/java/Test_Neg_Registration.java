import actions.Verifier;
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
public class Test_Neg_Registration extends SeleniumBaseClass {


    @Test(groups = {"negative"})
    public void registrationNegative() throws Exception {
        HeaderMenu headerMenu = new HeaderMenu(driver, wait);
        LogInPage logInPage = new LogInPage(driver, wait);

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
        headerMenu.logout();

        for (int i = 1; i <= langs.size(); i++) {
            RegistrationPage registrationPage = logInPage.clickRegister();
            registrationPage.setEmail("admin@");
            registrationPage.setPass("1111");
            verifier.assertEquals(wait.until(ExpectedConditions.presenceOfElementLocated(registrationPage.invalidFormat)).getText(), invalidEmailFormat.get(siteLang));
            //int emailNumber = Integer.valueOf((String) prop.get("emailNumber"));
            driver.findElement(registrationPage.email).clear();
            registrationPage.setEmail(TestData.email);

            verifier.assertEquals(wait.until(ExpectedConditions.presenceOfElementLocated(registrationPage.invalidFormatPass)).getText(), smallPasword.get(siteLang));
            //checks error "Your password must be at least 8 characters long, must contain Latin symbols"

            registrationPage.clearPass();

            registrationPage.setPass(TestData.pass);
            registrationPage.repeatPass(TestData.pass2);
            verifier.assertEquals(wait.until(ExpectedConditions.presenceOfElementLocated(registrationPage.passDNTMatch)).getText(), passNotMatch.get(siteLang));
            //checks error "Passwords don't match"

            registrationPage.clearEmail();
            registrationPage.setEmail(TestData.email);
            registrationPage.clearPass();
            registrationPage.setPass(TestData.pass);
            registrationPage.clearRepeatPass();
            registrationPage.repeatPass(TestData.pass);
            registrationPage.submit();
            verifier.assertEquals(wait.until(ExpectedConditions.presenceOfElementLocated(registrationPage.errorExists)).getText(), emailxists.get(siteLang));
            //checks error "User email exists"
            if (i == langs.size()) break;
            logInPage.login(TestData.email, TestData.pass);

            headerMenu.switchLanguage(i);
            siteLang = headerMenu.checkLanguage();
            headerMenu.logout();
        }
        verifier.assertTestPassed();
    }
}
