package pageobjects;

import actions.Custom;
import actions.Timer;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

/**
 * Created by Oleksii on 14.07.2017.
 */
public class CreateCampaignPage {

    WebDriver driver;
    Wait<WebDriver> wait;

    //push preview block
    public By titlePreview = By.cssSelector("p[data-ng-bind*=\"$ctrl.mtitle \"]");
    public By textPreview = By.cssSelector("p[data-ng-bind*=\"$ctrl.body\"]");
    public By iconPrev = By.cssSelector("div[class*=\"icon\"]>img[src*=\"cdn\"]");
    public By bigImagePrev = By.cssSelector("img[class=\"additional-img\"]");
    public By button1Prev = By.cssSelector("span[ng-bind*=\"$ctrl.btn1.title \"]");
    public By button2Prev = By.cssSelector("span[ng-bind*=\"$ctrl.btn2.title \"]");
    //input fields
    public By titleInput = By.name("title");
    public By textInput = By.name("text");
    public By urlToRedirect = By.name("redirect");
    public By iconInput = By.name("file");
    public By dateInput = By.cssSelector("input[type='text'][date-mask]");
    //UTM
    public By checkboxUTM = By.cssSelector("input[ng-model=\"vmUtmCampaignCtrl.isUtmActive\"]");
    public By inputUTMcampaign = By.cssSelector("input[ng-change*=\"CampaignCtrl.changedUtm()\"]");
    //date
    public By cancelDateButton = By.cssSelector("button[ng-click*=\"CancelDate\"]");

    //additional active items
    public By additionalActiveItems = By.cssSelector("span[ng-bind*=\"'ADDTNL_ELEMNTS'\"]");
    public By button1Switch = By.cssSelector("label[ng-bind*=\"'FRST_BTN'\"]");
    public By button2Switch = By.cssSelector("label[ng-bind*=\"'SND_BTN'\"]");
    public By button1NameInput = By.name("button1Title");
    public By button2NameInput = By.name("button2Title");
    public By button1URL = By.name("url1");
    public By button2URL = By.name("url2");
    public By button1IconDropdown = By.cssSelector("button[class*=\"dropdown\"][ng-disabled*=\"1Button\"]");
    public By button2IconDropdown = By.cssSelector("button[class*=\"dropdown\"][ng-disabled*=\"2Button\"]");
    public By buttonIcon = By.cssSelector("img[src*=\"img/button-icons\"]");
    public By bigImageSwitch = By.cssSelector("label[ng-bind*=\"'ADD_BIG_IMG'\"]");
    public By bigImageInput = By.cssSelector("input[ngf-select*=\"uploadFile\"]");


    //advanced options block and search masks
    public By advancedOptions = By.cssSelector("span[ng-bind*=\"'SEND_PUSH_ADVCD'\"]");
    public By tagSearch = By.cssSelector("input[ng-model=\"vmTagListCtrl.selectedTag\"]");
    public By aliasSearch = By.cssSelector("input[aria-label=\"Select aliases\"]");
    public By browserSearch = By.cssSelector("input[aria-label=\"Select browsers\"]");
    public By countrySearch = By.cssSelector("input[aria-label=\"Select countries\"]");
    public By citySearch = By.cssSelector("input[aria-label=\"Select cities\"]");
    //selected options
    public By tagsToSend = By.cssSelector("td[ng-bind=\"tag.name\"]");
    public By aliasToSend = By.cssSelector("span[uis-transclude-append]");
    public By browserToSend = By.xpath("//div[@title=\"Select browsers\"]//span[2]");
    public By browsersToList = By.cssSelector("div[title=\"Select browsers\"]>div>span>span");
    public By countryTosend = By.xpath("//div[@title=\"Select countries\"]//span[2]");
    public By cityToSend = By.xpath("//div[@title=\"Select cities\"]//span[2]");
    //error messages
    public By requiredTitle = By.xpath("//p[@translate='REQ_FIELD']");
    public By requiredText = By.xpath("//div[contains(@ng-show,'mainForm.text.$error.required')]/p");
    public By linkFormat = By.xpath("//div[contains(@ng-show,'redirect.$error.pattern')]/p");
    public By iconError = By.cssSelector("p[ng-bind*=imgErrorMessage]");
    public By button1TitelError = By.cssSelector("[ng-if*=\"button1Title.$error\"]");
    public By button1URLError = By.cssSelector("[ng-if*=\"url1.$error\"]");
    public By button2TitleError = By.cssSelector("[ng-if*=\"button2Title.$error\"]");
    public By button2URLError = By.cssSelector("[ng-if*=\"url2.$error\"]");
    //send button and "OK" button on pop up
    public By senTestPushButton = By.cssSelector("button[ng-bind*=\"'SEND_PUSH_TEST'\"]");
    public By sendPushButton = By.cssSelector("button[class=\"btn btn-lg btn-block btn-primary ng-binding waves-effect waves-light\"]");
    public By okPopUpButton = By.cssSelector("button[class=\"confirm ng-binding\"][ng-bind=\"ok | translate\"]");

    public CreateCampaignPage(WebDriver driver, Wait<WebDriver> wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public class NotificationPreview {

        public WebElement getTitlePreview() {
            WebElement element = null;
            try {
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(titlePreview));
            } catch (org.openqa.selenium.NoSuchElementException e) {
            }
            return element;
        }

        public WebElement getTextPreview() {
            WebElement element = null;
            try {
                element = textPreview.findElement(driver);
            } catch (org.openqa.selenium.NoSuchElementException e) {
            }
            return element;
        }

        public WebElement getIconPreview() {
            WebElement element = null;
            try {
                element = iconPrev.findElement(driver);
            } catch (org.openqa.selenium.NoSuchElementException e) {
            }
            return element;
        }

        public WebElement getButton1Preview() {
            Timer.waitSeconds(0.5);
            WebElement element = null;
            try {
                element = button1Prev.findElement(driver);
            } catch (org.openqa.selenium.NoSuchElementException e) {
            }
            return element;
        }

        public WebElement getButton2Preview() {
            WebElement element = null;
            try {
                element = button2Prev.findElement(driver);
            } catch (org.openqa.selenium.NoSuchElementException e) {
            }
            return element;
        }

        public WebElement getBigImagePreview() {
            WebElement element = null;
            try {
                element = bigImagePrev.findElement(driver);
            } catch (org.openqa.selenium.NoSuchElementException e) {
            }
            return element;
        }
    }

    public void setTitle(String title) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(titleInput)).sendKeys(title);

    }

    public void setText(String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(textInput)).sendKeys(text);

    }

    public void clearAllInputs() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(titleInput)).clear();
        textInput.findElement(driver).clear();
        urlToRedirect.findElement(driver).clear();
    }


    public void setUrlToRedirect(String url) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(urlToRedirect)).sendKeys(url);
    }

    public void setUTMcampaign(String utm) {
        boolean checked = wait.until(ExpectedConditions.visibilityOfElementLocated(checkboxUTM)).isSelected();
        if(!checked){
            checkboxUTM.findElement(driver).click();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputUTMcampaign)).sendKeys(utm);
    }

    public void setDateAndTime(int year, int month, int day, int hour, int minute) {
        LocalDateTime date = LocalDateTime.of(year, month, day, hour, minute);
        driver.findElement(dateInput).sendKeys(date.format(DateTimeFormatter.ofPattern("dd.MM.YYYY")));
//        ((JavascriptExecutor)driver).executeScript("document.querySelector(\"input[type='text'][time-mask]\").value='11.11';");
        setTime(date);
    }

    public void setDateAndTime(int plusDays, int plusHours, int plusMinutes) {
        LocalDateTime date = LocalDateTime.now().plusDays(plusDays).plusHours(plusHours).plusMinutes(plusMinutes);
        driver.findElement(dateInput).sendKeys(date.format(DateTimeFormatter.ofPattern("dd.MM.YYYY")));
        setTime(date);
    }

    public void setTime(LocalDateTime dateTime) {
        Custom custom = new Custom(driver);
        Actions actions = new Actions(driver);
        String hour = dateTime.format(DateTimeFormatter.ofPattern("HH:mm")).split(":")[0];

        String minute = dateTime.format(DateTimeFormatter.ofPattern("HH:mm")).split(":")[1];

        driver.findElement(By.cssSelector("input[type='text'][time-mask]")).click();
        Timer.waitSeconds(1);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class*=\"hours\"][style=\"visibility: visible;\"]>div[class*=\"jqclockpicker-tick\"]")));
        List<WebElement> hours = driver.findElements(By.cssSelector("div[class*=\"hours\"]>div[class*=\"jqclockpicker-tick\"]"));

        for (WebElement h : hours) {
            String ht = h.getText();
            if (ht.equals(hour)) {
                actions.moveToElement(h).click().perform();
//                h.click();
                break;
            }
        }
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class*=\"hours\"][style=\"visibility: visible;\"]>div[class*=\"jqclockpicker-tick\"]")));

        Timer.waitSeconds(1);


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class*=\"minutes\"][style=\"visibility: visible;\"]>[class*=\"jqclockpicker-tick\"]")));
        List<WebElement> minutes = driver.findElements(By.cssSelector("div[class*=\"minutes\"]>[class*=\"jqclockpicker-tick\"]"));
        for (WebElement m : minutes) {
            String mt = m.getText();
            if (mt.equals(minute)) {
                actions.moveToElement(m).click().perform();
//                m.click();
                Timer.waitSeconds(1);
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class*=\"minutes\"][style*=\"visibility: visible;\"]")));
                break;
            }
        }
    }

    public void cancelDate() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cancelDateButton)).click();
        Timer.waitSeconds(1);
    }


    public AdvancedOptions openAdvancedOptions() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(advancedOptions)).click();
        return new AdvancedOptions();
    }

    public class AdvancedOptions {
        public void addTagToCampaign(String tag) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(tagSearch)).sendKeys(tag);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[title='" + tag + "']"))).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'" + tag + "')]")));
        }

        public void aliasSearch(String alias) {

            ((JavascriptExecutor) driver).executeScript("function getElementByXpath(path) { " +
                    "return document.evaluate(path, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
                    "}" +
                    " getElementByXpath(\"//div[contains(text(),'" + alias + "')]\").click();");
        }

        public void addAliasToCampaign(String alias) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(aliasSearch)).sendKeys(alias);
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(text(),'" + alias + "')]")));
            Timer.waitSeconds(0.5);
            aliasSearch(alias);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'" + alias + "')]")));
        }

        public void hideBrowsers(int x) { //deselects all browsers that are selected by default before a search
            for (int i = 0; i < x; i++) {

                ((JavascriptExecutor) driver).executeScript("document.querySelector(\"span[class='close ui-select-match-close']\").click();");
            }
        }

        public void addBrowserToCampaign(String browser) {
            Custom custom = new Custom(driver);
            List<WebElement> num = driver.findElements(browsersToList);
            hideBrowsers(num.size());
            custom.clickAt(browserSearch.findElement(driver));

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'" + browser.toUpperCase() + "')]"))).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'" + browser.toUpperCase() + "')]")));
        }

        public void addCountryToCampaign(String country) {
            driver.findElement(countrySearch).sendKeys(country);

            ((JavascriptExecutor) driver).executeScript("function getElementByXpath(path) " +
                    "{ return document.evaluate(path, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
                    "} " +
                    "getElementByXpath(\"//*[contains(text(),'" + country + "')]\").click();");
        }


        public void addCityToCampaign(String city) {
            driver.findElement(citySearch).sendKeys(city);

            ((JavascriptExecutor) driver).executeScript("function getElementByXpath(path) " +
                    "{ " +
                    "return document.evaluate(path, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
                    "} " +
                    "getElementByXpath(\"//*[contains(text(),'" + city + "')]\").click();");
        }
    }


    public String uploadIconToPush(String path) {

        driver.findElement(iconInput).sendKeys(new File(path).getAbsolutePath());
        Timer.waitSeconds(1);
        String iconLink = wait.until(ExpectedConditions.visibilityOfElementLocated(iconPrev)).getAttribute("src");

        return iconLink;
    }

    public void uploadIcon(String path) {
        driver.findElement(iconInput).sendKeys(new File(path).getAbsolutePath());
    }


    public AdditionalActiveItems openAdditionalActiveItems() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(additionalActiveItems)).click();
        return new AdditionalActiveItems();
    }

    public class AdditionalActiveItems {
        public void switchButton1() {
            button1Switch.findElement(driver).click();
        }

        public void switchButton2() {
            button2Switch.findElement(driver).click();
        }

        public void setButton1Name(String name) {
            button1NameInput.findElement(driver).sendKeys(name);
        }

        public void setButton2Name(String name) {
            button2NameInput.findElement(driver).sendKeys(name);
        }

        public void setButton1URL(String url) {
            button1URL.findElement(driver).sendKeys(url);
        }

        public void setButton2URL(String url) {
            button2URL.findElement(driver).sendKeys(url);
        }

        public void setButton1(String buttonName, String buttonURL) {
            setButton1Name(buttonName);
            setButton1URL(buttonURL);
        }

        public void setButton2(String buttonName, String buttonURL) {
            setButton2Name(buttonName);
            setButton2URL(buttonURL);
        }

        public void setButtons(String button1Name, String button1URL, String button2Name, String button2URL) {
            switchButton1();
            switchButton2();
            setButton1(button1Name, button1URL);
            setButton2(button2Name, button2URL);
        }

        public void setButton1Icon() {
            wait.until(ExpectedConditions.visibilityOfElementLocated(button1IconDropdown)).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(buttonIcon));
            List<WebElement> icons = driver.findElements(buttonIcon);
            Random random = new Random();
            int index = random.nextInt(216);
            index = index + 1;
            icons.get(index).click();
        }

        public void setButton2Icon() {
            wait.until(ExpectedConditions.visibilityOfElementLocated(button2IconDropdown)).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(buttonIcon));
            List<WebElement> icons = driver.findElements(buttonIcon);
            Random random = new Random();
            int index = random.nextInt(216);
            index = index + 217;
            icons.get(index).click();
        }

        public void switchBIGImage() {
            bigImageSwitch.findElement(driver).click();
        }

        public void setBIGImage(String path) {
            switchBIGImage();
            {
                driver.findElement(bigImageInput).sendKeys(new File(path).getAbsolutePath());
            }
        }

        public String uploadBigImage(String path) {
            setBIGImage(path);
            String imageLink = wait.until(ExpectedConditions.visibilityOfElementLocated(bigImagePrev)).getAttribute("src");

            return imageLink;
        }
    }

    public void sendTestPush() {
        senTestPushButton.findElement(driver).click();
        Timer.waitSeconds(0.5);
    }

    public CampaignHistoryPage sendPush() {
        clickSendPush();
        CampaignHistoryPage campaignHistoryPage = clickOkPopUp();
        return campaignHistoryPage;
    }

    public void clickSendPush() {
        sendTestPush();
        Custom custom = new Custom(driver);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(sendPushButton));
        custom.clickAt(element);
    }

    public CampaignHistoryPage clickOkPopUp() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(okPopUpButton)).click();
        return new CampaignHistoryPage(driver, wait);
    }
}
