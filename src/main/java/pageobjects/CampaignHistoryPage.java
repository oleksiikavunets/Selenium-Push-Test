package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageobjects.common.AbstractAdminPage;
import pageobjects.common.annotations.PartialPath;
import pageutils.PaginationUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

@PartialPath(value = "/sites/SITE_ID/history")
public class CampaignHistoryPage extends AbstractAdminPage {

    private By message = By.cssSelector("span[class=\"text-strong-600 ng-binding\"]");
    private By delayedLabel = By.cssSelector("span.label[ng-if=\"message.delayed\"]");

    public CampaignHistoryPage(WebDriver driver) {
        super(driver);
    }

    public CampaignReportPage openMessage(String title) {
        try {
            searchForMessage(title).click();
        }catch (NullPointerException e){
            throw new NoSuchElementException("COULD NOT FIND MESSAGE WITH TITLE '" + title + "'...........................");
        }
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
            if (!new PaginationUtil(driver).openPage(++page)) {
                break;
            }
        }
        return findMessageOnPage(mes);
    }

    private WebElement searchForNonDelayedMessage(String mes) {
        int page = 1;
        while (getAmountOfDelayedMessagesOnPage() >= 10) {
            new PaginationUtil(driver).openPage(++page);
        }
        for (int i = 0; i < 5; i++) {
            if (findMessageOnPage(mes) == null) {
                driver.navigate().refresh();
            }
        }
        while (findMessageOnPage(mes) == null) {
            if (!new PaginationUtil(driver).openPage(++page)) {
                break;
            }
        }
        return findMessageOnPage(mes);
    }

    private WebElement findMessageOnPage(String mes) {
        return driver.findElements(message).stream()
                .filter(el -> el.getText().equals(mes))
                .findFirst()
                .orElse(null);
    }

    private int getAmountOfDelayedMessagesOnPage() {
        return driver.findElements(delayedLabel).size();
    }

    public boolean verifyMessageExists(String mes) {
        return searchForMessage(mes) != null;
    }
}
