package actions;

import com.selenium.MailService;
import com.selenium.enums.Server;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import pageobjects.*;
import pageutils.NavigationUtil;
import testconfigs.baseconfiguration.TestServerConfiguretion;
import testconfigs.testdatamanagers.TestSiteManager;
import testconfigs.testdatamanagers.TestUserManager;
import webdriverconfiger.WaitManager;

import java.util.stream.Collectors;

import static com.selenium.enums.Protocol.HTTP;
import static com.selenium.enums.Protocol.HTTPS;


public class UserActions {
    private WebDriver driver;
    private Wait<WebDriver> wait;
    Logger Log = LogManager.getLogger(UserActions.class);

    public UserActions(WebDriver driver, Wait<WebDriver> wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public UserActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitManager(driver).getWait();
    }

    public String createSite(String siteUrl) throws Exception {
        new LogInPage(driver).login(new TestUserManager().getEmail(), new TestUserManager().getPassword());
        return new AddNewSitePage(driver).createSite(siteUrl);
    }

    public void createSite(String email, String pass, String siteUrl) throws Exception {
        new LogInPage(driver).login(email, pass);
        new AddNewSitePage(driver).createSite(siteUrl);
    }

    public void checkCreateSiteMail(String siteUrl) throws Exception {
        BrowserMaster browserMaster = new BrowserMaster(driver);
        Verifier verifier = new Verifier();
        String createdSite = MailService.getCreatedSiteUrl();
        if (driver instanceof ChromeDriver) {
            browserMaster.openNewTab(createdSite);
            verifier.assertTrue(driver.getCurrentUrl().contains(siteUrl));
            browserMaster.switchToMainTab();
        }
    }

    public MainAdminPage deleteSite(String siteUrl) {

        SiteSettingsPage siteSettingsPage = new NavigationUtil(driver).open(SiteSettingsPage.class, siteUrl);

        for (int i = 0; i <= 40; i++) {
            boolean popUp = false;
            try {
                siteSettingsPage.clickDelete();
                Timer.waitSeconds(0.2);
                popUp = siteSettingsPage.getConfirmPopUpBtn().isDisplayed();
                siteSettingsPage.clickConfirmPopUpButton();
            } catch (NoSuchElementException e) {
                System.out.println();
            } catch (ElementNotVisibleException e) {
                System.out.println();
            }
            if (popUp) break;
        }
        wait.until(ExpectedConditions.invisibilityOfElementLocated(siteSettingsPage.cancelPopUpButton));
        siteSettingsPage.clickConfirmPopUpButton();
        return new MainAdminPage(driver);
    }

    public void createNewUser() throws Exception {
        NavigationUtil navigationUtil = new NavigationUtil(driver);
        int emailNumber = new TestUserManager().getEmailNumber();
        String email = "grovitek+" + emailNumber + "@gmail.com";
        String pass = new TestUserManager().getPassword();
        if (pass.equals("qqqq1111")) pass = "tttt1111";
        try {
            navigationUtil.open(RegistrationPage.class)
                    .setUserCridentials(email, pass);
            emailNumber = emailNumber + 2;
            new TestUserManager().setEmailNumber(emailNumber);
            String link = MailService.getConfirmationLink();
            System.out.println("CONFIRMATION LINK: " + link);
            driver.navigate().to(link);
        } catch (Exception e) {
            e.printStackTrace();
        }
        emailNumber = emailNumber + 2;
        new TestUserManager().setEmail(email, emailNumber);
        new TestUserManager().setPassword(pass);
    }

    private void managePopUp() {
        if (TestServerConfiguretion.iTest.equals(Server.GRV_7700)) {
            try {
                Timer.waitSeconds(1);
                driver.findElement(By.cssSelector("button[ng-click=\"$close()\"]")).click();
            } catch (NoSuchElementException e) {
                System.out.println("No pop up");
            }
        }
    }

    public void subscribe(String siteToSubscribe) {
        driver.get(siteToSubscribe);
        if (driver instanceof FirefoxDriver) {
            oneClickSubscribe();
        } else {
            twoClicksSubscribe();
        }
    }

    private void oneClickSubscribe() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[class=\"modal-body-button-text\"]"))).click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        wait.until(ExpectedConditions.numberOfWindowsToBe(1));
    }

    private void twoClicksSubscribe() {
        try {
            driver.findElement(By.cssSelector("button[id=\"gravitec-prompt-allow-button\"]")).click();
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            wait.until(ExpectedConditions.numberOfWindowsToBe(1));
        } catch (NoSuchElementException no) {
            Timer.waitSeconds(5);
            try {
                driver.findElement(By.cssSelector("button[id=\"gravitec-prompt-allow-button\"]")).click();
                wait.until(ExpectedConditions.numberOfWindowsToBe(2));
                wait.until(ExpectedConditions.numberOfWindowsToBe(1));
            } catch (NoSuchElementException noEl) {

            }
        }
    }

    public void handleAds() {
        try {
            driver.findElement(By.cssSelector("div[class=\"title_popup\"]"));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class=\"close_popup appeared\"]"))).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class=\"title_popup\"]")));
        } catch (NoSuchElementException exc) {
        }
    }

    public void addNewTag(String testSite, String... newTag) {
        JSRunner jsRunner = new JSRunner(driver);
        try {
            subscribe(testSite);
        } catch (TimeoutException | NoSuchElementException timeExc) {
            Log.info("Looks like you are already subscribed. Let`s try to add a tag");
        }
        for (int j = 0; j < newTag.length; j++) {
            for (int i = 0; i < 20; i++) {
                jsRunner.addNewTag(newTag[j]);
            }
            Log.info("New tag: " + newTag[j]);
        }
    }

    public void addNewAlias(String testSite, String alias) {
        JSRunner jsRunner = new JSRunner(driver);
        try {
            subscribe(testSite);
        } catch (TimeoutException | NoSuchElementException timeExc) {
            Log.info("Looks like you are already subscribed. Let`s try to set alias");
        }
        for (int i = 0; i < 20; i++) {
            jsRunner.addNewAlias(alias);
        }
        Log.info("New alias: " + alias);
    }

    public boolean handleErrorPopUp() {
        boolean isError = false;
        boolean displayed = false;
        Timer.waitSeconds(0.5);
        try {
            displayed = driver.findElement(By.cssSelector("div[class=\"sa-icon sa-error\"]")).isDisplayed();
        } catch (NoSuchElementException el) {
        }
        if (displayed) {
            driver.findElement(By.cssSelector("button[ng-bind=\"ok | translate\"]")).click();
            isError = true;
        }

        return isError;
    }

    public void scrollTo(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void deleteUnnecessarySites() {
        String necessarySite1 = new TestSiteManager().getNewTestSiteUrl(HTTP);
        String necessarySite2 = new TestSiteManager().getNewTestSiteUrl(HTTPS);
        String necessarySite3 = new TestSiteManager().getOldTestSiteUrl(HTTP);
        String necessarySite4 = new TestSiteManager().getOldTestSiteUrl(HTTPS);

//        if(!driver.getCurrentUrl().contains("/login")){
//            new LogInPage(driver).login(TestDataProvider.email, TestDataProvider.pass);
//        }

        MainAdminPage main = new HeaderMenu(driver).clickLogo();

        for (String s : main.getSiteList().stream().map(el -> el.getText())
                .filter(s -> !s.contains(necessarySite1))
                .filter(s -> !s.contains(necessarySite2))
                .filter(s -> !s.contains(necessarySite3))
                .filter(s -> !s.contains(necessarySite4))
                .collect(Collectors.toList())) {
            main.openSite(s)
                    .openSiteSettingsPage();
            deleteSite(s);
            System.out.println("Deleted site: " + s);
        }
    }
}
