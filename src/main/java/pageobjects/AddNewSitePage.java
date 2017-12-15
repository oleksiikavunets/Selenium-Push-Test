package pageobjects;


import actions.Custom;
import com.selenium.ConfigTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import testdata.TestData;
import webdriverconfiger.WaitManager;

import java.io.File;

import static com.selenium.enums.Server.GRV_7700;

/**
 * Created by Oleksii on 17.07.2017.
 */
public class AddNewSitePage {
    private WebDriver driver;
    private Wait<WebDriver> wait;

    public By addSiteButton = By.cssSelector("label[class*=\"btn-block btn-primary\"]");

    public By httpButton = By.cssSelector("button[ng-click*=\"http://\"]");
    public By httpsButton = By.cssSelector("button[ng-click*=\"https://\"]");
    public By domainInput = By.name("domain");
    public By fileInput = By.cssSelector("input[type='file']");
    //error messages locators
    public By errorHTTPBtn = By.cssSelector("[ng-click*='http'][ng-class*=\"protocolErr\"]");
    public By errorHTTPSBtn = By.cssSelector("[ng-click*='https'][ng-class*=\"protocolErr\"]");
    public By protocolMessage = By.cssSelector("div[ng-bind=\"vmAdd.errors.protocolErr | translate\"]");
    public By existsError = By.cssSelector("span[ng-bind=\"'ADD_ALRD_EXIST' | translate\"]");
    public By iconError = By.cssSelector("p[ng-bind=\"vmAdd.imgErrorMessage | translate\"]");
    public By iconError7700 = By.cssSelector("[ng-if*=\"errorIcon\"]");

    public AddNewSitePage(WebDriver driver){
        this.driver = driver;
        wait = new WaitManager(driver).getWait();
    }

    public String createSite(String siteUrl) throws Exception {
        new MainAdminPage(driver).clickAddNewSiteButton();
        setDomain(siteUrl);
        uploadIcon(TestData.icon);
        wait.until(ExpectedConditions.presenceOfElementLocated(fileInput));
        clickAdd();
        return  new SiteSettingsPage(driver).getSiteScript();
    }

    public WebElement getHttpBtn(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(httpButton));
    }

    public WebElement getHttpsBtn(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(httpsButton));
    }

    public WebElement getErrorProtocolMsg(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(protocolMessage));
    }

    public WebElement getErrorSiteExistsMsg(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(existsError));
    }



    public AddNewSitePage selectHTTPprotocol(){
        Custom custom = new Custom(driver);
        custom.clickAt(driver.findElement(httpButton));
        return this;
    }
    public AddNewSitePage selectHTTPSprotocol(){
        Custom custom = new Custom(driver);
        custom.clickAt(driver.findElement(httpsButton));
        return this;
    }

    public AddNewSitePage setDomain(String dom) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(domainInput)).sendKeys(dom);
        return this;
    }

    public AddNewSitePage clearDomainInput(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(domainInput)).clear();
        return this;
    }

    public AddNewSitePage uploadIcon(String path) {
        driver.findElement(fileInput).sendKeys(new File(path).getAbsolutePath());
        return this;
    }

    public AddNewSitePage clickAdd() {
        Custom custom = new Custom(driver);

        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(addSiteButton));
        custom.clickAt(el);
        return this;
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

