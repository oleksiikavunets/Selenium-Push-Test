import actions.Verifier;
import com.selenium.ConfigTest;
import com.selenium.MailService;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pageobjects.*;
import testdata.TestData;

import java.util.List;

public class Test_Pos_RecoverPassMultiLanguage extends SeleniumBaseClass {

    @Test(groups = {"mails", "recover password"})
    public void testRecoverPasswordMail() throws Exception {
        LogInPage logInPage = new LogInPage(driver, wait);
        HeaderMenu headerMenu = new HeaderMenu(driver, wait);
        RecoverPasswordPage recoverPasswordPage = new RecoverPasswordPage(driver, wait);
        NewPasswordSetUpPage newPasswordSetUpPage = new NewPasswordSetUpPage(driver, wait);


        ConfigTest config = new ConfigTest();
        Verifier verifier = new Verifier();
        int emailNumber = Integer.valueOf(config.getEmailNumber()) - 2;
        String email = "grovitek+" + emailNumber + "@gmail.com";
        String newPass = config.getPassword();

        String siteLang;


        if (newPass.equals("tttt1111")) newPass = "qqqq1111";
        else if (newPass.equals("qqqq1111")) newPass = "tttt1111";

        logInPage.login(TestData.email, TestData.pass);
        List<WebElement> langs = headerMenu.getAvailableLanguages();
        langs.get(0).click();
        driver.navigate().refresh();
        siteLang = headerMenu.checkLanguage();
        headerMenu.logout();
        for (int i = 1; i <= langs.size(); i++) {

            logInPage.clickForgotPass();
            recoverPasswordPage.requestPasswordReset(email);
            String message = MailService.getRecoverPasswordMail();
            System.out.println(message);
            verifier.assertTrue(verifier.verifyRecoverPasswordMail(message, ConfigTest.iTest, siteLang));

            String link = "https://" + message.split("https://")[3].split("\\n")[0];
            driver.get(link);
            newPasswordSetUpPage.setNewPass(newPass);
            logInPage.login(email, newPass);
            config.setPassword(newPass);
            if (i == langs.size()) {
                headerMenu.logout();
                break;
            }
            headerMenu.switchLanguage(i);
            siteLang = headerMenu.checkLanguage();
            headerMenu.logout();
        }
        verifier.assertTestPassed();
    }
}