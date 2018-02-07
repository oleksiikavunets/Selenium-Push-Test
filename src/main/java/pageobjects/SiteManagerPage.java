package pageobjects;

import actions.Timer;
import com.selenium.enums.Protocol;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pageobjects.common.AbstractPage;

import static com.selenium.enums.Protocol.HTTP;
import static com.selenium.enums.Protocol.HTTPS;
import static testconfigs.testdatamanagers.TestServerConfiguretionSiteManager.getNewTestSiteUrl;

public class SiteManagerPage extends AbstractPage{

    public static final String SITE_MANAGER_URL = "https://sitemanager.mpsdevelopment.com";

    public static final String siteDomain = ".mpsdevelopment.com";

    private static By siteNameInput = By.cssSelector("input[_ngcontent-c1]");
    private static By addBtn = By.cssSelector("div[_ngcontent-c1] button");
    private static By siteName = By.cssSelector("span[class=\"badge\"]");
    private static By deleteSiteBtn = By.cssSelector("button[class=\"delete\"]");
    private static By viewDetailsBtn = By.xpath("//button[text()='View Details']");
    private static By sdkInput = By.xpath("//label[text()='SDK URL: ']/following-sibling::input");
    private static By textarea = By.cssSelector("textarea[_ngcontent-c3][placeholder]");
    private static By saveBtn = By.xpath("//button[2]");

    private static String template = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "putYourScriptHere\n" +
            "<meta charset=\"UTF-8\">\n" +
            "    <meta name=\"robots\" content=\"noindex\" />\n" +
            "    <title>Title</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "<h1>SELENIUM</h1>\n" +
            "</body>\n" +
            "</html>\n" +
            "<p>This is the default web page for test site.</p>\n" +
            "<p>Do not delete!!!</p>\n" +
            "<html>";
    public SiteManagerPage(WebDriver driver){
        super(driver);
    }

    public String createNewSite(String newSiteName) {

        driver.get(SITE_MANAGER_URL);
        inputNewSiteName(newSiteName);
        clickAddBtn();

        Timer.waitSeconds(1);
        driver.get(newSiteName);
        String currentSite = driver.getCurrentUrl();
        System.out.println(currentSite);
        Assert.assertTrue(currentSite.contains(newSiteName), "current: " + currentSite + " is not new site: " + newSiteName);

        return newSiteName;
    }

    public SiteManagerPage setSiteDatas(String site, String script) {
        String oldSite = getOldSiteUrl(site);
        driver.get(SITE_MANAGER_URL);
        openSite(site);
        wait.until(ExpectedConditions.visibilityOfElementLocated(textarea)).clear();
        String newTemplate = template.replace("putYourScriptHere", script);
        textarea.findElement(driver).sendKeys(newTemplate);
        saveBtn.findElement(driver).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(textarea));
        if(!site.equals(oldSite)){
            deleteOldSite(oldSite);
        }
        return this;
    }

    public SiteManagerPage setSiteDatas(String site, String script, String linkSDK){
        String oldSite = getOldSiteUrl(site);
        driver.get(SITE_MANAGER_URL);

        openSite(site);
        wait.until(ExpectedConditions.visibilityOfElementLocated(textarea)).clear();
        String newTemplate = template.replace("putYourScriptHere", script);
        sdkInput.findElement(driver).sendKeys(linkSDK);
        textarea.findElement(driver).sendKeys(newTemplate);
        saveBtn.findElement(driver).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(textarea));
        if(!site.equals(oldSite)){
            deleteOldSite(oldSite);
        }
        return this;
    }

    private SiteManagerPage openSite(String site) {

        driver.findElement(By.xpath("//span[text()='" + site + "']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(viewDetailsBtn)).click();
        return this;
    }

    private SiteManagerPage deleteOldSite(String oldSite){

        try {
            driver.findElement(By.xpath("//span[text()='" + oldSite + "']/following-sibling::button[@class='delete']")).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[text()='" + oldSite + "']")));
            System.out.println("DELETED OLD SITE " + oldSite + " ......................................................");
        }catch (NoSuchElementException e){
            System.out.println("COULD NOT FIND " + oldSite + " TO DELETE................................................");
        }
        return this;
    }

    private String getOldSiteUrl(String newSite){
        Protocol protocol = newSite.contains("https://") ? HTTPS : HTTP;
        return getNewTestSiteUrl(protocol);
    }

    private SiteManagerPage inputNewSiteName(String newSiteName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(siteNameInput)).sendKeys(newSiteName);
        return this;
    }

    private SiteManagerPage clickAddBtn() {
        addBtn.findElement(driver).click();
        return this;
    }

    private String getSiteName(String siteName) {
        String name = "";
        if (siteName.contains("http://")) {
            name = siteName.split("http://")[1];
        } else if (siteName.contains("https://")) {
            name = siteName.split("https://")[1] + "-ssl";
        }
        System.out.println(name);
        return name;
    }


}
