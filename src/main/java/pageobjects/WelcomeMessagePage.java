package pageobjects;

import actions.Clicker;
import actions.Timer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.common.AbstractAdminPage;
import pageobjects.common.annotations.PartialPath;

/**
 * Created by Oleksii on 19.07.2017.
 */
@PartialPath(value = "/sites/SITE_ID/welcome-messages")
public class WelcomeMessagePage extends AbstractAdminPage {

    private By WMSwitcher = By.cssSelector("input[type=\"checkbox\"]");
    private By WMTitle = By.xpath("//*[@id=\"welcome-messages-list\"]//td[2]//strong");
    private By WMText = By.xpath("//*[@id=\"welcome-messages-list\"]//span/text()");
    private By addNewWMButton = By.cssSelector("a[ng-bind*=\"WLCMMS_ADD_MSSG\"]");
    private By editWMButton = By.cssSelector("a[ng-bind*=\"EDIT\"]");
    private By removeWMButton = By.cssSelector("button[ng-bind*=\"REMOVE\"]");
    private By disabledWM = By.cssSelector("span[ng-bind*=\"DISABLED\"]");
    private By enabledWM = By.cssSelector("span[ng-bind*='ENABLED']");

    public WelcomeMessagePage(WebDriver driver){
        super(driver);
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

    public WelcomeMessagePage switchWM() {

        new Clicker(driver).clickJS(WMSwitcher);
        Timer.waitSeconds(0.5);
        return this;
    }

    public CreateWMPage clickCreateNewWM() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(addNewWMButton));
        new Clicker(driver).clickJS(element);
        return new CreateWMPage(driver);
    }

    public CreateWMPage clickEditWM(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(editWMButton)).click();
        return new CreateWMPage(driver);
    }

    public WelcomeMessagePage enableWM() {
        new Clicker(driver).clickJS(wait.until(ExpectedConditions.visibilityOfElementLocated(disabledWM)));

        wait.until(ExpectedConditions.visibilityOfElementLocated(enabledWM)).isDisplayed();
        return this;
    }

    public WelcomeMessagePage disableWM() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(enabledWM)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(disabledWM));
        return this;
    }

    public WelcomeMessagePage deleteWM() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(removeWMButton));
        new Clicker(driver).clickJS(element);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(WMTitle));
        return this;
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
