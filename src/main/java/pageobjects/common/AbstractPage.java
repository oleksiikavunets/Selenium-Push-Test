package pageobjects.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import webdriverconfiger.WaitManager;

public abstract class AbstractPage {

    protected WebDriver driver;
    protected Wait<WebDriver> wait;

    protected AbstractPage(final WebDriver driver){
        this.driver = driver;
        wait = new WaitManager(driver).getWait();
    }
    protected AbstractPage(final WebDriver driver, int timeout){
        this.driver = driver;
        wait = new WaitManager(driver).getWait(timeout);
    }
}
