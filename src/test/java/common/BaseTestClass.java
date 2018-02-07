package common;

import actions.UserActions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pageobjects.HeaderMenu;
import testconfigs.baseconfiguration.TestParameterizer;
import testconfigs.baseconfiguration.TestServerConfiguretion;
import webdriverconfiger.WaitManager;
import webdriverconfiger.WebDriverManager;

import java.util.concurrent.TimeUnit;

import static common.TestConfiguration.*;

public class BaseTestClass {

    protected WebDriver driver;

    protected Wait<WebDriver> wait;

    @BeforeSuite
    public void configureTestSuite(){
        TestParameterizer.setTestServer(serverToTest);
        TestParameterizer.setTestSitesScope(testSitesScope);
        TestParameterizer.setClickOnPush(clickOnPush);
        TestParameterizer.setFailedTestsRetryCount(failedTestsRetryCount);
    }

    @BeforeTest(alwaysRun = true)
    public void initializeTest() {
        if (TestServerConfiguretion.iTest == null) {
            TestServerConfiguretion.setTestServer(serverToTest);
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

}