package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

/**
 * Created by Oleksii on 18.07.2017.
 */
public class RecoverPasswordPage {
    WebDriver driver;
    Wait<WebDriver> wait;

    public By resetPassButton = By.cssSelector("button[type=\"submit\"]");
    public By emailInput = By.cssSelector("input[type=\"email\"]");
    public By errorMessage = By.cssSelector("div[class='error']");

    public RecoverPasswordPage(WebDriver driver, Wait<WebDriver> wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void requestPasswordReset(String email){
        setEmail(email);
        clickResetButton();
    }

    public void setEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
    }

    public void clickResetButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(resetPassButton)).click();
    }
}
