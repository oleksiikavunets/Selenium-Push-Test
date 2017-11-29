package pageobjects;

import actions.Timer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import pageutils.ImageUploader;
import testrestrictions.BetaFeatures;

import java.io.File;

/**
 * Created by Oleksii on 19.07.2017.
 */
public class CreateWMPage {
    WebDriver driver;
    Wait<WebDriver> wait;

    //push preview block
    By titlePreview = By.cssSelector("p[data-ng-bind*=\"$ctrl.mtitle \"]");
    By textPreview = By.cssSelector("p[data-ng-bind*=\"$ctrl.body\"]");
    By iconPreview = By.cssSelector("div[class*=\"icon\"]>img[src]");
    By bigImagePreview = By.cssSelector("img[class=\"additional-img\"]");
    By button1Preview = By.cssSelector("span[ng-bind*=\"$ctrl.btn1.title \"]");
    By button2Preview = By.cssSelector("span[ng-bind*=\"$ctrl.btn2.title \"]");

    //text input fields
    By titleInput = By.name("title");
    By textInput = By.name("text");
    By urlInput = By.id("redirect-url");
    By iconInput = By.name("file");

    //additional active items
    By additionalActiveItems = By.cssSelector("span[ng-bind*=\"ADDTNL_ELEMNTS\"]");
    By button1Switch = By.cssSelector("label[ng-bind*=\"'FRST_BTN'\"]");
    By button2Switch = By.cssSelector("label[ng-bind*=\"'SND_BTN'\"]");
    By button1NameInput = By.name("button1Title");
    By button2NameInput = By.name("button2Title");
    By button1URL = By.name("url1");
    By button2URL = By.name("url2");
    By bigImageSwitch = By.cssSelector("label[ng-bind*=\"'ADD_BIG_IMG'\"]");
    By bigImageInput = By.cssSelector("input[ngf-select*=\"uploadFile\"]");

    By saveWMButton = By.cssSelector("button[type=\"submit\"]");
    //errors
    By requiredTitle = By.cssSelector("p[translate=\"REQ_FIELD\"]");
    By requiredText = By.cssSelector("div[ng-show*=\"text.$error.required\"]>p");
    By errorLink = By.cssSelector("div[ng-show*=\"redirect.$error.p\"]>p");


    public CreateWMPage(WebDriver driver, Wait<WebDriver> wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void setTitle(String ttl) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(titleInput)).sendKeys(ttl);
    }

    public void clearTitle() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(titleInput)).clear();
    }

    public void clearText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(textInput)).clear();
    }

    public void setText(String t) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(textInput)).sendKeys(t);
    }

    public void setURL(String url) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(urlInput)).sendKeys(url);
    }

    public WebElement getTitlePreview() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(titlePreview));
    }

    public WebElement getTextPreview() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(textPreview));
    }

    public WebElement getIconPreview() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(iconPreview));
    }

    public WebElement getButton1Preview() {
        WebElement element = null;
        try {
            element = button1Preview.findElement(driver);
        } catch (org.openqa.selenium.NoSuchElementException noEL) {
        }
        return element;
    }

    public WebElement getButton2Preview() {
        WebElement element = null;
        try {
            element = button2Preview.findElement(driver);
        } catch (org.openqa.selenium.NoSuchElementException noEL) {
        }
        return element;
    }

    public WebElement getBigImagePreview() {
        WebElement element = null;
        try {
            element = bigImagePreview.findElement(driver);
        } catch (org.openqa.selenium.NoSuchElementException noEL) {
        }
        return element;
    }

    public String uploadIconToWM(String path) {

        if(BetaFeatures.verifyBetaToTest("imageCropper")){
            ImageUploader.uploadIcon(path);
        }else {
            uploadIcon(path);
        }
        Timer.waitSeconds(1);
        String iconLink = wait.until(ExpectedConditions.visibilityOfElementLocated(iconPreview)).getAttribute("src");

        return iconLink;
    }

    public void uploadIcon(String path) {
        driver.findElement(iconInput).sendKeys(new File(path).getAbsolutePath());
    }

    public AdditionalActiveItems openAdditionalActiveItems() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(additionalActiveItems)).click();
        return new AdditionalActiveItems();
    }

    public class AdditionalActiveItems {
        public void switchButton1() {
            button1Switch.findElement(driver).click();
        }

        public void switchButton2() {
            button2Switch.findElement(driver).click();
        }

        public void setButton1Name(String name) {
            button1NameInput.findElement(driver).sendKeys(name);
        }

        public void setButton2Name(String name) {
            button2NameInput.findElement(driver).sendKeys(name);
        }

        public void setButton1URL(String url) {
            button1URL.findElement(driver).sendKeys(url);
        }

        public void setButton2URL(String url) {
            button2URL.findElement(driver).sendKeys(url);
        }

        public void setButton1(String buttonName, String buttonURL) {
            setButton1Name(buttonName);
            setButton1URL(buttonURL);
        }

        public void setButton2(String buttonName, String buttonURL) {
            setButton2Name(buttonName);
            setButton2URL(buttonURL);
        }

        public void setButtons(String button1Name, String button1URL, String button2Name, String button2URL) {
            switchButton1();
            switchButton2();
            setButton1(button1Name, button1URL);
            setButton2(button2Name, button2URL);
        }

        public void switchBIGImage() {
            bigImageSwitch.findElement(driver).click();
        }

        public void setBIGImage(String path) {
            switchBIGImage();
            {
                driver.findElement(bigImageInput).sendKeys(new File(path).getAbsolutePath());
            }
        }

        public String uploadBigImage(String path) {
            if(BetaFeatures.verifyBetaToTest("imageCropper")){
                switchBIGImage();
                ImageUploader.uploadBigImg(path);
            }else {
                setBIGImage(path);
            }
            String imageLink = wait.until(ExpectedConditions.visibilityOfElementLocated(bigImagePreview)).getAttribute("src");
            return imageLink;
        }
    }

    public WebElement getRequiredTitleError() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(requiredTitle));
    }

    public WebElement getRequiredTextError() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(requiredText));
    }

    public WebElement getInvalidLinkError() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorLink));
    }

    public void saveWM() {
        saveWMButton.findElement(driver).click();
    }
}
