import com.selenium.ConfigTest;
import com.selenium.MailService;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import pageobjects.HeaderMenu;
import pageobjects.LogInPage;
import pageobjects.NewPasswordSetUpPage;
import pageobjects.RecoverPasswordPage;

/**
 * Created by Oleksii on 31.07.2017.
 */
public class Test_Pos_RestorePassword extends SeleniumBaseClass {

    @Test(groups = {"mails", "recover password"})
    public void restorePassword() throws Exception {
        RecoverPasswordPage recover = new RecoverPasswordPage(driver, wait);
        ConfigTest config = new ConfigTest();
        int emailNumber = Integer.valueOf(config.getEmailNumber()) - 2;
        String newPass = config.getPassword();

        if (newPass.equals("tttt1111")) newPass = "qqqq1111";
        else if (newPass.equals("qqqq1111")) newPass = "tttt1111";

        driver.navigate().to(config.getStartUrl() + "/forgot");
        wait.until(ExpectedConditions.presenceOfElementLocated(recover.resetPassButton));
        recover.setEmail("grovitek+" + emailNumber + "@gmail.com");
        recover.clickResetButton();
        String link = MailService.getRecoverLink();
        System.out.println(link);
        driver.navigate().to(link);
        new NewPasswordSetUpPage(driver, wait).setNewPass(newPass);

        new LogInPage(driver, wait).login("grovitek+" + emailNumber + "@gmail.com", newPass);
        new HeaderMenu(driver, wait).logout();

        config.setPassword(newPass);
    }
}
