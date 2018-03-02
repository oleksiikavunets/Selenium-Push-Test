package tests.accounttests;

import actions.Timer;
import com.selenium.MailService;
import com.selenium.enums.Server;
import common.BaseTestClass;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.NewPasswordSetUpPage;
import pageobjects.RecoverPasswordPage;
import pageutils.NavigationUtil;
import testconfigs.baseconfiguration.TestServerConfiguretion;
import testconfigs.testdatamanagers.TestUserManager;
import testutils.Listeners.LogListener;

/**
 * Created by Oleksii on 31.07.2017.
 */
@Listeners(LogListener.class)
public class Test_Pos_RestorePassword extends BaseTestClass {

    @Test(groups = {"mails", "recover password"}, singleThreaded = true, threadPoolSize = 1)
    public void restorePasswordTest() throws Exception {
        String newPass = new TestUserManager().getPassword();

        if (newPass.equals("tttt1111")) newPass = "qqqq1111";
        else if (newPass.equals("qqqq1111")) newPass = "tttt1111";

        new NavigationUtil(driver).open(RecoverPasswordPage.class)
                .setEmail(new TestUserManager().getEmail())
                .clickResetButton();
        String link = MailService.getRecoverLink();

        driver.navigate().to(link);
        new NewPasswordSetUpPage(driver).setNewPass(newPass)
                .login(new TestUserManager().getEmail(), newPass);
        new TestUserManager().setPassword(newPass);
    }

    private void managePopUp() {
        if (TestServerConfiguretion.iTest.equals(Server.GRV_7700)) {
            try {
                Timer.waitSeconds(1);
                driver.findElement(By.cssSelector("button[ng-click=\"$close()\"]")).click();
            } catch (NoSuchElementException e) {
                System.out.println("No pop up");
            }
        }
    }
}
