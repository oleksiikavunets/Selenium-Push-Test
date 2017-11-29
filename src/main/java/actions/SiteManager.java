package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import webdriverconfiger.WaitManager;
import webdriverconfiger.WebDriverManager;

import java.util.List;

public class SiteManager {
    private static WebDriver driver = WebDriverManager.getDriver();
    private static Wait<WebDriver> wait = WaitManager.getWait();

    public static final String SITE_MANAGER_URL = "https://sitemanager.mpsdevelopment.com";

    public static final String siteDomain = ".mpsdevelopment.com";

    private static By siteNameInput = By.cssSelector("input[_ngcontent-c1]");
    private static By addBtn = By.cssSelector("div[_ngcontent-c1] button");
    private static By siteName = By.cssSelector("span[class=\"badge\"]");
    private static By viewDetailsBtn = By.xpath("//div[2]/button");
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
            "<h1>You are amazing!</h1>\n" +
            "</body>\n" +
            "</html>\n" +
            "<p>This is the default web page for this new site.</p>\n" +
            "<p>Yahoo, Yipee and Yabadabadoo</p>\n" +
            "<html>";


    public static String createNewSite(String siteName) {

        driver.get(SITE_MANAGER_URL);
        inputNewSiteName(siteName);
        clickAddBtn();

        Timer.waitSeconds(1);
        driver.get(siteName);
        String currentSite = driver.getCurrentUrl();
        System.out.println(currentSite);
        Assert.assertTrue(currentSite.contains(siteName), "current: " + currentSite + " is not new site: " + siteName);

        return siteName;
    }

    public static void setScript(String site, String script) {
        driver.get(SITE_MANAGER_URL);
        openSite(site);
        wait.until(ExpectedConditions.visibilityOfElementLocated(textarea)).clear();
        String newTemplate = template.replace("putYourScriptHere", script);
        textarea.findElement(driver).sendKeys(newTemplate);
        saveBtn.findElement(driver).click();
        Timer.waitSeconds(5);
        System.out.println("DONE!!");


    }

    private static void openSite(String site) {

        String siteToOpen = getSiteName(site);
        wait.until(ExpectedConditions.visibilityOfElementLocated(siteName));
        List<WebElement> sites = siteName.findElements(driver);

        for (WebElement s : sites) {
            if (s.getText().equals(siteToOpen)) {
                s.click();
                System.out.println("Found and clicked");
            }
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(viewDetailsBtn)).click();
    }

    private static void inputNewSiteName(String newSiteName) {
        siteNameInput.findElement(driver).sendKeys(newSiteName);
    }

    private static void clickAddBtn() {
        addBtn.findElement(driver).click();
    }

    private static String getSiteName(String siteName) {
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
