package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class SideBar {
    By createCampaignButton = By.cssSelector("span[ng-bind*=\"'LMENU_NEW_CAMP'\"]");
    By welcomeMessagesButton = By.cssSelector("span[ng-bind*=\"'WLCMMS_TL'\"]");
    By reportsButton = By.cssSelector("span[ng-bind*=\"'LMENU_STAT'\"]");
    By campaignHistoryButton = By.cssSelector("span[ng-bind*=\"'LMENU_HIST'\"]");
    By subscribersButton = By.cssSelector("span[ng-bind*=\"'LMENU_SUBS'\"]");
    By tagListButton = By.cssSelector("span[ng-bind*=\"'LMENU_TAGLIST'\"]");
    By siteSettingsButton = By.cssSelector("span[ng-bind*=\"'LMENU_SETT'\"]");

    WebDriver driver;
    Wait<WebDriver> wait;

    public SideBar(WebDriver driver, Wait<WebDriver> wait){
        this.driver = driver;
        this.wait = wait;
    }

    public CreateCampaignPage openCreateCampaignPage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(createCampaignButton)).click();
        return new CreateCampaignPage(driver, wait);
    }

    public WelcomeMessagePage openWelcomeMessagePage(){
        wait.until(ExpectedConditions.presenceOfElementLocated(welcomeMessagesButton)).click();
        return new WelcomeMessagePage(driver, wait);
    }

    public CampaignHistoryPage openCampaignHistoryPage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(campaignHistoryButton)).click();
        return new CampaignHistoryPage(driver, wait);
    }

    public SubscribersPage openSubscribersPage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(subscribersButton)).click();
        return new SubscribersPage(driver, wait);
    }

    public TagListPage openTagListPage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(tagListButton)).click();
        return new TagListPage(driver, wait);
    }

    public SiteSettingsPage openSiteSettingsPage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(siteSettingsButton)).click();
        return new SiteSettingsPage(driver, wait);
    }






}
