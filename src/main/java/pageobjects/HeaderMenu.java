package pageobjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.common.AbstractAdminPage;
import testconfigs.baseconfiguration.TestServerConfiguretion;
import webdriverconfiger.WaitManager;

import java.util.List;

import static com.selenium.enums.Server.P2B;
import static com.selenium.utils.TextGetter.textOf;

/**
 * Created by Oleksii on 13.07.2017.
 */
public class HeaderMenu extends AbstractAdminPage {

    public By logo = By.cssSelector("a[ui-sref=\"list\"]");
    public By logOutButton = By.cssSelector("span[ng-bind-html*=\"HDR_SGN_OUT\"]");
    public By languageDropDown = By.cssSelector("a[class=\"dropdown-toggle\"]");
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
        wait.until(ExpectedConditions.invisibilityOfElementLocated(logOutButton));
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

    public HeaderMenu switchFirstLanguage(){
        List<WebElement> langs = driver.findElements(language);
        if(langs.size() > 1) {
            String langToChange = textOf(logOutButton, driver);
            langs.get(0).click();
            wait.until(ExpectedConditions.invisibilityOfElementWithText(logOutButton, langToChange));
        }
        return this;
    }

    public HeaderMenu switchLanguage(int i) {
        if (!TestServerConfiguretion.iTest.equals(P2B)) //Push2b.com has only one language version
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
        if (!TestServerConfiguretion.iTest.equals(P2B)) //Push2b.com has only one language version
        {
            String langToChange = wait.until(ExpectedConditions.visibilityOfElementLocated(logOutButton)).getText();
            openLanguageDropDown();
            wait.until(ExpectedConditions.visibilityOfElementLocated(language)).click();
            wait.until(ExpectedConditions.invisibilityOfElementWithText(logOutButton, langToChange));
        }
        return this;
    }

    public String checkLanguage() {
        String mot = textOf(logOutButton, driver);
        return mot.equals("Sign out") ? "en" :
                mot.equals("Wyloguj") ? "pl" :
                        mot.equals("Вийти") ? "ua" :
                                mot.equals("Выйти") ? "ru" :
                                        mot.equals("Ausloggen") ? "de" : null;
    }

    public HeaderMenu switchPl() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(languageDropDown));
        try {
            Assert.assertTrue(driver.findElement(pl).isDisplayed());
        } catch (AssertionError e) {
            openLanguageDropDown();
            wait.until(ExpectedConditions.visibilityOfElementLocated(pl)).click();
        }
        return this;
    }

    public HeaderMenu switchEng() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(languageDropDown));
        try {
            Assert.assertTrue(driver.findElement(en).isDisplayed());
        } catch (AssertionError e) {
            openLanguageDropDown();
            wait.until(ExpectedConditions.visibilityOfElementLocated(en)).click();
        }
        return this;
    }

    public HeaderMenu switchRu() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(languageDropDown));
        try {
            Assert.assertTrue(driver.findElement(ru).isDisplayed());
        } catch (AssertionError e) {
            openLanguageDropDown();
            wait.until(ExpectedConditions.visibilityOfElementLocated(ru)).click();
        }
        return this;
    }

    public HeaderMenu switchDe() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(languageDropDown));
        try {
            Assert.assertTrue(driver.findElement(de).isDisplayed());
        } catch (AssertionError e) {
            openLanguageDropDown();
            wait.until(ExpectedConditions.visibilityOfElementLocated(de)).click();
        }
        return this;
    }

    public HeaderMenu switchNextLanguage() {
        String mot = wait.until(ExpectedConditions.visibilityOfElementLocated(logOutButton)).getText();
        switch (mot) {
            case ("Sign out"):
                switchPl();
                break;
            case ("Wyloguj"):
                switchRu();
                break;
            case ("Выйти"):
                switchDe();
                break;
            default:
                break;
        }
        return this;
    }

    public void waitForBeingLogged() {
        new WaitManager(driver).getWait(60)
        .until(ExpectedConditions.visibilityOfElementLocated(logOutButton));
    }

    public boolean verifyBeingLogged() {
        return logOutButton.findElements(driver).size() > 0;
    }
}



