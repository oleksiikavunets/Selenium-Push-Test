package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.common.AbstractPage;

import java.util.List;
import java.util.Random;

/**
 * Created by Oleksii on 17.07.2017.
 */
public class CampaignHistoryPage extends AbstractPage{

    private  By message = By.cssSelector("span[class=\"text-strong-600 ng-binding\"]");
    private  By pageNumber = By.cssSelector("a[ng-click*=\"Pagination.changePage\"][data-ng-bind=\"page\"]");
    private  By startPagination = By.cssSelector("a[ng-click*=\"vmPagination.changePage\"]");
    private  By endPagination = By.cssSelector("a[ng-click*=\"vmPagination.getPages\"]");

    public CampaignHistoryPage(WebDriver driver){
       super(driver);
    }

    public CampaignReportPage openMessage(String title) {
        searchForMessage(title).click();
        return new CampaignReportPage(driver);
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
