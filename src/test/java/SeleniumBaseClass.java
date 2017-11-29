import com.selenium.ConfigTest;
import com.selenium.enums.Server;
import webdriverconfiger.WaitManager;
import webdriverconfiger.WebDriverManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pageobjects.HeaderMenu;

import java.awt.*;

public class SeleniumBaseClass {

    Server serverToTest = Server.GRV;


    public WebDriver driver = null;



    protected Wait<WebDriver> wait;


    @BeforeSuite
    public void initializeTestSuite(){
        ConfigTest.setTestServer(serverToTest);
    }
    @BeforeTest(alwaysRun = true)
    public void initializeTest() {
        if (ConfigTest.iTest == null) {
            ConfigTest.setTestServer(serverToTest);
            System.out.println("Initialized test server again: " + serverToTest);
        }
    }

    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void configureWebDriver(@Optional("chrome") String browser) {

        driver = WebDriverManager.getDriver(browser);

        wait = WaitManager.getWait();
    }

    @AfterMethod
    public void endTest(ITestResult result) {
        String name = result.getName();
        System.out.println(name);
        driver.manage().deleteAllCookies();
    }

    @Parameters("browser")
    @AfterClass(alwaysRun = true)
    public void tearDownTestClass(@Optional("chrome") String browser) throws AWTException {
        try {
            driver.findElement(new HeaderMenu(driver, wait).logOutButton).click();
        } catch (NoSuchElementException | StaleElementReferenceException ex) {
            System.out.println("Looks like you`re already signed out)");
        } catch (org.openqa.selenium.WebDriverException ex){
            String url = driver.getCurrentUrl();
            System.out.println("Error occurred in URL. But it`s OK) The test is over\n" +
                    "URL: " + url);
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