package pageobjects;

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
public class CreateWMPage extends AbstractPage{

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

    public CreateWMPage(WebDriver driver){
        super(driver);
    }

    public CreateWMPage setTitle(String ttl) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(titleInput)).sendKeys(ttl);
        return this;
    }

    public CreateWMPage clearTitle() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(titleInput)).clear();
        return this;
    }

    public CreateWMPage clearText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(textInput)).clear();
        return this;
    }

    public CreateWMPage setText(String t) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(textInput)).sendKeys(t);
        return this;
    }

    public CreateWMPage setURL(String url) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(urlInput)).sendKeys(url);
        return this;
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
            new ImageUploader(driver).uploadIcon(path);
        }else {
            uploadIcon(path);
        }
        Timer.waitSeconds(1);
        String iconLink = wait.until(ExpectedConditions.visibilityOfElementLocated(iconPreview)).getAttribute("src");

        return iconLink;
    }

    public CreateWMPage uploadIcon(String path) {
        driver.findElement(iconInput).sendKeys(new File(path).getAbsolutePath());
        return this;
    }

    public AdditionalActiveItems openAdditionalActiveItems() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(additionalActiveItems)).click();
        return new AdditionalActiveItems();
    }

    public class AdditionalActiveItems {
        public AdditionalActiveItems switchButton1() {
            button1Switch.findElement(driver).click();
            return this;
        }

        public AdditionalActiveItems switchButton2() {
            button2Switch.findElement(driver).click();
            return this;
        }

        public AdditionalActiveItems setButton1Name(String name) {
            button1NameInput.findElement(driver).sendKeys(name);
            return this;
        }

        public AdditionalActiveItems setButton2Name(String name) {
            button2NameInput.findElement(driver).sendKeys(name);
            return this;
        }

        public AdditionalActiveItems setButton1URL(String url) {
            button1URL.findElement(driver).sendKeys(url);
            return this;
        }

        public AdditionalActiveItems setButton2URL(String url) {
            button2URL.findElement(driver).sendKeys(url);
            return this;
        }

        public AdditionalActiveItems setButton1(String buttonName, String buttonURL) {
            setButton1Name(buttonName);
            setButton1URL(buttonURL);
            return this;
        }

        public AdditionalActiveItems setButton2(String buttonName, String buttonURL) {
            setButton2Name(buttonName);
            setButton2URL(buttonURL);
            return this;
        }

        public AdditionalActiveItems setButtons(String button1Name, String button1URL, String button2Name, String button2URL) {
            switchButton1();
            switchButton2();
            setButton1(button1Name, button1URL);
            setButton2(button2Name, button2URL);
            return this;
        }

        public AdditionalActiveItems switchBIGImage() {
            bigImageSwitch.findElement(driver).click();
            return this;
        }

        public AdditionalActiveItems setBIGImage(String path) {
            switchBIGImage();
            {
                driver.findElement(bigImageInput).sendKeys(new File(path).getAbsolutePath());
            }
            return this;
        }

        public String uploadBigImage(String path) {
            if(BetaFeatures.verifyBetaToTest("imageCropper")){
                switchBIGImage();
                new ImageUploader(driver).uploadBigImg(path);
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

    public WelcomeMessagePage saveWM() {
        saveWMButton.findElement(driver).click();
        return new WelcomeMessagePage(driver);
    }
}
