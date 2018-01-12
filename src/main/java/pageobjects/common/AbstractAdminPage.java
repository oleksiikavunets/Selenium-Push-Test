package pageobjects.common.annotations;

import org.openqa.selenium.WebDriver;
import pageobjects.common.AbstractPage;

public class AbstractAdminPage extends AbstractPage {

    protected AbstractAdminPage(WebDriver driver) {
        super(driver);
    }

    protected AbstractAdminPage(final WebDriver driver, int timeout){
        super(driver, timeout);
    }


}
