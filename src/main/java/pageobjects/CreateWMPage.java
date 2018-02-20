package pageobjects;

import actions.Timer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.common.AbstractAdminPage;
import pageobjects.common.annotations.PartialPath;
import pageutils.CropUtil;
import testconfigs.testrestrictions.BetaFeatures;

import java.io.File;

@PartialPath(value = "/sites/SITE_ID/welcome-messages/create")
public class CreateWMPage extends AbstractAdminPage {

    //push preview block
    private By titlePreview = By.cssSelector("p[data-ng-bind*=\"$ctrl.mtitle \"]");
    private By textPreview = By.cssSelector("p[data-ng-bind*=\"$ctrl.body\"]");
    private By iconPreview = By.cssSelector("div[class*=\"icon\"]>img[src]");
    private By bigImagePreview = By.cssSelector("img[class=\"additional-img\"]");
    private By button1Preview = By.cssSelector("span[ng-bind*=\"$ctrl.btn1.title \"]");
    private By button2Preview = By.cssSelector("span[ng-bind*=\"$ctrl.btn2.title \"]");

    //text input fields
    private By titleInput = By.name("title");
    private By textInput = By.name("text");
    private By urlInput = By.id("redirect-url");
    private By iconInput = By.name("file");

    //additional active items
    private By additionalActiveItems = By.cssSelector("span[ng-bind*=\"ADDTNL_ELEMNTS\"]");
    private By button1Switch = By.cssSelector("label[ng-bind*=\"'FRST_BTN'\"]");
    private By button2Switch = By.cssSelector("label[ng-bind*=\"'SND_BTN'\"]");
    private By button1NameInput = By.name("button1Title");
    private By button2NameInput = By.name("button2Title");
    private By button1URL = By.name("url1");
    private By button2URL = By.name("url2");
    private By bigImageSwitch = By.cssSelector("label[ng-bind*=\"'ADD_BIG_IMG'\"]");
    private By bigImageInput = By.cssSelector("input[ngf-select*=\"uploadFile\"]");

    private By saveWMButton = By.cssSelector("button[type=\"submit\"]");
    //errors
    private By requiredTitle = By.cssSelector("p[translate=\"REQ_FIELD\"]");
    private By requiredText = By.cssSelector("div[ng-show*=\"text.$error.required\"]>p");
    private By errorLink = By.cssSelector("div[ng-show*=\"redirect.$error.p\"]>p");

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
        return titlePreview.findElement(driver);
    }

    public WebElement getTextPreview() {
        return textPreview.findElement(driver);
    }

    public WebElement getIconPreview() {

        return iconPreview.findElement(driver);
    }

    public WebElement getButton1Preview() {
        WebElement element = null;
        try {
            element = button1Preview.findElement(driver);
        } catch (org.openqa.selenium.NoSuchElementException noEL) {
            noEL.printStackTrace();
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
            new CropUtil(driver).uploadIcon(path);
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
                new CropUtil(driver).uploadBigImg(path);
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
