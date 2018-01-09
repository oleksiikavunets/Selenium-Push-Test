package pageobjects;

import actions.Timer;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pageobjects.common.AbstractPage;

public class SideBar extends AbstractPage{

    By createCampaignButton = By.cssSelector("span[ng-bind*=\"'LMENU_NEW_CAMP'\"]");
    By welcomeMessagesButton = By.cssSelector("span[ng-bind*=\"'WLCMMS_TL'\"]");
    By reportsButton = By.cssSelector("span[ng-bind*=\"'LMENU_STAT'\"]");
    By campaignHistoryButton = By.cssSelector("span[ng-bind*=\"'LMENU_HIST'\"]");
    By subscribersButton = By.cssSelector("span[ng-bind*=\"'LMENU_SUBS'\"]");
    By tagListButton = By.cssSelector("span[ng-bind*=\"'LMENU_TAGLIST'\"]");
    By siteSettingsButton = By.cssSelector("span[ng-bind*=\"'LMENU_SETT'\"]");


    public SideBar(WebDriver driver){
        super(driver);
    }

    public CreateCampaignPage openCreateCampaignPage(){
        String currentUrl = driver.getCurrentUrl();
        wait.until(ExpectedConditions.visibilityOfElementLocated(createCampaignButton)).click();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentUrl)));
        assertSiteIdPresent();
//        manageErrorPop();
        return new CreateCampaignPage(driver);
    }

    public WelcomeMessagePage openWelcomeMessagePage(){
        String currentUrl = driver.getCurrentUrl();
        wait.until(ExpectedConditions.presenceOfElementLocated(welcomeMessagesButton)).click();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentUrl)));
        assertSiteIdPresent();
        return new WelcomeMessagePage(driver);
    }

    public CampaignHistoryPage openCampaignHistoryPage(){
        String currentUrl = driver.getCurrentUrl();
        wait.until(ExpectedConditions.visibilityOfElementLocated(campaignHistoryButton)).click();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentUrl)));
        assertSiteIdPresent();
        return new CampaignHistoryPage(driver);
    }

    public SubscribersPage openSubscribersPage(){
        String currentUrl = driver.getCurrentUrl();
        wait.until(ExpectedConditions.visibilityOfElementLocated(subscribersButton)).click();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentUrl)));
        assertSiteIdPresent();
        Timer.waitSeconds(0.5);
        return new SubscribersPage(driver);
    }

    public TagListPage openTagListPage(){
        String currentUrl = driver.getCurrentUrl();
        wait.until(ExpectedConditions.visibilityOfElementLocated(tagListButton)).click();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentUrl)));
        assertSiteIdPresent();
        return new TagListPage(driver);
    }

    public SiteSettingsPage openSiteSettingsPage(){
        String currentUrl = driver.getCurrentUrl();
        Timer.waitSeconds(2);
        driver.findElement(siteSettingsButton).click();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentUrl)));
        assertSiteIdPresent();
        return new SiteSettingsPage(driver);
    }

    private void assertSiteIdPresent(){
        Timer.waitSeconds(0.3);
        String currentUrl = driver.getCurrentUrl();
        String cut = currentUrl.split("sites")[1];
        Assert.assertFalse(cut.contains("//"), "No siteId in current URL: " + currentUrl);
    }

    private void manageErrorPop(){
        try{
            Timer.waitSeconds(1);
            driver.findElement(By.cssSelector("div[class*=\"sweet-alert\"] button[ng-click=\"$close()\"][ng-bind]")).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class*=\"sweet-alert\"] button[ng-click=\"$close()\"][ng-bind]")));
            Timer.waitSeconds(1);
        }catch (NoSuchElementException e){

        }
    }






}
