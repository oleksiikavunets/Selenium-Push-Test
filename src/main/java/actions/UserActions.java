package actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.selenium.ConfigTest;
import com.selenium.MailService;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import pageobjects.*;
import static com.selenium.enums.Server.*;


public class UserActions {
    protected WebDriver driver;
    Wait<WebDriver> wait;
    Logger Log = LogManager.getLogger(UserActions.class);

    public UserActions(WebDriver driver, Wait<WebDriver> wait) {
        this.driver = driver;
        this.wait = wait;
    }


    public void createSite(String siteUrl) throws Exception {
        AddNewSitePage addNewSitePage = new AddNewSitePage(driver, wait);
        LogInPage logInPage = new LogInPage(driver, wait);
        ConfigTest config = new ConfigTest();

        int emailNumber = Integer.valueOf(config.getEmailNumber()) - 2;
        String email = "grovitek+" + emailNumber + "@gmail.com";
        String pass = config.getPassword();
        logInPage.login(email, pass);
        Timer.waitSeconds(3);
        driver.navigate().refresh();
        addNewSitePage.createSite(siteUrl);
    }

    public void createSite(String email, String pass, String siteUrl) throws Exception {
        AddNewSitePage addNewSitePage = new AddNewSitePage(driver, wait);
        LogInPage logInPage = new LogInPage(driver, wait);

        logInPage.login(email, pass);
        addNewSitePage.createSite(siteUrl);
    }

    public void checkCreateSiteMail(String siteUrl, String browser) throws Exception {
        BrowserMaster browserMaster = new BrowserMaster(driver);
        Verifier verifier = new Verifier();
        String createdSite = MailService.getCreatedSiteUrl();
        if(browser.equalsIgnoreCase("chrome")) {
            browserMaster.openNewTab(createdSite);
            verifier.assertTrue(driver.getCurrentUrl().contains(siteUrl));
            browserMaster.switchToMainTab();
        }
    }

    public void deleteSite(String siteUrl) throws InterruptedException {
        SiteSettingsPage siteSettingsPage = new SiteSettingsPage(driver, wait);
        MainAdminPage mainAdminPage = new MainAdminPage(driver, wait);

        try {
            siteSettingsPage.siteSettingsSideButton.findElement(driver).click();
        } catch (NoSuchElementException e) {
            mainAdminPage.openSite(siteUrl);
            siteSettingsPage.openSiteSettings();
        }
        for(int i = 0; i <= 40; i++) {
            boolean popUp = false;
            try {
                siteSettingsPage.clickDelete();
                Timer.waitSeconds(0.2);
                popUp = driver.findElement(siteSettingsPage.confirmPopUpButton).isDisplayed();
                driver.findElement(siteSettingsPage.confirmPopUpButton).click();
            } catch (NoSuchElementException e) {}
            if(popUp)break;
        }
        wait.until(ExpectedConditions.invisibilityOfElementLocated(siteSettingsPage.cancelPopUpButton));
        wait.until(ExpectedConditions.visibilityOfElementLocated(siteSettingsPage.confirmPopUpButton)).click();
    }


    public void createNewUser() throws Exception {
        RegistrationPage register = new RegistrationPage(driver, wait);
        ConfigTest config = new ConfigTest();
        int emailNumber = Integer.valueOf(config.getEmailNumber());
        String pass = config.getPassword();
        if (pass.equals("qqqq1111")) pass = "tttt1111";
        try {
            driver.navigate().to(config.getStartUrl() + "/register");
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

    public void subscribe(String browser, String site) {
        driver.get(site);
        if(browser.equalsIgnoreCase("firefox")){
            oneClickSubsribe();
        }else{
            if(ConfigTest.iTest.equals(GRV_7700)||
                    ConfigTest.iTest.equals(GRV)||
                    ConfigTest.iTest.equals(GRV_7600)){

                twoClicksSubscribe();
            }
        }
    }

    private void oneClickSubsribe(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[class=\"modal-body-button-text\"]"))).click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        wait.until(ExpectedConditions.numberOfWindowsToBe(1));
    }

    private void twoClicksSubscribe(){
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

    public void addNewTag(String browser, String testSite, String newTag) throws Exception {
        JSRunner jsRunner = new JSRunner(driver);
        try {
            subscribe(browser, testSite);
        } catch (TimeoutException | NoSuchElementException timeExc) {
            Log.info("Looks like you are already subscribed. Let`s try to add a tag");
        }
        for (int i = 0; i < 20; i++) {
            jsRunner.addNewTag(newTag);
        }
        Log.info("New tag: " + newTag);
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
}
