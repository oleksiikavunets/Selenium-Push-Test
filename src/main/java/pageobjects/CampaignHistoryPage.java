package pageobjects;

import actions.Timer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import java.util.List;
import java.util.Random;

/**
 * Created by Oleksii on 17.07.2017.
 */
public class CampaignHistoryPage {
    WebDriver driver;
    Wait<WebDriver> wait;

    public By message = By.cssSelector("span[class=\"text-strong-600 ng-binding\"]");
    public By pageNumber = By.cssSelector("a[ng-click*=\"Pagination.changePage\"][data-ng-bind=\"page\"]");
    public By startPagination = By.cssSelector("a[ng-click*=\"vmPagination.changePage\"]");
    public By endPagination = By.cssSelector("a[ng-click*=\"vmPagination.getPages\"]");

    public CampaignHistoryPage(WebDriver driver, Wait<WebDriver> wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public CampaignReportPage openMessage(String title) {
        searchForMessage(title).click();
        return new CampaignReportPage(driver, wait);
    }

    public WebElement searchForMessage(String mes) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(message));
        WebElement searchElement = null;
        List<WebElement> messages = driver.findElements(message);
        Random random = new Random();

/**
        for (WebElement m : messages) {
           if (mes.equals(m.getText())) {
             searchElement = m;
              break;
           }
       }
 */
       for (int j = 0; j <= 100; j++) {
            int rand = random.nextInt(messages.size());
            WebElement m = messages.get(rand);
            if (m.getText().equals(mes)) {
                searchElement = m;
                break;
            }
        }


        if (searchElement == null) {
            int maxPages = getPagesAmount();

            quit:
            for (int i = 1; i <= maxPages; i++) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(message));
                List<WebElement> allMessages = driver.findElements(message);

                for (WebElement m : allMessages) {
                    if (mes.equals(m.getText())) {
                        searchElement = m;
                        break quit;
                    }
                }

                wait.until(ExpectedConditions.visibilityOfElementLocated(pageNumber));
                List<WebElement> pages = driver.findElements(pageNumber);
                for (WebElement p : pages) {
                    if (Integer.valueOf(p.getText()) == i) {
                        p.click();
                        break;
                    }
                }
            }
        }
        return searchElement;
    }

    public boolean verifyMessageExists(String mes) {
        boolean found = false;
        WebElement messageToFind = searchForMessage(mes);

        if (messageToFind != null) {
            found = true;
        }
        return found;
    }

    public int getPagesAmount() {
        int amount;
        try {
            driver.findElement(endPagination).click();
        } catch (org.openqa.selenium.NoSuchElementException e) {
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageNumber));
        List<WebElement> pages = driver.findElements(pageNumber);
        amount = Integer.valueOf(pages.get(pages.size() - 1).getText());
        try {
            driver.findElement(startPagination).click();
        } catch (org.openqa.selenium.NoSuchElementException e) {
        }
        return amount;
    }
}
