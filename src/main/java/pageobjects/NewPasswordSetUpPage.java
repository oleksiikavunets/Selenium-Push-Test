package pageobjects;

import actions.Timer;
import com.selenium.ConfigTest;
import com.selenium.enums.Server;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.common.AbstractPage;

/**
 * Created by Oleksii on 18.07.2017.
 */
public class NewPasswordSetUpPage extends AbstractPage{

    Logger Log = LogManager.getLogger(LogInPage.class);

    public By submitButton = By.cssSelector("button[type=\"submit\"]");
    public By passInput = By.name("password");
    public By passRepeat = By.name("password_repeat");
    public By okPopUpButton = By.cssSelector("button[ng-bind*=\"ok\"]");

    public NewPasswordSetUpPage(WebDriver driver){
        super(driver);
    }

    public LogInPage setNewPass(String newPass) {
        managePopUp();
        wait.until(ExpectedConditions.visibilityOfElementLocated(passInput)).sendKeys(newPass);
        managePopUp();
        passRepeat.findElement(driver).sendKeys(newPass);
        managePopUp();
        submitButton.findElement(driver).click();
        managePopUp();
        Log.info("NEW PASSWORD: " + newPass);
       clickOkPopUP();
       return new LogInPage(driver);
    }

    public void clickOkPopUP(){
        if(ConfigTest.iTest.equals(Server.GRV_7700)) {
            try {
                Timer.waitSeconds(0.5);
                okPopUpButton.findElement(driver).click();
            } catch (NoSuchElementException e) {
                System.out.println("");
            }
        }else{
            wait.until(ExpectedConditions.visibilityOfElementLocated(okPopUpButton)).click();
        }
    }

    private void managePopUp(){
        if(ConfigTest.iTest.equals(Server.GRV_7700)){
            try{
                Timer.waitSeconds(1);
                driver.findElement(By.cssSelector("button[ng-click=\"$close()\"]")).click();
            }catch (NoSuchElementException e){
                System.out.println("No pop up");
            }
        }
    }
}
