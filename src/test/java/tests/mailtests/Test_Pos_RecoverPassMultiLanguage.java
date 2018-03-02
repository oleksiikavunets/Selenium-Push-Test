package tests.mailtests;

import actions.Verifier;
import com.selenium.MailService;
import common.BaseTestClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.HeaderMenu;
import pageobjects.LogInPage;
import pageobjects.NewPasswordSetUpPage;
import pageobjects.RecoverPasswordPage;
import pageutils.NavigationUtil;
import testconfigs.baseconfiguration.TestServerConfiguretion;
import testconfigs.testdata.TestData;
import testconfigs.testdatamanagers.TestUserManager;
import testutils.Listeners.LogListener;

import java.util.List;

import static com.selenium.enums.Server.P2B;

@Listeners(LogListener.class)
public class Test_Pos_RecoverPassMultiLanguage extends BaseTestClass {

    @Test(groups = {"mails", "recover password"}, singleThreaded = true, threadPoolSize = 1)
    public void recoverPasswordMailTest() throws Exception {
        Logger Log = LogManager.getLogger(Test_Pos_RecoverPassMultiLanguage.class);
        HeaderMenu headerMenu = new HeaderMenu(driver);
        Verifier verifier = new Verifier();

        String email = new TestUserManager().getEmail();
        String newPass;
        String oldPass = new TestUserManager().getPassword();
        System.out.println(oldPass);
        String siteLang;

        newPass = oldPass.equals("tttt1111") ? "qqqq1111" : "tttt1111";
        System.out.println(newPass);
        new LogInPage(driver).login(TestData.email, TestData.pass);
        List<WebElement> langs = headerMenu.getAvailableLanguages();
        headerMenu.switchFirstLanguage();
        siteLang = headerMenu.checkLanguage();
        headerMenu.logout();
        for (int i = 1; i <= langs.size(); i++) {

            new NavigationUtil(driver).open(RecoverPasswordPage.class).requestPasswordReset(email);
            String message = MailService.getRecoverPasswordMail();
            Log.info(message);
            verifier.assertTrue(verifier.verifyReceivedMail(message, siteLang));

            String link = "https://" + message.split("https://")[3].split("\\n")[0];
            driver.get(link);
            new NewPasswordSetUpPage(driver).setNewPass(newPass).login(email, newPass);
            new TestUserManager().setPassword(newPass);
            if (i == langs.size() || TestServerConfiguretion.iTest.equals(P2B)) {
                break;
            }
            headerMenu.switchLanguage(i);
            siteLang = headerMenu.checkLanguage();
            headerMenu.logout();
        }
        verifier.assertTestPassed();
    }
}
