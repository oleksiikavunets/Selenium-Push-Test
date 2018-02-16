package pageobjects;

import actions.Timer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.common.AbstractAdminPage;
import pageobjects.common.annotations.PartialPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@PartialPath(value = "/sites/SITE_ID/history")
public class CampaignHistoryPage extends AbstractAdminPage {

    private By message = By.cssSelector("span[class=\"text-strong-600 ng-binding\"]");
    private By delayedLabel = By.cssSelector("span.label[ng-if=\"message.delayed\"]");
    private By pageNumber = By.cssSelector("a[ng-click*=\"Pagination.changePage\"][data-ng-bind=\"page\"]");
    private By startPagination = By.cssSelector("a[ng-click*=\"vmPagination.changePage\"]");
    private By endPagination = By.cssSelector("a[ng-click*=\"vmPagination.getPages\"]");

    public CampaignHistoryPage(WebDriver driver) {
        super(driver);
    }

    public CampaignReportPage openMessage(String title) {
        searchForMessage(title).click();
        return new CampaignReportPage(driver);
    }

    public WebElement searchForMessage(String mes) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(message));
        WebElement searchElement = null;

        for (int i = 0; i < 3; i++) {
            List<WebElement> messages = driver.findElements(message);
            for (WebElement m : messages) {
                if (mes.equals(m.getText())) {
                    searchElement = m;
                    break;
                }
            }
            if (searchElement == null) {
                driver.navigate().refresh();
                new HeaderMenu(driver).waitForBeingLogged();
                Timer.waitSeconds(2);
            } else break;
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
        if (searchElement == null)
            System.out.println("Could not find message....................");
        return searchElement;
    }

    public WebElement searchForMes(String message) {
        return new ArrayList<>(Arrays.asList(
                Thread.currentThread().getStackTrace()))
                .stream().map(el -> el.getMethodName())
                .filter(e -> e.toLowerCase().contains("delayed"))
                .collect(Collectors.toList())
                .size() > 0 ? searchForDelayedMessage(message) : searchForNonDelayedMessage(message);
    }

    private WebElement searchForDelayedMessage(String mes) {
        int page = 1;
        WebElement element = null;
        while (driver.findElements(delayedLabel).size() > 0) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(message));
            List<WebElement> messages = driver.findElements(message);
            for(WebElement m : messages) {
                if (m.getText().equals(mes)) {
                    element = m;
                }
            }
            openPage(++page);
        }
        return element;
    }

    private WebElement searchForNonDelayedMessage(String message) {
        return searchForMes(message);
    }

    private void openPage(int page) {
        String url = driver.getCurrentUrl();
        List<WebElement> pages = driver.findElements(pageNumber);
        for (WebElement p : pages) {
            if (p.getText().equals(String.valueOf(page))) {
                System.out.println(p.getText());
                p.click();
            }
        }
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(url)));
    }

    public boolean verifyMessageExists(String mes) {
        boolean found = false;
        WebElement messageToFind = searchForMessage(mes);

        if (messageToFind != null) {
            found = true;
        }
        return found;
    }

    private int getPagesAmount() {
        int amount = 1;
        try {
            driver.findElement(endPagination).click();
        } catch (org.openqa.selenium.NoSuchElementException e) {
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageNumber));
        List<WebElement> pages = driver.findElements(pageNumber);
        if (pages.size() > amount) {
            amount = Integer.valueOf(pages.get(pages.size() - 1).getText());
            try {
                driver.findElement(startPagination).click();
            } catch (org.openqa.selenium.NoSuchElementException e) {
            }
        }
        return amount;
    }
}
