package pageobjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Oleksii on 13.07.2017.
 */
public class HeaderMenu {
    WebDriver driver;
     Wait<WebDriver> wait;



    public By logo = By.cssSelector("a[ui-sref=\"list\"]");
    public By logOutButton = By.cssSelector("span[ng-bind-html*=\"HDR_SGN_OUT\"]");
    public By languageDropDown = By.cssSelector("a[class=\"dropdown-toggle\"]");
    public By language = By.cssSelector("a[ng-class*=\"Header.getLang\"]");
    public By en = By.cssSelector("img[src=\"../../../assets/img/english.png\"]");
    public By ru = By.cssSelector("img[src=\"../../../assets/img/russian.png\"]");
    public By pl = By.cssSelector("img[src=\"../../../assets/img/polish.png\"]");
    public By de = By.cssSelector("img[src=\"../../../assets/img/de.png\"]");

    public HeaderMenu(WebDriver driver, Wait<WebDriver> wait) {
        this.driver = driver;
        this.wait = wait;
        signout.put("en", "Sign out");
        signout.put("pl", "Wyloguj");
        signout.put("ru", "Выйти");
        signout.put("de", "Ausloggen");
    }

    public HashMap<String, String> signout = new HashMap<>();

    public void clickLogo(){
        logo.findElement(driver).click();
    }


    public void logout() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(logOutButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(new LogInPage(driver, wait).buttonSubmit));
    }

    public void openLanguageDropDown() {
        ((JavascriptExecutor) driver).executeScript("document.querySelector('a[class=\"dropdown-toggle\"]').click();");
    }

    public List<WebElement> getAvailableLanguages() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(languageDropDown));
        openLanguageDropDown();
        wait.until(ExpectedConditions.visibilityOfElementLocated(language));
        List<WebElement> langs = driver.findElements(language);
        return langs;
    }

    public void switchLanguage(int i) {

        String langToChange = wait.until(ExpectedConditions.visibilityOfElementLocated(logOutButton)).getText();
        openLanguageDropDown();
        wait.until(ExpectedConditions.visibilityOfElementLocated(language));
        List<WebElement> l = driver.findElements(language);
        wait.until(ExpectedConditions.visibilityOf(l.get(i))).click();
        wait.until(ExpectedConditions.invisibilityOfElementWithText(logOutButton, langToChange));
    }

    public void switchLanguage() {
        String langToChange = wait.until(ExpectedConditions.visibilityOfElementLocated(logOutButton)).getText();
        openLanguageDropDown();
        wait.until(ExpectedConditions.visibilityOfElementLocated(language)).click();
        wait.until(ExpectedConditions.invisibilityOfElementWithText(logOutButton, langToChange));
    }

    public String checkLanguage() {
        String lang = "";

        String mot = wait.until(ExpectedConditions.visibilityOfElementLocated(logOutButton)).getText();

        switch (mot) {
            case ("Sign out"):
                lang = "en";
                break;
            case ("Wyloguj"):
                lang = "pl";
                break;
            case ("Выйти"):
                lang = "ru";
                break;
            case ("Ausloggen"):
                lang = "de";
                break;
        }
        return lang;
    }


    public void switchPl() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(languageDropDown));
        try {
            Assert.assertTrue(driver.findElement(pl).isDisplayed());
        } catch (AssertionError e) {
            openLanguageDropDown();
            wait.until(ExpectedConditions.visibilityOfElementLocated(pl)).click();
        }
    }

    public void switchEng() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(languageDropDown));
        try {
            Assert.assertTrue(driver.findElement(en).isDisplayed());
        } catch (AssertionError e) {
            openLanguageDropDown();
            wait.until(ExpectedConditions.visibilityOfElementLocated(en)).click();
        }
    }

    public void switchRu() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(languageDropDown));
        try {
            Assert.assertTrue(driver.findElement(ru).isDisplayed());
        } catch (AssertionError e) {
            openLanguageDropDown();
            wait.until(ExpectedConditions.visibilityOfElementLocated(ru)).click();
        }
    }

    public void switchDe() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(languageDropDown));
        try {
            Assert.assertTrue(driver.findElement(de).isDisplayed());
        } catch (AssertionError e) {
            openLanguageDropDown();
            wait.until(ExpectedConditions.visibilityOfElementLocated(de)).click();
        }
    }



    public void switchNextLanguage() {
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
    }




}



