import actions.Verifier;
import com.selenium.utils.Listener;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.HeaderMenu;
import pageobjects.LogInPage;
import pageobjects.RecoverPasswordPage;
import testdata.ErrorMessages;
import testdata.TestData;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Oleksii on 31.07.2017.
 */

@Listeners(Listener.class)
public class Test_Neg_RestorePassword extends SeleniumBaseClass {

    @Test(groups = { "negative" })
    public void restorePasswordNegative() throws Exception {
        HeaderMenu headerMenu = new HeaderMenu(driver, wait);
        LogInPage logInPage = new LogInPage(driver, wait);
        ErrorMessages errorMessages = new ErrorMessages();
        Verifier verifier = new Verifier();

        HashMap<String, String> emailExists = errorMessages.getEmailNotExists();

        String siteLang;

        logInPage.login(TestData.email, TestData.pass);
        List<WebElement> langs = headerMenu.getAvailableLanguages();
        langs.get(0).click();
        driver.navigate().refresh();
        siteLang = headerMenu.checkLanguage();
        headerMenu.logout();

        for (int i = 1; i <= langs.size(); i++) {
            RecoverPasswordPage recoverPasswordPage = logInPage.clickForgotPass();
            recoverPasswordPage.setEmail(TestData.inValidEmail);
            recoverPasswordPage.clickResetButton();
            verifier.assertEquals(wait.until(ExpectedConditions.presenceOfElementLocated(recoverPasswordPage.errorMessage)).getText(), emailExists.get(siteLang));


            if (i == langs.size()) break;
            logInPage.login(TestData.email, TestData.pass);
            headerMenu.switchLanguage(i);
            siteLang = headerMenu.checkLanguage();
            headerMenu.logout();
        }
        verifier.assertTestPassed();
    }
}
