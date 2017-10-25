package pageobjects;

import actions.Timer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import java.util.List;


/**
 * Created by Oleksii on 13.07.2017.
 */
public class MainAdminPage {
    WebDriver driver;
    Wait<WebDriver> wait;

    Logger Log = LogManager.getLogger(LogInPage.class);

    public By siteName = By.cssSelector("a[ng-bind=\"site.url\"]");
    public By siteList = By.cssSelector("div[class*=\"table-sites-list\"]");
    public By addNewSiteButton = By.cssSelector("span[data-ng-bind-html=\"'LIST_ADD_NEW' | translate\"]");
    public By amountOfAllSubscribers = By.cssSelector("span[ng-bind*=\"(vmList.currentUser.followers)\"]");
    public By amountOfSiteSucsribers = By.cssSelector("td[ng-bind*=\"(site.totalFollowers)\"]");

    public MainAdminPage(WebDriver driver, Wait<WebDriver> wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public int getAmountOfSubscribers(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(amountOfAllSubscribers));
        Timer.waitSeconds(0.5);
        return Integer.valueOf(driver.findElement(amountOfSiteSucsribers).getText());
    }

    public void verifySitePresent(String site) {
        WebElement mySite = null;
        wait.until(ExpectedConditions.visibilityOfElementLocated(siteName));
        List<WebElement> sites = driver.findElements(siteName);
        for (WebElement s : sites) {
            if (s.getText().equals(site)) {
                mySite = s;
            }
        }
        Assert.assertEquals(mySite.getText(), site);
    }



    public AddNewSitePage clickAddNewSiteButton(){
        wait.until(ExpectedConditions.presenceOfElementLocated(addNewSiteButton)).click();
        return new AddNewSitePage(driver, wait);
    }

    public SideBar openSite(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(siteName)).click();
        return new SideBar(driver, wait);
    }

    public SideBar openSite(String siteToOpen) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(siteName));

        List<WebElement> siteList = driver.findElements(siteName);

        for (WebElement site : siteList) {

            if (site.getText().equalsIgnoreCase(siteToOpen)) {
                site.click();
                break;
            }
        }
        Log.info("TEST SITE: " + siteToOpen);
        return new SideBar(driver, wait);

    }

    public void verifySiteToBeDeleted(String siteUrl) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(siteList));
        List<WebElement> siteList = driver.findElements(siteName);

        for (WebElement s : siteList) {
            Assert.assertFalse(s.getText().equals(siteUrl), "HA!!! Found your site!! It`s not deleted))");
        }

    }


}
