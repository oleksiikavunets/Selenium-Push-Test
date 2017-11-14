package pageobjects;


import actions.Custom;
import com.selenium.ConfigTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import testdata.TestData;

import java.io.File;

import static com.selenium.enums.Server.GRV_7700;

/**
 * Created by Oleksii on 17.07.2017.
 */
public class AddNewSitePage {
    WebDriver driver;
    Wait<WebDriver> wait;

    public By addSiteButton = By.cssSelector("label[class*=\"btn-block btn-primary\"]");

    public By httpButton = By.cssSelector("button[ng-click*=\"http://\"]");
    public By httpsButton = By.cssSelector("button[ng-click*=\"https://\"]");
    public By domainInput = By.name("domain");
    public By fileInput = By.cssSelector("input[type='file']");
    //error messages locators
    public By errorHTTPButton = By.cssSelector("button[ng-class*=\"=== 'http://', 'btn-danger'\"]");
    public By errorHTTPSButton = By.cssSelector("button[ng-class*=\"=== 'https://', 'btn-danger'\"]");
    public By protocolMessage = By.cssSelector("div[ng-bind=\"vmAdd.errors.protocolErr | translate\"]");
    public By existsError = By.cssSelector("span[ng-bind=\"'ADD_ALRD_EXIST' | translate\"]");
    public By iconError = By.cssSelector("p[ng-bind=\"vmAdd.imgErrorMessage | translate\"]");
    public By iconError7700 = By.cssSelector("[ng-if*=\"errorIcon\"]");


    public AddNewSitePage(WebDriver driver, Wait<WebDriver> wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void createSite(String siteUrl) throws Exception {

        new MainAdminPage(driver, wait).clickAddNewSiteButton();
        setDomain(siteUrl);
        uploadIcon(TestData.icon);
        wait.until(ExpectedConditions.presenceOfElementLocated(fileInput));
        clickAdd();
        wait.until(ExpectedConditions.visibilityOfElementLocated(new SiteSettingsPage(driver, wait).deleteWebsiteButton));
    }



    public void selectHTTPprotocol(){
        Custom custom = new Custom(driver);
        custom.clickAt(driver.findElement(httpButton));
    }
    public void selectHTTPSprotocol(){
        Custom custom = new Custom(driver);
        custom.clickAt(driver.findElement(httpsButton));
    }

    public void setDomain(String dom) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(domainInput)).sendKeys(dom);
    }

    public void uploadIcon(String path) {
        driver.findElement(fileInput).sendKeys(new File(path).getAbsolutePath());
    }

    public void clickAdd() {
        Custom custom = new Custom(driver);

        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(addSiteButton));
        custom.clickAt(el);
    }

    public WebElement getIconTooBigError(){
        By locator;
        if(ConfigTest.iTest.equals(GRV_7700)){
            locator = iconError7700;
        }else {
            locator = iconError;
        }
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


}

