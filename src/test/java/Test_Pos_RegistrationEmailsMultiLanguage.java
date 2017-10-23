import actions.Verifier;
import com.selenium.ConfigTest;
import com.selenium.MailService;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pageobjects.HeaderMenu;
import pageobjects.LogInPage;
import pageobjects.RegistrationPage;
import testdata.TestData;

import java.io.IOException;
import java.util.List;

public class Test_Pos_RegistrationEmailsMultiLanguage extends SeleniumBaseClass {

    @Test(groups = {"mails", "registration"})
    public void testRegistrationMails() throws IOException {
        LogInPage logInPage = new LogInPage(driver, wait);
        HeaderMenu headerMenu = new HeaderMenu(driver, wait);
        RegistrationPage register = new RegistrationPage(driver, wait);
        ConfigTest config = new ConfigTest();
        Verifier verifier = new Verifier();


        String pass = config.getPassword();
        String siteLang;
        if (pass.equals("qqqq1111")) pass = "tttt1111";
        logInPage.login(TestData.email, TestData.pass);
        List<WebElement> langs = headerMenu.getAvailableLanguages();
        langs.get(0).click();
        driver.navigate().refresh();
        siteLang = headerMenu.checkLanguage();
        headerMenu.logout();
        for (int i = 1; i <= langs.size(); i++) {

            try {
                logInPage.clickRegister();
                int emailNumber = Integer.valueOf(config.getEmailNumber());
                register.setUserCridentials("grovitek+" + emailNumber + "@gmail.com", pass);
                String message = MailService.getRegistrationMail();
                emailNumber = emailNumber + 2;
                config.setEmailNumber(emailNumber);
                config.setPassword(pass);
                System.out.println(message);
                verifier.assertTrue(verifier.verifyRegistrationMail(message, ConfigTest.iTest, siteLang));
                String link = "https://" + message.split(" https://")[1].split("\\n")[0];
                System.out.println(link);
                driver.navigate().to(link);
            } catch (Exception e) {
                e.printStackTrace();
            }

            int emailNumber = Integer.valueOf(config.getEmailNumber())-2;
            pass = config.getPassword();
            logInPage.login("grovitek+" + emailNumber + "@gmail.com", pass);
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
