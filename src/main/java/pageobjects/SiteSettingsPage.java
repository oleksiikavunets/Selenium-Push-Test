package pageobjects;

import actions.Custom;
import actions.Timer;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;

/**
 * Created by Oleksii on 19.07.2017.
 */
public class SiteSettingsPage {
    WebDriver driver;
    Wait<WebDriver> wait;

    public By siteSettingsSideButton = By.cssSelector("span[ng-bind*=\"'LMENU_SETT'\"]");
    public By siteIcon = By.cssSelector("img[ng-show*=\"site.icon\"]");
    public By iconInput = By.cssSelector("input[type='file']");
    public By UTMsource = By.cssSelector("span[ng-bind*=\"site.utm.source\"]");
    public By UTMmedium = By.cssSelector("span[ng-bind*=\"site.utm.medium\"]");
    public By editUTMbutton = By.cssSelector("button[ng-bind*=\"'EDIT'\"]");
    public By inputUTMsource = By.name("source");
    public By inputUTMmedium = By.name("medium");
    public By saveUTMbutton = By.cssSelector("button[ng-bind*=\"'SAVE'\"]");
    public By reqFielsUTMsource = By.cssSelector("span[ng-bind*=\"'REQ_FIELD'\"][ng-if*=\"utm.source\"]");
    public By reqFielsUTMmedium = By.cssSelector("span[ng-bind*=\"'REQ_FIELD'\"][ng-if*=\"utm.medium\"]");

    public By confirmPopUpButton = By.cssSelector("button[ng-bind*=\"ok\"]");
    public By cancelPopUpButton = By.cssSelector("button[ng-bind*=\"cancel\"]");
    public By deleteWebsiteButton = By.cssSelector("button[ng-click*=\"remove\"]");

    public String iconPath = "src/main/resources/imgs/selenium2.jpg";

    public SiteSettingsPage(WebDriver driver, Wait<WebDriver> wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void clickConfirmPopUpButton(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPopUpButton)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(confirmPopUpButton));
    }

    public void clickCancelPopUpButton(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(cancelPopUpButton)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(cancelPopUpButton));    }

    public void openSiteSettings() throws InterruptedException {
        Custom custom = new Custom(driver);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(siteSettingsSideButton));
//        custom.click(element);
        ((JavascriptExecutor) driver).executeScript("document.querySelector('span[ng-bind*=\"LMENU_SETT\"]').click();");
        Timer.waitSeconds(0.5);
    }

    public void clickDeleteWebsiteButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(deleteWebsiteButton)).click();
    }

    public void uplodIcon(String path) {
        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(new File(path).getAbsolutePath());
    }

    public void clickDelete() {

        Custom custom = new Custom(driver);

        wait.until(ExpectedConditions.elementToBeClickable(deleteWebsiteButton));
//       WebElement element = driver.findElement(deleteWebsiteButton);
//        Timer.waitSeconds(1);
        custom.clickAt(deleteWebsiteButton.findElement(driver));

    }

    public void clickEditUTM() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(editUTMbutton)).click();

    }

    public void saveNewUTM() {
        saveUTMbutton.findElement(driver).click();
        clickConfirmPopUpButton();
    }

    public void setUTM_source(String s){
        inputUTMsource.findElement(driver).sendKeys(s);
    }
    public void setUTM_medium(String s){
        inputUTMmedium.findElement(driver).sendKeys(s);
    }

    public void setNewUTM(String source, String medium) {
        clickEditUTM();
        clearUTMTags();
        inputUTMsource.findElement(driver).sendKeys(source);
        inputUTMmedium.findElement(driver).sendKeys(medium);
        saveNewUTM();
    }

    public void clearUTMTags() {
        inputUTMsource.findElement(driver).clear();
        inputUTMmedium.findElement(driver).clear();
    }

    public String getUtm_source(){
        Timer.waitSeconds(0.5);
        String utm =  wait.until(ExpectedConditions.visibilityOfElementLocated(UTMsource)).getText();
        if(utm.length()== 0){
            while(utm.length()==0) {
                Timer.waitSeconds(0.5);
                utm = UTMsource.findElement(driver).getText();
            }
        }
        return utm;
    }
    public String getUtm_medium(){
        String utm =  wait.until(ExpectedConditions.visibilityOfElementLocated(UTMsource)).getText();
        return utm;
    }
}
