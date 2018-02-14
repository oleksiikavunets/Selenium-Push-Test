package actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import webdriverconfiger.WaitManager;

import java.util.ArrayList;

public class BrowserMaster {
    WebDriver driver;
    Wait<WebDriver> wait;
    public BrowserMaster(WebDriver driver){
        this.driver = driver;
        wait = new WaitManager(this.driver).getWait();
    }

    public int getNumberOfWindows(){
        return driver.getWindowHandles().size();
    }

    public BrowserMaster openNewTab(){
        JSRunner jsRunner = new JSRunner(driver);
        jsRunner.openNewTab();
        ArrayList<String> tabs_windows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs_windows.get(tabs_windows.size() - 1));
        return new BrowserMaster(driver);
    }
    public BrowserMaster openNewTab(String url){
        JSRunner jsRunner = new JSRunner(driver);
        jsRunner.openNewTab();
        ArrayList<String> tabs_windows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs_windows.get(tabs_windows.size() - 1));
        driver.get(url);
        return new BrowserMaster(driver);
    }

    public BrowserMaster switchToMainTab(){
        JSRunner jsRunner = new JSRunner(driver);
        ArrayList<String> tabs_windows = new ArrayList<>(driver.getWindowHandles());
        jsRunner.closeTab();
        driver.switchTo().window(tabs_windows.get(0));
        return new BrowserMaster(driver);
    }

    public BrowserMaster switchToLastTub(){
        ArrayList<String> tabs_windows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs_windows.get(tabs_windows.size() - 1));
        return new BrowserMaster(driver);
    }

    public BrowserMaster waitForNumberOfWindowsToBe(int number){
        wait.until(ExpectedConditions.numberOfWindowsToBe(number));
        return new BrowserMaster(driver);
    }
}
