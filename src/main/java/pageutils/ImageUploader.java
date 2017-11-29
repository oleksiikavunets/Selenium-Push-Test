package pageutils;

import webdriverconfiger.WaitManager;
import webdriverconfiger.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;

public class ImageUploader {

    private static WebDriver driver = WebDriverManager.getDriver();
    private static Wait<WebDriver> wait = WaitManager.getWait();

    private static By iconInput = By.id("file");
    private static By bigImgInput = By.id("fileImg");
    private static By confirmBtn = By.cssSelector("button[class*=\"btn-success\"]");
    private static By cancelBtn = By.cssSelector("button[ng-if][class*=\"primary\"]");


    public static void uploadIcon(String iconPath) {
        iconInput.findElement(driver).sendKeys(new File(iconPath).getAbsolutePath());
        confirmUpload();
    }

    public static void uploadBigImg(String bigImgPath) {
        bigImgInput.findElement(driver).sendKeys(new File(bigImgPath).getAbsolutePath());
        confirmUpload();
    }

    public static void confirmUpload() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmBtn)).click();
    }

    public static void cancelUpload() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cancelBtn)).click();
    }


}
