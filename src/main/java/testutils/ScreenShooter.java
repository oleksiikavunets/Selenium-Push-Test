package testutils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import webdriverconfiger.WebDriverManager;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShooter {

    static WebDriver driver = WebDriverManager.getDriver();

    public static String captureScreenshot(String screenShotName) throws IOException
    {
        DateFormat dateformate = new SimpleDateFormat("dd-mm-yy-hh-mm-ss");
        Date date = new Date();
        String currentdate = dateformate.format(date);
        String imageName =screenShotName+currentdate;
        TakesScreenshot ts=(TakesScreenshot)driver;
        File source=ts.getScreenshotAs(OutputType.FILE);
        String location =System.getProperty("test-output") + "test-output\\screenshots\\"+imageName+".png";
        File screenshotLocation =new File (location);
        FileUtils.copyFile(source, screenshotLocation);
        return location;
    }
}