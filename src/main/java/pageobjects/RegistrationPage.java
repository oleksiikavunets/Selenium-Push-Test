package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

/**
 * Created by Oleksii on 17.07.2017.
 */
public class RegistrationPage {
    WebDriver driver;
    Wait<WebDriver> wait;

    public By email = By.name("email");
    public By password = By.name("password");
    public By repeatPassword = By.name("password_repeat");
    public By submit = By.cssSelector("button[type=\"submit\"]");
    public By invalidFormat = By.xpath("//p[@ng-bind=\"'Invalid format' | translate\"]");
    public By invalidFormatPass = By.cssSelector("p[ng-bind=\"'Invalid format pass' | translate\"]");
    public By passDNTMatch = By.cssSelector("p[translate=\"PASS_DNT_MATCH\"]");
    public By errorExists = By.cssSelector("p[ng-repeat=\"error in vmRegister.errors.email track by $index\"]");

    public RegistrationPage(WebDriver driver, Wait<WebDriver> wait) {
        this.driver = driver;
        this.wait = wait;
    }


    public void setEmail(String login) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(email)).sendKeys(login);
    }

    public void clearEmail(){
        email.findElement(driver).clear();
    }

    public void setPass(String pass) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(password)).sendKeys(pass);
    }
    public void clearPass(){
        password.findElement(driver).clear();
    }

    public void repeatPass(String pass) {
        repeatPassword.findElement(driver).sendKeys(pass);
    }
    public void clearRepeatPass(){
        repeatPassword.findElement(driver).clear();
    }

    public void submit() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(submit)).click();
    }



    public void setUserCridentials(String login, String pass) {
        setEmail(login);
        setPass(pass);
        repeatPass(pass);
        submit();
    }




}
