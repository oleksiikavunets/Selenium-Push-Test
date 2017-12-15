import actions.Verifier;
import com.selenium.ConfigTest;
import com.selenium.MailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.HeaderMenu;
import pageobjects.LogInPage;
import pageobjects.NewPasswordSetUpPage;
import testdata.TestData;
import testutils.Listeners.LogListener;

import java.util.List;

import static com.selenium.enums.Server.P2B;

@Listeners(LogListener.class)
public class Test_Pos_RecoverPassMultiLanguage extends BaseTestClass {

    @Test(groups = {"mails", "recover password"}, singleThreaded = true, threadPoolSize = 1)
    public void testRecoverPasswordMail() throws Exception {
        Logger Log = LogManager.getLogger(Test_Pos_RecoverPassMultiLanguage.class);
        LogInPage logInPage = new LogInPage(driver);
        HeaderMenu headerMenu = new HeaderMenu(driver);

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

            logInPage.clickForgotPass().requestPasswordReset(email);
            String message = MailService.getRecoverPasswordMail();
            Log.info(message);
            verifier.assertTrue(verifier.verifyRecoverPasswordMail(message, siteLang));

            String link = "https://" + message.split("https://")[3].split("\\n")[0];
            driver.get(link);
            new NewPasswordSetUpPage(driver).setNewPass(newPass).login(email, newPass);
            config.setPassword(newPass);
            if (i == langs.size() || ConfigTest.iTest.equals(P2B)) {
                break;
            }
            headerMenu.switchLanguage(i);
            siteLang = headerMenu.checkLanguage();
            headerMenu.logout();
        }
        verifier.assertTestPassed();
    }
}
