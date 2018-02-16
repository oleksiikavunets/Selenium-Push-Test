package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.common.AbstractAdminPage;
import pageobjects.common.annotations.PartialPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

@PartialPath(value = "/sites/SITE_ID/history")
public class CampaignHistoryPage extends AbstractAdminPage {

    private By message = By.cssSelector("span[class=\"text-strong-600 ng-binding\"]");
    private By delayedLabel = By.cssSelector("span.label[ng-if=\"message.delayed\"]");
    private By pageNumber = By.cssSelector("a[ng-click*=\"Pagination.changePage\"][data-ng-bind=\"page\"]");

    public CampaignHistoryPage(WebDriver driver) {
        super(driver);
    }

    public CampaignReportPage openMessage(String title) {
        searchForMessage(title).click();
        return new CampaignReportPage(driver);
    }

    public WebElement searchForMessage(String message) {
        return new ArrayList<>(Arrays.asList(
                Thread.currentThread().getStackTrace()))
                .stream().map(el -> el.getMethodName())
                .filter(e -> e.toLowerCase().contains("delayed"))
                .collect(Collectors.toList())
                .size() > 0 ? searchForDelayedMessage(message) :
                searchForNonDelayedMessage(message);
    }

    private WebElement searchForDelayedMessage(String mes) {
        int page = 1;
        while (getAmountOfDelayedMessagesOnPage() > 0 && findMessageOnPage(mes) == null) {
            if (!openPage(++page)) {
                break;
            }
        }
        if (findMessageOnPage(mes) == null) {
            System.err.println("COULD NOT FIND YOUR MESSAGE...................");
        }
        return findMessageOnPage(mes);
    }

    private WebElement searchForNonDelayedMessage(String mes) {
        int page = 1;
        while (getAmountOfDelayedMessagesOnPage() >= 10) {
            openPage(++page);
        }
        for (int i = 0; i < 5; i++) {
            if (findMessageOnPage(mes) == null) {
                driver.navigate().refresh();
            }
        }
        while (findMessageOnPage(mes) == null) {
            if (!openPage(++page)) {
                break;
            }
        }
        if (findMessageOnPage(mes) == null) {
            System.err.println("COULD NOT FIND YOUR MESSAGE...................");
        }
        return findMessageOnPage(mes);
    }

    private WebElement findMessageOnPage(String mes) {
        return driver.findElements(message).stream()
                .filter(webElement -> webElement.getText().equals(mes))
                .findFirst()
                .orElse(null);
    }

    private int getAmountOfDelayedMessagesOnPage() {
        return driver.findElements(delayedLabel).size();
    }

    private boolean openPage(int page) {
        boolean opened = false;
        String url = driver.getCurrentUrl();
        try {
            driver.findElements(pageNumber).stream()
                    .filter(p -> p.getText().equals(String.valueOf(page)))
                    .findFirst()
                    .orElse(null)
                    .click();
            System.out.println("OPENED PAGE #" + page);
            wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(url)));
            opened = true;
        } catch (java.lang.NullPointerException | org.openqa.selenium.WebDriverException e) {
            System.err.println("Page NO# " + (page - 1) + " is the last page");
        }
        return opened;
    }

    public boolean verifyMessageExists(String mes) {
        return searchForMessage(mes) != null;
    }
}
