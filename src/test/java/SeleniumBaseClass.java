import com.selenium.ConfigTest;
import com.selenium.enums.Server;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pageobjects.HeaderMenu;
import webdriverconfiger.WebDriverManager;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class SeleniumBaseClass {

    static Server serverToTest = Server.GRV_7700;  //

    public WebDriver driver = null;
//    public static GravitecServer gravitecServer;


    protected Wait<WebDriver> wait;


    @BeforeTest(alwaysRun = true)
    public static void initializeTestServer() {
        ConfigTest.setTestServer(serverToTest);
    }

    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void configureWebDriver(@Optional("chrome") String browser) {

        driver = WebDriverManager.getDriver(browser);

        wait = new FluentWait<>(driver).withMessage("Element was not found").withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);
    }

    @AfterMethod
    public void endTest(ITestResult result) {
        String name = result.getName();
        System.out.println(name);
        driver.manage().deleteAllCookies();
    }

    @Parameters("browser")
    @AfterClass(alwaysRun = true)
    public void closeWebDriver(@Optional("chrome") String browser) throws AWTException {
        try {
            driver.findElement(new HeaderMenu(driver, wait).logOutButton).click();
        } catch (NoSuchElementException | StaleElementReferenceException ex) {
            System.out.println("Looks like you`re already signed out)");
        }
    }

    @Parameters("browser")
    @AfterSuite
    public void endTest(@Optional("chrome") String browser) {
        WebDriverManager.quitAllDrivers();
    }

    public WebDriver getDriver() {
        return driver;
    }
}