package pageobjects;

import actions.Timer;
import com.selenium.ConfigTest;
import com.selenium.enums.Server;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.common.AbstractPage;


/**
 * Created by Oleksii on 12.07.2017.
 */
public class LogInPage extends AbstractPage {

    private By loginInput = By.name("email");
    private By passwordInput = By.name("password");
    private By buttonSubmit = By.cssSelector("button[type='submit']");
    private By registerButton = By.cssSelector("b[ng-bind*=\"'LGN_SIGN_UP'\"]");
    private By forgotPassButton = By.cssSelector("a[ng-bind*=\"'LGN_FRGT_PSSWRD'\"]");
    private By error = By.xpath("//p[@ng-bind=\"vmLogin.error | translate\"]");

    public LogInPage(WebDriver driver) {
        super(driver);
    }

    public MainAdminPage login(String login, String pass) {
        try {
            String currentUrl = driver.getCurrentUrl();
            if (!currentUrl.contains("login")) driver.get(new ConfigTest().getStartUrl() + "/login");
        } catch (NullPointerException e) {
        }
        for (int i = 0; i <= 100; i++) {
            if (i > 0 && i % 20 == 0) {
                driver.navigate().refresh();
            }
            System.out.println("IN.....");
            setLogin(login).setPassword(pass);
            if (driver.findElement(loginInput).getAttribute("value").equals(login) && driver.findElement(passwordInput).getAttribute("value").equals(pass)) {
                submit();
                break;
            } else {
                clearAll();
            }
        }
        System.out.println("OUT.....");
        return new MainAdminPage(driver);
    }

    private LogInPage managePopUp() {
        if (ConfigTest.iTest.equals(Server.GRV_7700)) {
            try {
                Timer.waitSeconds(1);
                driver.findElement(By.cssSelector("button[ng-click=\"$close()\"]")).click();
            } catch (NoSuchElementException e) {
                System.out.println("No pop up");
            }
        }
        return this;
    }

    public LogInPage setLogin(String login) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginInput)).sendKeys(login);
        return this;
    }

    public LogInPage setPassword(String pass) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(pass);
        return this;
    }

    public LogInPage submit() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonSubmit)).click();
        return this;
    }

    public LogInPage clearLogin() {
        driver.findElement(loginInput).clear();
        return this;
    }

    public LogInPage clearAll() {
        driver.findElement(loginInput).clear();
        driver.findElement(passwordInput).clear();
        return this;
    }

    public RegistrationPage clickRegister() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(registerButton)).click();
        return new RegistrationPage(driver);
    }

    public RecoverPasswordPage clickForgotPass() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(forgotPassButton)).click();
        return new RecoverPasswordPage(driver);
    }

    public WebElement getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(error));
    }
}