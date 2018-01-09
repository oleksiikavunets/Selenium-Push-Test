package pageutils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import webdriverconfiger.WaitManager;

import java.io.File;

public class ImageUploader {

    private static WebDriver driver;
    private static Wait<WebDriver> wait;

    private static By iconInput = By.id("file");
    private static By bigImgInput = By.id("fileImg");
    private static By confirmBtn = By.cssSelector("button[class*=\"btn-success\"]");
    private static By cancelBtn = By.cssSelector("button[ng-if][class*=\"primary\"]");

    public ImageUploader(WebDriver driver){
        this.driver = driver;
        wait = new WaitManager(driver).getWait();
    }


    public  void uploadIcon(String iconPath) {
        iconInput.findElement(driver).sendKeys(new File(iconPath).getAbsolutePath());
        confirmUpload();
    }

    public  void uploadBigImg(String bigImgPath) {
        bigImgInput.findElement(driver).sendKeys(new File(bigImgPath).getAbsolutePath());
        confirmUpload();
    }

    public  void confirmUpload() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmBtn)).click();
    }

    public  void cancelUpload() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cancelBtn)).click();
    }


}