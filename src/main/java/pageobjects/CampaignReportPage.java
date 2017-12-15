package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.common.AbstractPage;

import java.util.List;

/**
 * Created by Oleksii on 17.07.2017.
 */
public class CampaignReportPage extends AbstractPage{

    private  By backToHistoryButton = By.cssSelector("span[ng-bind*=\"'HSTR_PUSH_BACK'\"]");
    private  By editButton = By.cssSelector("span[ng-bind*=\"'HSTR_PUSH_EDIT'\"]");
    private  By cancelButton = By.cssSelector("span[translate=\"HSTR_PUSH_CANCAMP\"]");
    private  By copyButton = By.cssSelector("span[translate=\"COPY\"]");

    private  By pushTitle = By.cssSelector("h5[ng-bind=\"vmHistoryPush.message.tl\"]");
    private  By pushText = By.cssSelector("h5[ng-bind=\"vmHistoryPush.message.tx\"]");
    private  By urlToRedirect = By.cssSelector("a[class*=\"text-info\"][ng-href]");
    private  By confirmPopUpButton = By.cssSelector("button[class*=\"confirm\"]");
    private  By okPopUpButton = By.cssSelector("button[ng-bind*=\"ok\"]");


    private  By alias = By.cssSelector("span[ng-bind=\"alias.name\"]");
    private  By tag = By.cssSelector("span[ng-bind=\"tag.name\"]");
    private  By redirect = By.cssSelector("a[class=\"text-info ng-binding\"]");

    public CampaignReportPage(WebDriver driver){
        super(driver);
    }

    public CreateCampaignPage copyCampaign() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(copyButton)).click();
        return new CreateCampaignPage(driver);
    }

    public CampaignHistoryPage cancelCampaign() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cancelButton)).click();
        clickConfirmPopUpButton();
        clickOkPopUpButton();
        return new CampaignHistoryPage(driver);
    }

    public CreateCampaignPage clickEditCampaign() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(editButton)).click();
        return new CreateCampaignPage(driver);
    }

    public CampaignReportPage clickConfirmPopUpButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPopUpButton)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(confirmPopUpButton));
        return this;
    }

    public CampaignReportPage clickOkPopUpButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(okPopUpButton)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(okPopUpButton));
        return this;
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

    public List<WebElement> getSentTags(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(tag));
        List<WebElement> sentTags = driver.findElements(tag);
        return sentTags;
    }
    public WebElement getSentAlias(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(alias));
    }
}
