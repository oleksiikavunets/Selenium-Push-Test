package pageutils;

import actions.Clicker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import webdriverconfiger.WaitManager;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static actions.Timer.waitSeconds;
import static utils.TextUtil.textOf;

public class CropUtil {

    private WebDriver driver;
    private Wait<WebDriver> wait;

    private By iconInput = By.id("file");
    private By bigImgInput = By.id("fileImg");
    private By confirmBtn = By.cssSelector("button[class*=btn-success]");
    private By cancelIconUploadBtn = By.cssSelector("button[ng-if][class*=primary]");
    private By cancelBigImgUploadBtn = By.cssSelector("uib-accordion button[ng-if][class*=primary]");

    private By iconCropRec = By.cssSelector("p[ng-bind-html*='SETTNGS_REC']");
    private By iconTooSmallErr = By.cssSelector("span[ng-bind*=ICON_TOO_SMALL]");
    private By iconTooBigErr = By.cssSelector("[ng-if*='iconErrorMessage'][ng-bind*='max size 200kb']");
    private By iconNotDistHint = By.cssSelector("[picture*=icon] p[translate=IMG_NOT_DIST]");
    private By iconWillBeDistHint = By.cssSelector("[picture*=icon] p[translate=IMG_WILL_BE_DIST]");
    private By iconWillZoomHint = By.cssSelector("[picture*=icon] p[translate=IMG_WILL_ZOOMED]");

    private By bigImgCropRec = By.cssSelector("p[ng-bind*=SETTNGS_BIG_REC]");
    private By bigImgTooSmallErr = By.cssSelector("span[ng-bind*=IMAGE_TOO_SMALL]");
    private By bigImgTooBigErr = By.cssSelector("[ng-if*='imageErrorMessage'][ng-bind*='max size 200kb']");

    private By bigImgNotDistHint = By.cssSelector("[picture*=img] p[translate=IMG_NOT_DIST]");
    private By bigImgWillBeDistHint = By.cssSelector("[picture*=img] p[translate=IMG_WILL_BE_DIST]");
    private By bigImgWillZoomHint = By.cssSelector("[picture*=img] p[translate=IMG_WILL_ZOOMED]");

    public CropUtil(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wait = new WaitManager(driver).getWait();
    }


    public CropUtil uploadAndCropIcon(String iconPath) {
        uploadIcon(iconPath).confirmUpload();
        return this;
    }

    public CropUtil uploadAndCropBigImg(String bigImgPath) {
        uploadBigImg(bigImgPath).confirmUpload();
        waitSeconds(1);
        return this;
    }

    public CropUtil uploadIcon(String path) {
        iconInput.findElement(driver).sendKeys(new File(path).getAbsolutePath());
        return this;
    }

    public CropUtil uploadBigImg(String path) {
        bigImgInput.findElement(driver).sendKeys(new File(path).getAbsolutePath());
        return this;
    }

    private CropUtil confirmUpload() {
        Clicker clicker = new Clicker(driver);
        clicker.click(confirmBtn);
        return this;
    }

    public CropUtil cancelIconUpload() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cancelIconUploadBtn)).click();
        return this;
    }

    public CropUtil cancelBigImageUpload() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cancelBigImgUploadBtn)).click();
        return this;
    }

    public String getIconCropRecommendation() {
        return textOf(iconCropRec, driver);
    }

    public String getIconTooSmallErr() {
        return textOf(iconTooSmallErr, driver);
    }

    public String getIconTooBigErr(){
        return textOf(iconTooBigErr, driver);
    }

    public String getIconNotDistHint() {
        return textOf(iconNotDistHint, driver);
    }

    public String getIconWillBeDistHint(){
        return textOf(iconWillBeDistHint, driver);
    }

    public String getIconWillZoomHint(){
        return textOf(iconWillZoomHint, driver);
    }

    public String getBigImageCropRecommendation() {
        return textOf(bigImgCropRec, driver);
    }

    public String getBigImgTooSmallErr() {
        return textOf(bigImgTooSmallErr, driver);
    }

    public String getBigImgTooBigErr() {
        return textOf(bigImgTooBigErr, driver);
    }

    public String getBigImgNotDistHint() {
        return textOf(bigImgNotDistHint, driver);
    }

    public String getBigImgWillBeDistHint() {
        return textOf(bigImgWillBeDistHint, driver);
    }

    public String getBigImgWillZoomHint() {
        return textOf(bigImgWillZoomHint, driver);
    }
}
