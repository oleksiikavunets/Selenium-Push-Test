package pageobjects;

import actions.Clicker;
import com.selenium.ConfigTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.common.AbstractAdminPage;
import pageobjects.common.annotations.PartialPath;
import pageutils.ImageUploader;
import testrestrictions.BetaFeatures;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import static actions.Timer.waitSeconds;
import static com.selenium.enums.Server.GRV;
import static com.selenium.enums.Server.GRV_7700;

/**
 * Created by Oleksii on 14.07.2017.
 */
@PartialPath(value = "/sites/SITE_ID/send")
public class CreateCampaignPage extends AbstractAdminPage {

    //push preview block
    private By titlePreview = By.cssSelector("p[data-ng-bind*=\"$ctrl.mtitle \"]");
    private By textPreview = By.cssSelector("p[data-ng-bind*=\"$ctrl.body\"]");
    private By iconPrev = By.cssSelector("div[class*=\"icon\"]>img[src*=\"cdn\"]");
    private By bigImagePrev = By.cssSelector("img[class=\"additional-img\"]");
    private By button1Prev = By.cssSelector("span[ng-bind*=\"$ctrl.btn1.title \"]");
    private By button2Prev = By.cssSelector("span[ng-bind*=\"$ctrl.btn2.title \"]");
    //input fields
    private By titleInput = By.name("title");
    private By textInput = By.name("text");
    private By urlToRedirect = By.name("redirect");
    private By iconInput = By.name("file");
    private By dateInput = By.cssSelector("input[type='text'][date-mask]");
    //UTM
    private By checkboxUTM = By.cssSelector("input[ng-model=\"vmUtmCampaignCtrl.isUtmActive\"]");
    private By inputUTMcampaign = By.cssSelector("input[ng-change*=\"CampaignCtrl.changedUtm()\"]");
    //date
    private By cancelDateButton = By.cssSelector("button[ng-click*=\"CancelDate\"]");

    //additional active items
    private By additionalActiveItems = By.cssSelector("span[ng-bind*=\"'ADDTNL_ELEMNTS'\"]");
    private By button1Switch = By.cssSelector("label[ng-bind*=\"'FRST_BTN'\"]");
    private By button2Switch = By.cssSelector("label[ng-bind*=\"'SND_BTN'\"]");
    private By button1NameInput = By.name("button1Title");
    private By button2NameInput = By.name("button2Title");
    private By button1URL = By.name("url1");
    private By button2URL = By.name("url2");
    private By button1IconDropdown = By.cssSelector("button[class*=\"dropdown\"][ng-disabled*=\"1Button\"]");
    private By button2IconDropdown = By.cssSelector("button[class*=\"dropdown\"][ng-disabled*=\"2Button\"]");
    private By buttonIcon = By.cssSelector("img[src*=\"img/button-icons\"]");
    private By bigImageSwitch = By.cssSelector("label[ng-bind*=\"'ADD_BIG_IMG'\"]");
    private By bigImageInput = By.cssSelector("input[ngf-select*=\"uploadFile\"]");


    //advanced options block and search masks
    private By advancedOptions = By.cssSelector("span[ng-bind*=\"'SEND_PUSH_ADVCD'\"]");
    private By tagSearch = By.cssSelector("input[ng-model=\"vmTagListCtrl.selectedTag\"]");
    private By aliasSearch = By.cssSelector("input[aria-label=\"Select aliases\"]");
    private By browserSearch = By.cssSelector("input[aria-label=\"Select browsers\"]");
    private By countrySearch = By.cssSelector("input[aria-label=\"Select countries\"]");
    private By citySearch = By.cssSelector("input[aria-label=\"Select cities\"]");
    //selected options
    private By tagsToSend = By.cssSelector("td[ng-bind=\"tag.name\"]");
    private By aliasToSend = By.cssSelector("span[uis-transclude-append]");
    private By browserToSend = By.xpath("//div[@title=\"Select browsers\"]//span[2]");
    private By browsersToList = By.cssSelector("div[title=\"Select browsers\"]>div>span>span");
    private By countryTosend = By.xpath("//div[@title=\"Select countries\"]//span[2]");
    private By cityToSend = By.xpath("//div[@title=\"Select cities\"]//span[2]");
    //error messages
    private By requiredTitle = By.xpath("//p[@translate='REQ_FIELD']");
    private By requiredText = By.xpath("//div[contains(@ng-show,'mainForm.text.$error.required')]/p");
    private By linkFormat = By.xpath("//div[contains(@ng-show,'redirect.$error.pattern')]/p");
    private By iconError = By.cssSelector("p[ng-bind*=imgErrorMessage]");
    private By iconErrorGRV = By.cssSelector("[ng-if*=\"iconErrorMessage\"]");
    private By button1TitelError = By.cssSelector("[ng-if*=\"button1Title.$error\"]");
    private By button1URLError = By.cssSelector("[ng-if*=\"url1.$error\"]");
    private By button2TitleError = By.cssSelector("[ng-if*=\"button2Title.$error\"]");
    private By button2URLError = By.cssSelector("[ng-if*=\"url2.$error\"]");
    //send button and "OK" button on pop up
    private By senTestPushButton = By.cssSelector("button[ng-bind*=\"'SEND_PUSH_TEST'\"]");
    private By sendPushButton = By.cssSelector("button[class=\"btn btn-lg btn-block btn-primary ng-binding waves-effect waves-light\"]");
    private By okPopUpButton = By.cssSelector("button[class=\"confirm ng-binding\"][ng-bind=\"ok | translate\"]");


    public CreateCampaignPage(WebDriver driver) {
        super(driver);
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

        public WebElement getBigImgPreview() {
            WebElement element = null;
            try {
                element = bigImagePrev.findElement(driver);
            } catch (org.openqa.selenium.NoSuchElementException e) {
            }
            return element;
        }

        public WebElement getButton1Preview() {
            waitSeconds(0.5);
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
            for(int i = 0; i < 3; i++) {
                try {
                    element = bigImagePrev.findElement(driver);
                } catch (org.openqa.selenium.NoSuchElementException e) {
                    waitSeconds(1);
                }
            }
            return element;
        }
    }

    public CreateCampaignPage setTitle(String title) {
        titleInput.findElement(driver).sendKeys(title);
        return this;

    }

    public CreateCampaignPage setText(String text) {
        textInput.findElement(driver).sendKeys(text);
        return this;
    }

    public CreateCampaignPage clearAllInputs() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(titleInput)).clear();
        textInput.findElement(driver).clear();
        urlToRedirect.findElement(driver).clear();
        return this;
    }


    public CreateCampaignPage setUrlToRedirect(String url) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(urlToRedirect)).sendKeys(url);
        return this;
    }

    public CreateCampaignPage setUTMcampaign(String utm) {
        boolean checked = wait.until(ExpectedConditions.visibilityOfElementLocated(checkboxUTM)).isSelected();
        if (!checked) checkboxUTM.findElement(driver).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputUTMcampaign)).sendKeys(utm);
        return this;
    }

    public WebElement getTitleErrorMsg(){
        return requiredTitle.findElement(driver);
    }

    public WebElement getTextErrorMsg(){
        return requiredText.findElement(driver);
    }

    public WebElement getInvalidLinkFormatMsg(){
        return linkFormat.findElement(driver);
    }

    public WebElement getIconTooBigError() {
        By locator;
        if (ConfigTest.iTest.equals(GRV_7700) || ConfigTest.iTest.equals(GRV)) {
            locator = iconErrorGRV;
        } else {
            locator = iconError;
        }
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public CreateCampaignPage setDateAndTime(int year, int month, int day, int hour, int minute) {
        LocalDateTime date = LocalDateTime.of(year, month, day, hour, minute);
        driver.findElement(dateInput).sendKeys(date.format(DateTimeFormatter.ofPattern("dd.MM.YYYY")));
//        ((JavascriptExecutor)driver).executeScript("document.querySelector(\"input[type='text'][time-mask]\").value='11.11';");
        setTime(date);
        return this;
    }

    public CreateCampaignPage setDateAndTime(int plusDays, int plusHours, int plusMinutes) {
        LocalDateTime date = LocalDateTime.now().plusDays(plusDays).plusHours(plusHours).plusMinutes(plusMinutes);
        System.out.println(date);
        driver.findElement(dateInput).sendKeys(date.format(DateTimeFormatter.ofPattern("dd.MM.YYYY")));
        setTime(date);
        System.out.println(date);
        return this;
    }

    public CreateCampaignPage setTime(LocalDateTime dateTime) {
        Actions actions = new Actions(driver);
        String hour = dateTime.format(DateTimeFormatter.ofPattern("H:mm")).split(":")[0];
        System.out.println(hour);

        String minute = dateTime.format(DateTimeFormatter.ofPattern("HH:mm")).split(":")[1];

        driver.findElement(By.cssSelector("input[type='text'][time-mask]")).click();
        waitSeconds(1);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class*=\"hours\"][style=\"visibility: visible;\"]>div[class*=\"jqclockpicker-tick\"]")));
        List<WebElement> hours = driver.findElements(By.cssSelector("div[class*=\"hours\"]>div[class*=\"jqclockpicker-tick\"]"));

        for (WebElement h : hours) {
            String ht = h.getText();
            if (ht.equals(hour)) {
                actions.moveToElement(h).click().perform();
                break;
            }
        }

        waitSeconds(1);


        List<WebElement> minutes = driver.findElements(By.cssSelector("div[class*=\"minutes\"]>[class*=\"jqclockpicker-tick\"]"));
        for (WebElement m : minutes) {
            String mt = m.getText();
            if (mt.equals(minute)) {
                actions.moveToElement(m).click().perform();
                waitSeconds(0.5);
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class*=\"minutes\"][style*=\"visibility: visible;\"]")));
                break;
            }
        }
        return this;
    }

    public CreateCampaignPage cancelDate() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cancelDateButton)).click();
        waitSeconds(1);
        return this;
    }


    public AdvancedOptions openAdvancedOptions() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(advancedOptions)).click();
        return new AdvancedOptions();
    }

    public class AdvancedOptions {


        public AdvancedOptions addTagToCampaign(String... tag) {
            for (String t : tag) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(tagSearch)).sendKeys(t);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[title='" + t + "']"))).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'" + t + "')]")));
            }
            return this;
        }

        public List<WebElement> getTagsToBeSent() {
            return driver.findElements(tagsToSend);
        }

        public AdvancedOptions aliasSearch(String alias) {

            ((JavascriptExecutor) driver).executeScript("function getElementByXpath(path) { " +
                    "return document.evaluate(path, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
                    "}" +
                    " getElementByXpath(\"//div[contains(text(),'" + alias + "')]\").click();");
            return this;
        }

        public AdvancedOptions addAliasToCampaign(String alias) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(aliasSearch)).sendKeys(alias);
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(text(),'" + alias + "')]")));
            waitSeconds(0.5);
            aliasSearch(alias);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'" + alias + "')]")));
            return this;
        }

        private AdvancedOptions hideBrowsers(int x) { //deselects all browsers that are selected by default before a search
            for (int i = 0; i < x; i++) {
                ((JavascriptExecutor) driver).executeScript("document.querySelector(\"span[class='close ui-select-match-close']\").click();");
            }
            return this;
        }

        public AdvancedOptions addBrowserToCampaign(String browser) {
            Clicker clicker = new Clicker(driver);
            List<WebElement> num = driver.findElements(browsersToList);
            hideBrowsers(num.size());
            clicker.clickJS(browserSearch.findElement(driver));

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'" + browser.toUpperCase() + "')]"))).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'" + browser.toUpperCase() + "')]")));
            return this;
        }

        public AdvancedOptions addCountryToCampaign(String country) {
            driver.findElement(countrySearch).sendKeys(country);

            ((JavascriptExecutor) driver).executeScript("function getElementByXpath(path) " +
                    "{ return document.evaluate(path, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
                    "} " +
                    "getElementByXpath(\"//*[contains(text(),'" + country + "')]\").click();");
            return this;
        }


        public AdvancedOptions addCityToCampaign(String city) {
            driver.findElement(citySearch).sendKeys(city);

            ((JavascriptExecutor) driver).executeScript("function getElementByXpath(path) " +
                    "{ " +
                    "return document.evaluate(path, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
                    "} " +
                    "getElementByXpath(\"//*[contains(text(),'" + city + "')]\").click();");
            return this;
        }
    }


    public String uploadIconToPush(String path) {

        if (BetaFeatures.verifyBetaToTest("imageCropper")) {
            new ImageUploader(driver).uploadIcon(path);
        } else {
            uploadIcon(path);
        }
        waitSeconds(1);
        String iconLink = wait.until(ExpectedConditions.visibilityOfElementLocated(iconPrev)).getAttribute("src");

        return iconLink;
    }

    public CreateCampaignPage uploadIcon(String path) {
        driver.findElement(iconInput).sendKeys(new File(path).getAbsolutePath());
        return this;
    }


    public AdditionalActiveItems openAdditionalActiveItems() {
        wait.until(ExpectedConditions.elementToBeClickable(additionalActiveItems)).click();
        return new AdditionalActiveItems();
    }

    public class AdditionalActiveItems {
        public AdditionalActiveItems switchButton1() {
            wait.until(ExpectedConditions.visibilityOfElementLocated(button1Switch)).click();
            return this;
        }

        public AdditionalActiveItems switchButton2() {
            button2Switch.findElement(driver).click();
            return this;
        }

        public AdditionalActiveItems setButton1Name(String name) {
            button1NameInput.findElement(driver).sendKeys(name);
            return this;
        }

        public AdditionalActiveItems setButton2Name(String name) {
            button2NameInput.findElement(driver).sendKeys(name);
            return this;
        }

        public AdditionalActiveItems setButton1URL(String url) {
            button1URL.findElement(driver).sendKeys(url);
            return this;
        }

        public AdditionalActiveItems setButton2URL(String url) {
            button2URL.findElement(driver).sendKeys(url);
            return this;
        }

        public AdditionalActiveItems setButton1(String buttonName, String buttonURL) {
            setButton1Name(buttonName);
            setButton1URL(buttonURL);
            return this;
        }

        public AdditionalActiveItems setButton2(String buttonName, String buttonURL) {
            setButton2Name(buttonName);
            setButton2URL(buttonURL);
            return this;
        }

        public AdditionalActiveItems setButtons(String button1Name, String button1URL, String button2Name, String button2URL) {
            switchButton1();
            switchButton2();
            setButton1(button1Name, button1URL);
            setButton2(button2Name, button2URL);
            return this;
        }

        public AdditionalActiveItems setButton1Icon() {
            wait.until(ExpectedConditions.visibilityOfElementLocated(button1IconDropdown)).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(buttonIcon));
            List<WebElement> icons = driver.findElements(buttonIcon);
            Random random = new Random();
            int index = random.nextInt(216);
            index = index + 1;
            icons.get(index).click();
            return this;
        }

        public AdditionalActiveItems setButton2Icon() {
            wait.until(ExpectedConditions.visibilityOfElementLocated(button2IconDropdown)).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(buttonIcon));
            List<WebElement> icons = driver.findElements(buttonIcon);
            Random random = new Random();
            int index = random.nextInt(216);
            index = index + 217;
            icons.get(index).click();
            return this;
        }

        public AdditionalActiveItems switchBIGImage() {
            wait.until(ExpectedConditions.visibilityOfElementLocated(bigImageSwitch)).click();
            return this;
        }

        public AdditionalActiveItems setBIGImage(String path) {
            switchBIGImage();
            {
                driver.findElement(bigImageInput).sendKeys(new File(path).getAbsolutePath());
            }
            return this;
        }

        public String uploadBigImage(String path) {
            if (BetaFeatures.verifyBetaToTest("imageCropper")) {
                switchBIGImage();
                new ImageUploader(driver).uploadBigImg(path);
            } else {
                setBIGImage(path);
            }
            String imageLink = wait.until(ExpectedConditions.visibilityOfElementLocated(bigImagePrev)).getAttribute("src");

            return imageLink;
        }

        public WebElement getBtn1TitleError() {
            return button1TitelError.findElement(driver);
        }

        public WebElement getBtn2TitleError() {
            return button2TitleError.findElement(driver);
        }

        public WebElement getBtn1UrlError(){
            return button1URLError.findElement(driver);
        }
        public WebElement getBtn2UrlError(){
            return button2URLError.findElement(driver);
        }
    }

    public CreateCampaignPage sendTestPush() {
        senTestPushButton.findElement(driver).click();
        waitSeconds(0.5);
        return this;
    }

    public CampaignHistoryPage sendPush() {
        clickSendPush();
        return clickOkPopUp();
    }

    public CreateCampaignPage clickSendPush() {
        sendTestPush();
        Clicker clicker = new Clicker(driver);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(sendPushButton));
        clicker.clickJS(element);
        return this;
    }

    private CampaignHistoryPage clickOkPopUp() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(okPopUpButton)).click();
        return new CampaignHistoryPage(driver);
    }
}
