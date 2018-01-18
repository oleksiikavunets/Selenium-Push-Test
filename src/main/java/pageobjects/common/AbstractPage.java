package pageobjects.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import webdriverconfiger.WaitManager;

public abstract class AbstractPage {

    protected final WebDriver driver;
    protected final Wait<WebDriver> wait;

    protected AbstractPage(final WebDriver driver){
        this.driver = driver;
        wait = new WaitManager(this.driver).getWait();
    }
    protected AbstractPage(final WebDriver driver, int timeout){
        this.driver = driver;
        wait = new WaitManager(this.driver).getWait(timeout);
    }
}
