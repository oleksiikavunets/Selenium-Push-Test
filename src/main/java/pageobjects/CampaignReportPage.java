package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.common.AbstractAdminPage;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Oleksii on 17.07.2017.
 */
public class CampaignReportPage extends AbstractAdminPage {

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
        return this;
    }

    public CampaignReportPage clickOkPopUpButton() {
        okPopUpButton.findElement(driver).click();
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
        return wait.until(ExpectedConditions.visibilityOfElementLocated(redirect)).getText().split("utm_source=")[1].split("&")[0];
    }

    public String getUTM_medium() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(redirect)).getText().split("utm_medium=")[1].split("&")[0];
    }

    public String getUTM_campaign() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(redirect)).getText().split("utm_campaign=")[1].split("&")[0];
    }

    public List<WebElement> getSentTags(){
        List<WebElement> sentTags;
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(tag));
            sentTags = driver.findElements(tag);
        } catch (TimeoutException e){
            throw new NoSuchElementException("COULD NOT FIND ANY SENT TAG ON CAMPAIGN REPORT PAGE.");
        }
        return sentTags;
    }
    public WebElement getSentAlias(){
        WebElement el;
        try {
            el = wait.until(ExpectedConditions.visibilityOfElementLocated(alias));
        }catch (TimeoutException e){
            throw new NoSuchElementException("COULD NOT FIND SENT ALIAS");
        }
        return el;
    }
}
