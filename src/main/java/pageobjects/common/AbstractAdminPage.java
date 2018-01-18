package pageobjects.common;

import org.openqa.selenium.WebDriver;

public abstract class AbstractAdminPage extends AbstractPage {

    protected AbstractAdminPage(final WebDriver driver) {
        super(driver);
    }

    protected AbstractAdminPage(final WebDriver driver, int timeout){
        super(driver, timeout);
    }


}
