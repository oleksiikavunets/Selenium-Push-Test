package pageobjects;

import actions.Custom;
import actions.Timer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.common.AbstractPage;
import pageutils.ImageUploader;
import testrestrictions.BetaFeatures;

import java.io.File;

/**
 * Created by Oleksii on 19.07.2017.
 */
public class SiteSettingsPage extends AbstractPage{

    private By siteScript = By.xpath("//blockquote[@id=\"client-script-tag\"]");
    private By siteIcon = By.cssSelector("img[ng-show*=\"site.icon\"]");
    public By iconInput = By.cssSelector("input[type='file']");
    public By UTMsource = By.cssSelector("span[ng-bind*=\"site.utm.source\"]");
    public By UTMmedium = By.cssSelector("span[ng-bind*=\"site.utm.medium\"]");
    public By editUTMbutton = By.cssSelector("button[ng-bind*=\"'EDIT'\"]");
    public By inputUTMsource = By.name("source");
    public By inputUTMmedium = By.name("medium");
    public By saveUTMbutton = By.cssSelector("button[ng-bind*=\"'SAVE'\"]");
    public By reqFielsUTMsource = By.cssSelector("span[ng-bind*=\"'REQ_FIELD'\"][ng-if*=\"utm.source\"]");
    public By reqFielsUTMmedium = By.cssSelector("span[ng-bind*=\"'REQ_FIELD'\"][ng-if*=\"utm.medium\"]");

    private By confirmPopUpButton = By.cssSelector("button[ng-bind*=\"ok\"]");
    public By cancelPopUpButton = By.cssSelector("button[ng-bind*=\"cancel\"]");
    public By deleteWebsiteButton = By.cssSelector("button[ng-click*=\"remove\"]");

    public String iconPath = "src/main/resources/imgs/selenium2.jpg";

    public SiteSettingsPage(WebDriver driver) {
        super(driver);
    }

    public String getSiteScript() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(siteScript)).getText();
    }

    public WebElement getSiteIcon(){
        return siteIcon.findElement(driver);
    }

    public WebElement getConfirmPopUpBtn(){
        return confirmPopUpButton.findElement(driver);
    }

    public SiteSettingsPage clickConfirmPopUpButton() {
        getConfirmPopUpBtn().click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(confirmPopUpButton));
        return this;
    }

    public SiteSettingsPage clickCancelPopUpButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cancelPopUpButton)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(cancelPopUpButton));
        return this;
    }

    public SiteSettingsPage clickDeleteWebsiteButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(deleteWebsiteButton)).click();
        return this;
    }

    public SiteSettingsPage uplodIcon(String path) {
        if (BetaFeatures.verifyBetaToTest("imageCropper")) {
            new ImageUploader(driver).uploadIcon(path);
        } else {
            driver.findElement(By.cssSelector("input[type='file']")).sendKeys(new File(path).getAbsolutePath());
        }
        return this;
    }

    public SiteSettingsPage clickDelete() {

        Custom custom = new Custom(driver);

        wait.until(ExpectedConditions.elementToBeClickable(deleteWebsiteButton));
//       WebElement element = driver.findElement(deleteWebsiteButton);
//        Timer.waitSeconds(1);
        custom.clickAt(deleteWebsiteButton.findElement(driver));
        return this;
    }

    public SiteSettingsPage clickEditUTM() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(editUTMbutton)).click();
        return this;
    }

    public SiteSettingsPage saveNewUTM() {
        saveUTMbutton.findElement(driver).click();
        clickConfirmPopUpButton();
        return this;
    }

    public SiteSettingsPage setUTM_source(String s) {
        inputUTMsource.findElement(driver).sendKeys(s);
        return this;
    }

    public SiteSettingsPage setUTM_medium(String s) {
        inputUTMmedium.findElement(driver).sendKeys(s);
        return this;
    }

    public SiteSettingsPage setNewUTM(String source, String medium) {
        clickEditUTM();
        clearUTMTags();
        inputUTMsource.findElement(driver).sendKeys(source);
        inputUTMmedium.findElement(driver).sendKeys(medium);
        saveNewUTM();
        return this;
    }

    public SiteSettingsPage clearUTMTags() {
        inputUTMsource.findElement(driver).clear();
        inputUTMmedium.findElement(driver).clear();
        return this;
    }

    public String getUtm_source() {
        Timer.waitSeconds(0.5);
        String utm = wait.until(ExpectedConditions.visibilityOfElementLocated(UTMsource)).getText();
        if (utm.length() == 0) {
            while (utm.length() == 0) {
                Timer.waitSeconds(0.5);
                utm = UTMsource.findElement(driver).getText();
            }
        }
        return utm;
    }

    public String getUtm_medium() {
        String utm = wait.until(ExpectedConditions.visibilityOfElementLocated(UTMmedium)).getText();
        return utm;
    }
}
