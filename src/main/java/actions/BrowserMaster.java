package actions;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class BrowserMaster {
    WebDriver driver;
    public BrowserMaster(WebDriver driver){
        this.driver = driver;
    }

    public void openNewTab(){
        JSRunner jsRunner = new JSRunner(driver);
        jsRunner.openNewTab();
        ArrayList<String> tabs_windows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs_windows.get(tabs_windows.size() - 1));
    }
    public void openNewTab(String url){
        JSRunner jsRunner = new JSRunner(driver);
        jsRunner.openNewTab();
        ArrayList<String> tabs_windows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs_windows.get(tabs_windows.size() - 1));
        driver.get(url);
    }

    public void switchToMainTab(){
        JSRunner jsRunner = new JSRunner(driver);
        ArrayList<String> tabs_windows = new ArrayList<>(driver.getWindowHandles());
        jsRunner.closeTab();
        driver.switchTo().window(tabs_windows.get(0));
    }
}
