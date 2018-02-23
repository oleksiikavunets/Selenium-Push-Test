package pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.common.AbstractAdminPage;
import webdriverconfiger.WaitManager;

import java.util.List;
import java.util.NoSuchElementException;

import static com.selenium.enums.Server.P2B;
import static utils.TextUtil.textOf;
import static testconfigs.baseconfiguration.TestServerConfiguretion.iTest;

/**
 * Created by Oleksii on 13.07.2017.
 */
public class HeaderMenu extends AbstractAdminPage {

    public By logo = By.cssSelector("a[ui-sref=\"list\"]");
    public By logOutButton = By.cssSelector("span[ng-bind-html*=\"HDR_SGN_OUT\"]");
    public By languageDropDown = By.cssSelector("a[class=\"dropdown-toggle\"]");
    private By siteLang = By.cssSelector("img[ng-if*='Header.getLang']");
    public By language = By.cssSelector("a[ng-class*=\"Header.getLang\"]");
    public By en = By.cssSelector("img[src=\"../../../assets/img/english.png\"]");
    public By ru = By.cssSelector("img[src=\"../../../assets/img/russian.png\"]");
    public By pl = By.cssSelector("img[src=\"../../../assets/img/polish.png\"]");
    public By de = By.cssSelector("img[src=\"../../../assets/img/de.png\"]");


    public HeaderMenu(WebDriver driver) {
        super(driver);
    }

    public MainAdminPage clickLogo() {
        if (driver.getCurrentUrl().contains("/sites/")) {
            logo.findElement(driver).click();
            wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("/sites")));
        }
        return new MainAdminPage(driver);
    }

    public LogInPage logout() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(logOutButton)).click();
        wait.until(l -> new LogInPage(driver).verifyLoginPageOpened());
        return new LogInPage(driver);
    }

    public HeaderMenu openLanguageDropDown() {
        ((JavascriptExecutor) driver).executeScript("document.querySelector('a[class=\"dropdown-toggle\"]').click();");
        return this;
    }

    public List<WebElement> getAvailableLanguages() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(languageDropDown));
        openLanguageDropDown();
        wait.until(ExpectedConditions.visibilityOfElementLocated(language));
        List<WebElement> langs = driver.findElements(language);
        return langs;
    }

    public HeaderMenu switchFirstLanguage() {
        List<WebElement> langs = driver.findElements(language);
        if (langs.size() > 1 &&
                !checkLanguage().equals(langs.get(0)
                        .getAttribute("ng-class")
                        .split("=== '")[1].split("'")[0])) {
            try {
                String langToChange = textOf(logOutButton, driver);
                langs.get(0).click();
                wait.until(ExpectedConditions.invisibilityOfElementWithText(logOutButton, langToChange));
            }catch (ElementNotVisibleException e){
                openLanguageDropDown().switchFirstLanguage();
            }
        }
        return this;
    }

    public HeaderMenu switchLanguage(int i) {
        if (!iTest.equals(P2B)) //Push2b.com has only one language version
        {
            String langToChange = wait.until(ExpectedConditions.visibilityOfElementLocated(logOutButton)).getText();
            openLanguageDropDown();
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(language));
            List<WebElement> ls = driver.findElements(language);
            ls.get(i).click();
            wait.until(ExpectedConditions.invisibilityOfElementWithText(logOutButton, langToChange));
        }
        return this;
    }

    public HeaderMenu switchLanguage() {
        if (!iTest.equals(P2B)) //Push2b.com has only one language version
        {
            String langToChange = wait.until(ExpectedConditions.visibilityOfElementLocated(logOutButton)).getText();
            openLanguageDropDown();
            wait.until(ExpectedConditions.visibilityOfElementLocated(language)).click();
            wait.until(ExpectedConditions.invisibilityOfElementWithText(logOutButton, langToChange));
        }
        return this;
    }

    public String checkLanguage() {
        return siteLang.findElement(driver).getAttribute("ng-if").split("=== '")[1].split("'")[0];
    }

    public void waitForBeingLogged() {
        try {
            new WaitManager(driver).getWait(60)
                    .until(ExpectedConditions.visibilityOfElementLocated(logOutButton));
        } catch (org.openqa.selenium.TimeoutException e) {
            throw new NoSuchElementException("COULD NOT AUTHORIZE.");
        }

    }

    public boolean verifyBeingLogged() {
        return logOutButton.findElements(driver).size() > 0;
    }
}



