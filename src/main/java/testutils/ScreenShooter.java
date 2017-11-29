package testutils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import webdriverconfiger.WebDriverManager;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShooter {

    static WebDriver driver = WebDriverManager.getDriver();


    public static String captureScreenshot(String screenShotName)
    {
        String location;
        WebDriver augmentedDriver = new Augmenter().augment(driver);
        DateFormat dateformate = new SimpleDateFormat("dd-mm-yy-hh-mm-ss");
        Date date = new Date();
        String currentdate = dateformate.format(date);
        String imageName =screenShotName+currentdate;
        File scrFile = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
        location = "test-output\\screenShots\\" + imageName + ".png";
        try {
            FileUtils.copyFile(scrFile, new File(location));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return location;
    }
}