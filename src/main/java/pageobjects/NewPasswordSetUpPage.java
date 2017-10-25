package pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

/**
 * Created by Oleksii on 18.07.2017.
 */
public class NewPasswordSetUpPage {
    WebDriver driver;
    Wait<WebDriver> wait;

    Logger Log = LogManager.getLogger(LogInPage.class);

    public By submitButton = By.cssSelector("button[type=\"submit\"]");
    public By passInput = By.name("password");
    public By passRepeat = By.name("password_repeat");
    public By okPopUpButton = By.cssSelector("button[ng-bind*=\"ok\"]");

    public NewPasswordSetUpPage(WebDriver driver, Wait<WebDriver> wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void setNewPass(String newPass) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passInput)).sendKeys(newPass);
        passRepeat.findElement(driver).sendKeys(newPass);
        submitButton.findElement(driver).click();
        Log.info("NEW PASSWORD: " + newPass);
       clickOkPopUP();
    }
    public void clickOkPopUP(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(okPopUpButton)).click();
    }
}
