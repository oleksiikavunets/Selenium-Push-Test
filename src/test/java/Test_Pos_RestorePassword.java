import actions.Timer;
import com.selenium.ConfigTest;
import com.selenium.MailService;
import com.selenium.enums.Server;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.NewPasswordSetUpPage;
import pageobjects.RecoverPasswordPage;
import testutils.Listeners.LogListener;

/**
 * Created by Oleksii on 31.07.2017.
 */
@Listeners(LogListener.class)
public class Test_Pos_RestorePassword extends BaseTestClass {

    @Test(groups = {"mails", "recover password"}, singleThreaded = true, threadPoolSize = 1)
    public void restorePassword() throws Exception {
        ConfigTest config = new ConfigTest();
        int emailNumber = Integer.valueOf(config.getEmailNumber()) - 2;
        String newPass = config.getPassword();

        if (newPass.equals("tttt1111")) newPass = "qqqq1111";
        else if (newPass.equals("qqqq1111")) newPass = "tttt1111";

        driver.navigate().to(config.getStartUrl() + "/forgot");

        new RecoverPasswordPage(driver).setEmail("grovitek+" + emailNumber + "@gmail.com")
                .clickResetButton();
        String link = MailService.getRecoverLink();

        driver.navigate().to(link);
        new NewPasswordSetUpPage(driver).setNewPass(newPass).login("grovitek+" + emailNumber + "@gmail.com", newPass);
        config.setPassword(newPass);
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
