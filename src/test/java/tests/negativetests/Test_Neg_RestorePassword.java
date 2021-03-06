package tests.negativetests;

import actions.Verifier;
import common.BaseTestClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.HeaderMenu;
import pageobjects.LogInPage;
import pageobjects.RecoverPasswordPage;
import pageutils.NavigationUtil;
import testconfigs.testdata.ErrorMessages;
import testconfigs.testdata.TestData;
import testutils.Listeners.LogListener;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Oleksii on 31.07.2017.
 */

@Listeners(LogListener.class)
public class Test_Neg_RestorePassword extends BaseTestClass {

    @Test(groups = {"negative"})
    public void restorePasswordNegativeTest() throws Exception {
        NavigationUtil navigationUtil = new NavigationUtil(driver);
        HeaderMenu headerMenu = new HeaderMenu(driver);
        LogInPage logInPage = new LogInPage(driver);
        ErrorMessages errorMessages = new ErrorMessages();
        Verifier verifier = new Verifier();

        HashMap<String, String> emailExists = errorMessages.getEmailNotExists();

        String siteLang;

        logInPage.login(TestData.email, TestData.pass);
        List<WebElement> langs = headerMenu.getAvailableLanguages();
        langs.get(0).click();
        driver.navigate().refresh();
        siteLang = headerMenu.checkLanguage();


        for (int i = 1; i <= langs.size(); i++) {
            headerMenu.logout();
            RecoverPasswordPage recoverPasswordPage = navigationUtil.open(RecoverPasswordPage.class)
                    .setEmail(TestData.inValidEmail)
                    .clickResetButton();
            verifier.assertEquals(wait.until(ExpectedConditions.presenceOfElementLocated(recoverPasswordPage.errorMessage)).getText(), emailExists.get(siteLang));


            if (i == langs.size()) break;
            logInPage.login(TestData.email, TestData.pass);
            headerMenu.switchLanguage(i);
            siteLang = headerMenu.checkLanguage();

        }
        verifier.assertTestPassed();
    }
}
