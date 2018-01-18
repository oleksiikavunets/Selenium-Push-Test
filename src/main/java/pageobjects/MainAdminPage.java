package pageobjects;

import actions.Timer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pageobjects.common.AbstractAdminPage;
import pageobjects.common.annotations.PartialPath;

import java.util.List;


@PartialPath(value = "/")
public class MainAdminPage extends AbstractAdminPage {

    Logger Log = LogManager.getLogger(MainAdminPage.class);

    private By siteName = By.cssSelector("a[ng-bind=\"site.url\"]");
    private By siteList = By.cssSelector("div[class*=\"table-sites-list\"]");
    private By addNewSiteButton = By.cssSelector("span[data-ng-bind-html=\"'LIST_ADD_NEW' | translate\"]");
    private By amountOfAllSubscribers = By.cssSelector("span[ng-bind*=\"(vmList.currentUser.followers)\"]");
    private By amountOfSiteSucsribers = By.cssSelector("td[ng-bind*=\"(site.totalFollowers)\"]");


    public MainAdminPage(WebDriver driver){
        super(driver);
    }

    public int getTotalAmountOfSubscribers(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(amountOfAllSubscribers));
        Timer.waitSeconds(1);
        return Integer.valueOf(driver.findElement(amountOfAllSubscribers).getText());
    }

    public int getAmountOfSiteSubscribers(String siteName){

        return Integer.valueOf(driver.findElement(By.xpath("//a[text()='" + siteName + "']/parent::td[@class=\"site-name\"]/following-sibling::td")).getText());
    }

    public boolean verifySitePresent(String site) {
        boolean present = false;
        WebElement mySite = null;
        wait.until(ExpectedConditions.visibilityOfElementLocated(siteName));
        List<WebElement> sites = driver.findElements(siteName);
        for (WebElement s : sites) {
            if (s.getText().equals(site)) {
                present = true;
            }
        }
        return present;
    }

    public AddNewSitePage clickAddNewSiteButton(){
        wait.until(ExpectedConditions.presenceOfElementLocated(addNewSiteButton)).click();
        return new AddNewSitePage(driver);
    }

    public SideBar openSite(){
        String currentUrl = driver.getCurrentUrl();
        wait.until(ExpectedConditions.visibilityOfElementLocated(siteName)).click();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentUrl)));
        return new SideBar(driver);
    }

    public SideBar openSite(String siteToOpen) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(siteName));
        String currentUrl = driver.getCurrentUrl();

        List<WebElement> siteList = driver.findElements(siteName);

        for (WebElement site : siteList) {
            if (site.getText().equalsIgnoreCase(siteToOpen)) {
                site.click();
                break;
            }
        }
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentUrl)));
        Log.info("TEST SITE: " + siteToOpen);
        return new SideBar(driver);
    }

    public void verifySiteToBeDeleted(String siteUrl) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(siteList));
        List<WebElement> siteList = driver.findElements(siteName);
        for (WebElement s : siteList) {
            Assert.assertFalse(s.getText().equals(siteUrl), "HA!!! Found your site!! It`s not deleted))");
        }
    }

    public List<WebElement> getSiteList(){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(siteName));
        return siteName.findElements(driver);
    }

    public String getSiteId(String site){
        String a =  driver.findElement(By.xpath("//a[text()='" + site + "']/../following-sibling::td/a")).getAttribute("href").split("/")[4];
        System.out.println(a);
   return a;
    }
}
