package pageutils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import webdriverconfiger.WaitManager;

public class PaginationUtil {

    private WebDriver driver;
    private Wait<WebDriver> wait;

    private By pageNumber = By.cssSelector("a[ng-click*=\"Pagination.changePage\"][data-ng-bind=\"page\"]");

    public PaginationUtil(WebDriver webDriver){
        driver = webDriver;
        wait = new WaitManager(driver).getWait();
    }

    public boolean openPage(int page) {
        boolean opened = false;
        String url = driver.getCurrentUrl();
        try {
            driver.findElements(pageNumber).stream()
                    .filter(p -> p.getText().equals(String.valueOf(page)))
                    .findFirst()
                    .orElse(null)
                    .click();
            wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(url)));
            opened = true;
        } catch (java.lang.NullPointerException | org.openqa.selenium.WebDriverException e) {
            System.err.println("Page NO# " + (page - 1) + " is the last page");
        }
        return opened;
    }

    public boolean openPopUpPage(int page) {
        boolean opened = false;
        try {
            driver.findElements(pageNumber).stream()
                    .filter(p -> p.getText().equals(String.valueOf(page)))
                    .findFirst()
                    .orElse(null)
                    .click();
            opened = true;
        } catch (java.lang.NullPointerException | org.openqa.selenium.WebDriverException e) {
            System.err.println("Page NO# " + (page - 1) + " is the last page");
        }
        return opened;
    }




}
