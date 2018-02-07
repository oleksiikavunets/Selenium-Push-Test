package tests.mailtests;

import actions.Verifier;
import com.selenium.MailService;
import common.BaseTestClass;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.HeaderMenu;
import pageobjects.LogInPage;
import pageobjects.RegistrationPage;
import pageutils.Navigator;
import testconfigs.baseconfiguration.TestServerConfiguretion;
import testconfigs.testdata.TestData;
import testutils.Listeners.LogListener;

import java.io.IOException;
import java.util.List;

import static com.selenium.enums.Server.P2B;
import static com.selenium.utils.NameGenerator.generateNewUserEmail;
import static testconfigs.testdatamanagers.TestUserManager.*;

@Listeners(LogListener.class)
public class Test_Pos_RegistrationEmailsMultiLanguage extends BaseTestClass {

    @Test(groups = {"mails", "registration"}, singleThreaded = true, threadPoolSize = 1)
    public void registrationMailsTest() throws IOException {

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
            emailNumber = emailNumber + 2;

            String message = MailService.getRegistrationMail();
            System.out.println(message);


            verifier.assertTrue(verifier.verifyReceivedMail(message, siteLang));
            String link = "https://" + message.split(" https://")[1].split("\\n")[0];
            driver.navigate().to(link);

            logInPage.login(email, pass);
            setEmail(email, emailNumber);
            setPassword(pass);
            if (i == langs.size() || TestServerConfiguretion.iTest.equals(P2B)) {
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
