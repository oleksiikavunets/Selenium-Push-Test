package common;

import actions.UserActions;
import com.selenium.ConfigTest;
import com.selenium.enums.Server;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pageobjects.HeaderMenu;
import webdriverconfiger.WaitManager;
import webdriverconfiger.WebDriverManager;

import java.util.concurrent.TimeUnit;

public class BaseTestClass {

    Server serverToTest = Server.GRV;

    protected WebDriver driver;

    protected Wait<WebDriver> wait;

    @BeforeSuite
    public void initializeTestServer(){
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

        driver = new WebDriverManager().getDriver(browser);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WaitManager(driver).getWait();
    }

    @AfterMethod
    public void endTest(ITestResult result) {
        String name = result.getName();
        System.out.println(name);
        driver.manage().deleteAllCookies();
    }

    @Parameters("browser")
    @AfterClass(alwaysRun = true)
    public void tearDownTestClass(@Optional("chrome") String browser){
        try {
            driver.findElement(new HeaderMenu(driver).logOutButton).click();
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
    public void endTest(@Optional("chrome") String browser, ITestContext iTestContext) {
        String s = iTestContext.getCurrentXmlTest().getName();
        System.out.println("Suite Name: " + s);
        if(s.equals("Chrome Suite")) {
            new UserActions(driver).deleteUnnecessarySites();
        }
        new WebDriverManager().quitAllDrivers();
    }

    public WebDriver getDriver() {
        return driver;
    }

    //accessToken=eyJ1aWQiOiJ0ZXN0QGdyYXZpdGVjLm5ldCIsInJlcXVlc3RlZFVpZCI6bnVsbCwicm9sZSI6IkFETUlOIiwidHlwZSI6IlVTRVIiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsImV4cCI6MTUxNjEwMTE5MH0.e4hQ4JAsH4mYd8P3VkTB8Rd4H_CjQSJUYedWrVQ50V8

}