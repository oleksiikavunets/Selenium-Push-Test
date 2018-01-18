import actions.Verifier;
import com.selenium.ConfigTest;
import com.selenium.MailService;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.HeaderMenu;
import pageobjects.LogInPage;
import pageobjects.RegistrationPage;
import pageutils.Navigator;
import testdata.TestData;
import testutils.Listeners.LogListener;

import java.io.IOException;
import java.util.List;

import static com.selenium.enums.Server.P2B;
import static com.selenium.utils.NameGenerator.generateNewUserEmail;
import static testdatamanagers.TestUserManager.*;

@Listeners(LogListener.class)
public class Test_Pos_RegistrationEmailsMultiLanguage extends BaseTestClass {

    @Test(groups = {"mails", "registration"}, singleThreaded = true, threadPoolSize = 1)
    public void testRegistrationMails() throws IOException {

        LogInPage logInPage = new LogInPage(driver);
        HeaderMenu headerMenu = new HeaderMenu(driver);
        Verifier verifier = new Verifier();

        String pass = getPassword();
        String siteLang;
        if (pass.equals("qqqq1111")) pass = "tttt1111";
        logInPage.login(TestData.email, TestData.pass);
        List<WebElement> langs = headerMenu.getAvailableLanguages();
        langs.get(0).click();
        driver.navigate().refresh();
        siteLang = headerMenu.checkLanguage();
        headerMenu.logout();
        for (int i = 1; i <= langs.size(); i++) {
            int emailNumber = getEmailNumber();
            String email = generateNewUserEmail(emailNumber);
            new Navigator(driver).open(RegistrationPage.class)
                    .setUserCridentials(email, pass);
            String message = MailService.getRegistrationMail();
            System.out.println(message);
            emailNumber = emailNumber + 2;
            setEmail(email, emailNumber);
            setPassword(pass);

            verifier.assertTrue(verifier.verifyReceivedMail(message, siteLang));
            String link = "https://" + message.split(" https://")[1].split("\\n")[0];
            driver.navigate().to(link);

            pass = getPassword();
            logInPage.login(email, pass);
            if (i == langs.size() || ConfigTest.iTest.equals(P2B)) {
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
