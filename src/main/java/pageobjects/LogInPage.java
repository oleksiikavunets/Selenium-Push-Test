package pageobjects;

import actions.Timer;
import com.selenium.ConfigTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;


/**
 * Created by Oleksii on 12.07.2017.
 */
public class LogInPage {

    protected WebDriver driver;
    Wait<WebDriver> wait;
    public By loginInput = By.name("email");
    public By passwordInput = By.name("password");
    public By buttonSubmit = By.cssSelector("button[type='submit']");
    public By registerButton = By.cssSelector("b[ng-bind*=\"'LGN_SIGN_UP'\"]");
    public By forgotPassButton = By.cssSelector("a[ng-bind*=\"'LGN_FRGT_PSSWRD'\"]");
    public By error = By.xpath("//p[@ng-bind=\"vmLogin.error | translate\"]");

    public LogInPage(WebDriver driver, Wait<WebDriver> wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public MainAdminPage login(String login, String pass) {
        Logger Log = LogManager.getLogger(LogInPage.class);

        ConfigTest config = new ConfigTest();

        driver.navigate().to(config.getStartUrl() + "/login");
//        Log.info("LOGIN: " + login + " PASSWORD: " + pass);


        for (int i = 0; i <= 100; i++) {
            if(i%20==0){
                driver.navigate().refresh();
            }
            try {
                setLogin(login);
                setPassword(pass);
                driver.findElement(buttonSubmit).click();
                Timer.waitSeconds(0.2);
                try {
                    Assert.assertFalse(driver.findElement(error).isDisplayed());
                } catch (AssertionError A) {
                    clearAll();
                }


            } catch (Exception e) {
                break;//quit;
            }
        }

        return new MainAdminPage(driver, wait);
    }

    public void setLogin(String login) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginInput)).sendKeys(login);
    }

    public void setPassword(String pass) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(pass);
    }

    public void submit() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonSubmit)).click();
    }

    public void clearAll() {
        driver.findElement(loginInput).clear();
        driver.findElement(passwordInput).clear();
    }

    public RegistrationPage clickRegister() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(registerButton)).click();
        return new RegistrationPage(driver, wait);
    }

    public RecoverPasswordPage clickForgotPass() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(forgotPassButton)).click();
        return new RecoverPasswordPage(driver, wait);
    }
}