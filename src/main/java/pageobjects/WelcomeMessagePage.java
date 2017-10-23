package pageobjects;

import actions.Custom;
import actions.Timer;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

/**
 * Created by Oleksii on 19.07.2017.
 */
public class WelcomeMessagePage {
    WebDriver driver;
    Wait<WebDriver> wait;

    By WMSwitcher = By.cssSelector("input[type='checkbox']");
    By WMTitle = By.xpath("//*[@id=\"welcome-messages-list\"]//td[2]//strong");
    By WMText = By.xpath("//*[@id=\"welcome-messages-list\"]//span/text()");
    By addNewWMButton = By.cssSelector("a[ng-bind*=\"WLCMMS_ADD_MSSG\"]");
    By editWMButton = By.cssSelector("a[ng-bind*=\"EDIT\"]");
    By removeWMButton = By.cssSelector("button[ng-bind*=\"REMOVE\"]");
    By disabledWM = By.cssSelector("span[ng-bind*=\"DISABLED\"]");
    By enabledWM = By.cssSelector("span[ng-bind*=\"'ENABLED'\"]");

    public WelcomeMessagePage(WebDriver driver, Wait<WebDriver> wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public WebElement getWMTitle(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(WMTitle));
    }

    public WebElement getWMText(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(WMText));
    }

    public WebElement getCreateNewWMButton(){
        WebElement button = null;
     try {
         button = addNewWMButton.findElement(driver);
     }catch (org.openqa.selenium.NoSuchElementException  noEL){}
        return button;
    }

    public void switchWM() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(WMSwitcher));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"input[type='checkbox']\").click();");
        Timer.waitSeconds(0.5);
    }

    public CreateWMPage clickCreateNewWM() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(addNewWMButton));
        new Custom(driver).clickAt(element);
        return new CreateWMPage(driver, wait);
    }

    public void clickEditWM(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(editWMButton)).click();
    }

    public void enableWM() {
        new Custom(driver).clickAt(wait.until(ExpectedConditions.visibilityOfElementLocated(disabledWM)));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(disabledWM)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(enabledWM)).isDisplayed();
    }

    public void disableWM() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(enabledWM)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(disabledWM));
    }

    public void deleteWM() {
        Custom custom = new Custom(driver);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(removeWMButton));
        custom.clickAt(element);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(WMTitle));
    }

    public boolean isDisabledWM() {
        boolean isDisabled = wait.until(ExpectedConditions.visibilityOfElementLocated(disabledWM)).isDisplayed();
        return isDisabled;
    }

    public boolean isEnabledWM() {
        boolean isEnabled = wait.until(ExpectedConditions.visibilityOfElementLocated(enabledWM)).isDisplayed();
        return isEnabled;
    }

}
