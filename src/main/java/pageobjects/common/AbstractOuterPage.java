package pageobjects.common;

import org.openqa.selenium.WebDriver;

public abstract class AbstractOuterPage extends AbstractPage {

    protected AbstractOuterPage(final WebDriver driver) {
        super(driver);
    }

    protected AbstractOuterPage(final WebDriver driver, int timeout) {
        super(driver);
    }
}
