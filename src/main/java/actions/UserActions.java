package actions;

import com.selenium.ConfigTest;
import com.selenium.MailService;
import com.selenium.TestSiteManager;
import com.selenium.enums.Server;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import pageobjects.*;
import webdriverconfiger.WaitManager;

import java.util.stream.Collectors;

import static com.selenium.enums.Server.WPUSH;


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
        AddNewSitePage addNewSitePage = new AddNewSitePage(driver);
        LogInPage logInPage = new LogInPage(driver);
        ConfigTest config = new ConfigTest();

        int emailNumber = Integer.valueOf(config.getEmailNumber()) - 2;
        String email = "grovitek+" + emailNumber + "@gmail.com";
        String pass = config.getPassword();
        logInPage.login(email, pass);

        String script = addNewSitePage.createSite(siteUrl);
        return script;
    }

    public void createSite(String email, String pass, String siteUrl) throws Exception {
        AddNewSitePage addNewSitePage = new AddNewSitePage(driver);
        LogInPage logInPage = new LogInPage(driver);

        logInPage.login(email, pass);
        addNewSitePage.createSite(siteUrl);
    }

    public void checkCreateSiteMail(String siteUrl, String browser) throws Exception {
        BrowserMaster browserMaster = new BrowserMaster(driver);
        Verifier verifier = new Verifier();
        String createdSite = MailService.getCreatedSiteUrl();
        if (browser.equalsIgnoreCase("chrome")) {
            browserMaster.openNewTab(createdSite);
            verifier.assertTrue(driver.getCurrentUrl().contains(siteUrl));
            browserMaster.switchToMainTab();
        }
    }

    public MainAdminPage deleteSite(String siteUrl) {
        SiteSettingsPage siteSettingsPage = new SiteSettingsPage(driver);
        try {
            new SideBar(driver).openSiteSettingsPage();
        } catch (NoSuchElementException e) {
            new MainAdminPage(driver).openSite(siteUrl)
            .openSiteSettingsPage();
        }
        for (int i = 0; i <= 40; i++) {
            boolean popUp = false;
            try {
                siteSettingsPage.clickDelete();
                Timer.waitSeconds(0.2);
                popUp = siteSettingsPage.getConfirmPopUpBtn().isDisplayed();
                siteSettingsPage.clickConfirmPopUpButton();
            } catch (NoSuchElementException e) {
                e.printStackTrace();
            }catch (ElementNotVisibleException e){
                e.printStackTrace();
            }
            if (popUp) break;
        }
        wait.until(ExpectedConditions.invisibilityOfElementLocated(siteSettingsPage.cancelPopUpButton));
        siteSettingsPage.clickConfirmPopUpButton();
        return new MainAdminPage(driver);
    }

    public void createNewUser() throws Exception {
        RegistrationPage register = new RegistrationPage(driver);
        ConfigTest config = new ConfigTest();
        int emailNumber = Integer.valueOf(config.getEmailNumber());
        String pass = config.getPassword();
        if (pass.equals("qqqq1111")) pass = "tttt1111";
        try {
            driver.navigate().to(config.getStartUrl() + "/register");
            managePopUp();
            wait.until(ExpectedConditions.presenceOfElementLocated(register.submit));
            register.setUserCridentials("grovitek+" + emailNumber + "@gmail.com", pass);
            String link = MailService.getConfirmationLink();
            System.out.println("CONFIRMATION LINK: " + link);
            driver.navigate().to(link);
        } catch (Exception e) {
            e.printStackTrace();
        }
        emailNumber = emailNumber + 2;
        config.setEmailNumber(emailNumber);
        config.setPassword(pass);
        Timer.waitSeconds(10);
    }

    private void managePopUp() {
        if (ConfigTest.iTest.equals(Server.GRV_7700)) {
            try {
                Timer.waitSeconds(1);
                driver.findElement(By.cssSelector("button[ng-click=\"$close()\"]")).click();
            } catch (NoSuchElementException e) {
                System.out.println("No pop up");
            }
        }
    }

    public void subscribe(String browser, String site) {
        driver.get(site);
        if (browser.equalsIgnoreCase("firefox")) {
            oneClickSubscribe();
        } else {
            if (ConfigTest.iTest.equals(WPUSH)) {
                oneClickSubscribe();
            } else {
                twoClicksSubscribe();
            }
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

    public void addNewTag(String browser, String testSite, String... newTag) throws Exception {
        JSRunner jsRunner = new JSRunner(driver);
        try {
            subscribe(browser, testSite);
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

    public void addNewAlias(String browser, String testSite, String alias) throws Exception {
        JSRunner jsRunner = new JSRunner(driver);
        try {
            subscribe(browser, testSite);
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
        TestSiteManager testSiteManager = new TestSiteManager();
        String necessarySite1 = testSiteManager.getHttpSiteUrl();
        String necessarySite2 = testSiteManager.getHttpsSiteUrl();
        String necessarySite3 = testSiteManager.getTestSiteUrl();

//        if(!driver.getCurrentUrl().contains("/login")){
//            new LogInPage(driver).login(TestData.email, TestData.pass);
//        }

        MainAdminPage main = new HeaderMenu(driver).clickLogo();

        for (String s : main.getSiteList().stream().map(el -> el.getText())
                .filter(s -> !s.contains(necessarySite1))
//                .filter(s -> !s.contains(necessarySite2))
                .filter(s -> !s.contains(necessarySite3))
                .collect(Collectors.toList())) {
            deleteSite(s);
            System.out.println("Deleted site: " + s);
        }
    }
}
