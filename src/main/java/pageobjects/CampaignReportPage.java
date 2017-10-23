package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

/**
 * Created by Oleksii on 17.07.2017.
 */
public class CampaignReportPage {
    WebDriver driver;
    Wait<WebDriver> wait;

    public By backToHistoryButton = By.cssSelector("span[ng-bind*=\"'HSTR_PUSH_BACK'\"]");
    public By editButton = By.cssSelector("span[ng-bind*=\"'HSTR_PUSH_EDIT'\"]");
    public By cancelButton = By.cssSelector("span[translate=\"HSTR_PUSH_CANCAMP\"]");
    public By copyButton = By.cssSelector("span[translate=\"COPY\"]");

    public By pushTitle = By.cssSelector("h5[ng-bind=\"vmHistoryPush.message.tl\"]");
    public By pushText = By.cssSelector("h5[ng-bind=\"vmHistoryPush.message.tx\"]");
    public By urlToRedirect = By.cssSelector("a[class*=\"text-info\"][ng-href]");
    public By confirmPopUpButton = By.cssSelector("button[class*=\"confirm\"]");
    public By okPopUpButton = By.cssSelector("button[ng-bind*=\"ok\"]");


    public By alias = By.cssSelector("span[ng-bind=\"alias.name\"]");
    public By tag = By.cssSelector("span[ng-bind=\"tag.name\"]");
    public By redirect = By.cssSelector("a[class=\"text-info ng-binding\"]");

    public CampaignReportPage(WebDriver driver, Wait<WebDriver> wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void copyCampaign() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(copyButton)).click();
    }

    public void cancelCampaign() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cancelButton)).click();
        clickConfirmPopUpButton();
        clickOkPopUpButton();
    }

    public CreateCampaignPage clickEditCampaign() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(editButton)).click();
        return new CreateCampaignPage(driver, wait);
    }

    public void clickConfirmPopUpButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPopUpButton)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(confirmPopUpButton));
    }

    public void clickOkPopUpButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(okPopUpButton)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(okPopUpButton));
    }

    public boolean verifyMessageDelayed() {
        boolean delayed = false;
        WebElement copy = null;
        wait.until(ExpectedConditions.visibilityOfElementLocated(copyButton));
        try {
            copy = editButton.findElement(driver);
        } catch (org.openqa.selenium.NoSuchElementException noEl) {
        } finally {
            if (copy != null) {
                if (copy.isDisplayed()) {
                    delayed = true;
                }
            }
        }
        return delayed;
    }

    public String getPushTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pushTitle)).getText();
    }

    public String getPushText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pushText)).getText();
    }

    public String getRedirectURL() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(urlToRedirect)).getText();
    }

    public String getUTM_source() {
        String utm_source = wait.until(ExpectedConditions.visibilityOfElementLocated(redirect)).getText().split("utm_source=")[1].split("&")[0];
        return utm_source;
    }

    public String getUTM_medium() {
        String utm_medium = wait.until(ExpectedConditions.visibilityOfElementLocated(redirect)).getText().split("utm_medium=")[1].split("&")[0];
        return utm_medium;
    }

    public String getUTM_campaign() {
        String utm_campaign = wait.until(ExpectedConditions.visibilityOfElementLocated(redirect)).getText().split("utm_campaign=")[1].split("&")[0];
        return utm_campaign;
    }
}
