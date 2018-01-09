package pageobjects;

import com.selenium.ConfigTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.common.AbstractPage;

/**
 * Created by Oleksii on 18.07.2017.
 */
public class RecoverPasswordPage extends AbstractPage{

    public By resetPassButton = By.cssSelector("button[type=\"submit\"]");
    public By emailInput = By.cssSelector("input[type=\"email\"]");
    public By errorMessage = By.cssSelector("div[class='error']");

    public RecoverPasswordPage(WebDriver driver){
        super(driver);
    }

    public RecoverPasswordPage openRecoverPasswordPage(){
        driver.get(new ConfigTest().getStartUrl() + "/forgot");
        return this;
    }

    public RecoverPasswordPage requestPasswordReset(String email){
        Logger Log = LogManager.getLogger(LogInPage.class);
        setEmail(email);
        clickResetButton();
        Log.info("REQUESTED NEW PASSWORD FOR: " + email);
        return this;
    }

    public RecoverPasswordPage setEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
        return this;
    }

    public RecoverPasswordPage clickResetButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(resetPassButton)).click();
        return this;
    }
}
