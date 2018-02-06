import actions.Timer;
import com.selenium.ConfigTest;
import com.selenium.MailService;
import com.selenium.enums.Server;
import common.BaseTestClass;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.NewPasswordSetUpPage;
import pageobjects.RecoverPasswordPage;
import pageutils.Navigator;
import testutils.Listeners.LogListener;

import static testdata.TestData.testEmail;
import static testdata.TestData.testPass;
import static testdatamanagers.TestUserManager.setPassword;

/**
 * Created by Oleksii on 31.07.2017.
 */
@Listeners(LogListener.class)
public class Test_Pos_RestorePassword extends BaseTestClass {

    @Test(groups = {"mails", "recover password"}, singleThreaded = true, threadPoolSize = 1)
    public void restorePasswordTest() throws Exception {
        String newPass = testPass;

        if (newPass.equals("tttt1111")) newPass = "qqqq1111";
        else if (newPass.equals("qqqq1111")) newPass = "tttt1111";

        new Navigator(driver).open(RecoverPasswordPage.class)
                .setEmail(testEmail)
                .clickResetButton();
        String link = MailService.getRecoverLink();

        driver.navigate().to(link);
        new NewPasswordSetUpPage(driver).setNewPass(newPass)
                .login(testEmail, newPass);
        setPassword(newPass);
    }

    private void managePopUp() {
        if (ConfigTest.iTest.equals(Server.GRV_7700)) {
            try {
                Timer.waitSeconds(1);
                driver.findElement(By.cssSelector("button[ng-click=\"$close()\"]")).click();
            } catch (NoSuchElementException e) {
                System.out.println("No pop up");
            }
        }
    }
}
