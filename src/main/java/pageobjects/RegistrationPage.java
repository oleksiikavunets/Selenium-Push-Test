package pageobjects;

import com.selenium.ConfigTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.common.AbstractPage;

/**
 * Created by Oleksii on 17.07.2017.
 */
public class RegistrationPage extends AbstractPage{

    public By email = By.name("email");
    public By password = By.name("password");
    public By repeatPassword = By.name("password_repeat");
    public By submit = By.cssSelector("button[type=\"submit\"]");
    public By invalidFormat = By.xpath("//p[@ng-bind=\"'Invalid format' | translate\"]");
    public By invalidFormatPass = By.cssSelector("p[ng-bind=\"'Invalid format pass' | translate\"]");
    public By passDNTMatch = By.cssSelector("p[translate=\"PASS_DNT_MATCH\"]");
    public By errorExists = By.cssSelector("p[ng-repeat=\"error in vmRegister.errors.email track by $index\"]");

    public RegistrationPage(WebDriver driver){
        super(driver);
    }

    public RegistrationPage setEmail(String login) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(email)).sendKeys(login);
        return this;
    }

    public RegistrationPage openRegistrationPage(){
        driver.navigate().to(new ConfigTest().getStartUrl() + "/forgot");
        return new RegistrationPage(driver);
    }

    public RegistrationPage clearEmail(){
        email.findElement(driver).clear();
        return this;
    }

    public RegistrationPage setPass(String pass) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(password)).sendKeys(pass);
        return this;
    }
    public RegistrationPage clearPass(){
        password.findElement(driver).clear();
        return this;
    }

    public RegistrationPage repeatPass(String pass) {
        repeatPassword.findElement(driver).sendKeys(pass);
        return this;
    }
    public RegistrationPage clearRepeatPass(){
        repeatPassword.findElement(driver).clear();
        return this;
    }

    public RegistrationPage submit() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(submit)).click();
        return this;
    }



    public void setUserCridentials(String login, String pass) {
        Logger Log = LogManager.getLogger(LogInPage.class);
        setEmail(login);
        setPass(pass);
        repeatPass(pass);
        submit();
        Log.info("NEW USER REGISTERED. EMAIL: " + login + " PASSWORD:" + pass);
    }
}
