package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import pageobjects.common.AbstractAdminPage;
import pageobjects.common.annotations.PartialPath;
import pageutils.PaginationUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static actions.Timer.waitSeconds;
import static com.selenium.utils.TextGetter.textOf;

@PartialPath(value = "/sites/SITE_ID/subscribers")
public class SubscribersPage extends AbstractAdminPage {

    private By totalSubscribersAmount = By.cssSelector("span[ng-bind*=\"Subscribers.daystat.total \"]");
    private By newSubscribersAmount = By.cssSelector("span[ng-bind*=\"Subscribers.daystat.totalNew\"]");
    private By unsbSubscribersAmount = By.cssSelector("span[ng-bind*=\"Subscribers.daystat.totalUnsubscribed\"]");

    private By todayBtn = By.cssSelector("[ng-bind-html*='STAT_TODAY']");
    private By yesterdayBtn = By.cssSelector("[ng-bind-html*='STAT_YESTERD']");
    private By weekBtn = By.cssSelector("[ng-bind-html*='STAT_WEEK']");
    private By monthBtn = By.cssSelector("[ng-bind-html*='STAT_MONTH']");
    private By lifetimeBtn = By.cssSelector("[ng-bind-html*='STAT_ALL_TIME']");

    private By allSubsBtn = By.cssSelector("button[ng-bind*=\"SUBS_ALLSUBS\"]");
    private By newSubsBtn = By.cssSelector("button[ng-bind*=\"SUBS_ONLY_NEW\"]");
    private By byTimeSubsBtn = By.cssSelector("button[ng-bind*=\"SUBS_BY_TIME\"]");
    private By unsbSubsBtn = By.cssSelector("button[ng-bind*=\"UNSBCRBD\"]");

    private By chartBar = By.cssSelector("rect[style*=\"-webkit-tap\"]");
    private By charDate = By.cssSelector("text[style^=\"-webkit-tap\"][transform^=\"matrix\"]");

    private By toolTipDate = By.cssSelector("div[class=\"morris-hover-row-label\"]");
    private By toolTipSubsAmount = By.cssSelector("div[class=\"morris-hover-point\"]");

    private By inputStartDate = By.cssSelector("input[class$=\"control active\"][name$=\"start\"]");
    private By inputEndDate = By.xpath("//input[contains(@class, \"active\")]/ancestor::div/child::div[@class=\"calendar right\"]/descendant::input");

    private By nameInPopUp = By.cssSelector("span[ng-bind=\"name\"]");
    private By amountOfSubsInPopUp = By.cssSelector("span[ng-bind^=\"val | number\"]");
    private By amountOfSubsByAlias = By.cssSelector("li span[ng-bind*=\"aliasExist\"]");
    private By pageNumberInPopUp = By.cssSelector("a[ng-click*=\"page\"]");
    private By okBtn = By.cssSelector("button[data-ng-bind*='OK']");

    private By tagsSelect = By.cssSelector("select-picker[options*='TAG'] select");
    private By browserSelect = By.cssSelector("select-picker[options$='browser'] select");
    private By countrySelect = By.cssSelector("select-picker[options$='country'] select");
    private By citySelect = By.cssSelector("select-picker[options$='city'] select");
    private By osSelect = By.cssSelector("select-picker[options$='os'] select");
    private By aliasSelect = By.cssSelector("select-picker[options*='ALIAS'] select");
    private By countBtn = By.cssSelector("button[ng-bind-html*='SUBS_COUNT']");
    private By subsCalculationResult = By.cssSelector("span[ng-bind=\"vmSubscribers.calcRes\"]");

    public SubscribersPage(WebDriver driver) {
        super(driver);
    }

    public SubscribersPage clickAndWaitForChartToReload(WebElement element) {
        List<String> graph = getAllDatesInGraph();

        element.click();
        for (int i = 0; i < 30; i++) {
            waitSeconds(0.5);
            List<String> newGraph = getAllDatesInGraph();
            if (!graph.equals(newGraph) || newGraph.size() == 0) break;
        }
        return this;
    }

    public int getAmountOfSubscribers() {
        int amount = Integer.valueOf(wait.until(ExpectedConditions.visibilityOfElementLocated(totalSubscribersAmount)).getText());
        return amount;
    }

    public int getAmountOfNewSubs() {
        return Integer.valueOf(newSubscribersAmount.findElement(driver).getText());
    }

    public int getAmountOfUnsbSubs() {
        return Integer.valueOf(unsbSubscribersAmount.findElement(driver).getText());
    }

    public SubscribersPage switchTodayStats() {
        clickAndWaitForChartToReload(todayBtn.findElement(driver));
        return this;
    }

    public SubscribersPage switchYesterdayStats() {
        clickAndWaitForChartToReload(yesterdayBtn.findElement(driver));
        return this;
    }

    public SubscribersPage swithWeekStats() {
        clickAndWaitForChartToReload(weekBtn.findElement(driver));
        return this;
    }

    public SubscribersPage switchMonthStats() {
        clickAndWaitForChartToReload(monthBtn.findElement(driver));
        return this;
    }

    public SubscribersPage switchLifeTimeStats() {
        clickAndWaitForChartToReload(lifetimeBtn.findElement(driver));
        return this;
    }

    public SubscribersPage switchAllSubsDisplay() {
        allSubsBtn.findElement(driver).click();
        return this;
    }

    public SubscribersPage switchNewSubsDisplay() {
        newSubsBtn.findElement(driver).click();
        return this;
    }

    public SubscribersPage switchByTimeSubsDisplay() {
        byTimeSubsBtn.findElement(driver).click();
        return this;
    }

    public SubscribersPage switchUnsbSubs() {
        unsbSubsBtn.findElement(driver).click();
        return this;
    }

    public List<String> getAllDatesInGraph() {
        return textOf(driver.findElements(charDate));
    }

    private SubscribersPage openDetailsBtn(String target) {
        driver.findElement(By.xpath("//h3[contains(@ng-bind,'SUBS_BY_" + target.toUpperCase() + "')]/following-sibling::button[contains(@ng-bind,'Detail')]")).click();
        return this;
    }

    public int getSubsAmountByTags() {
        int subs = 0;
        int page = 1;

        openDetailsBtn("TAGS");
        do {
            subs += driver.findElements(amountOfSubsInPopUp)
                    .stream().map(s -> Integer.valueOf(s.getText()))
                    .collect(Collectors.summingInt(i -> i));
            System.out.println(subs);

        } while (openPage(++page));
        okBtn.findElement(driver).click();

        return subs;
    }

    public HashMap<String, String> getSubsAmountByAliases() {
        HashMap<String, String> subs = new HashMap<>();

        List<WebElement> aliasSubs = driver.findElements(amountOfSubsByAlias);

        subs.put("Alias set", aliasSubs.get(0).getText());
        subs.put("Alias not set", aliasSubs.get(1).getText());

        return subs;
    }

    public HashMap<String, String> getSubsAmountByBrowsers() {
        return getSubsAmountByName("BROWSERS");
    }

    public HashMap<String, String> getSubsAmountByCountry() {
        return getSubsAmountByName("COUNTR");
    }

    public HashMap<String, String> getSubsAmountByCity() {
        return getSubsAmountByName("CITIES");
    }

    public HashMap<String, String> getSubsAmountByOs() {
        return getSubsAmountByName("OS");
    }

    private HashMap<String, String> getSubsAmountByName(String name) {
        openDetailsBtn(name);
        int page = 1;
        HashMap<String, String> subs = new HashMap<>();
        do {
            List<WebElement> names = driver.findElements(nameInPopUp);
            List<WebElement> amount = driver.findElements(amountOfSubsInPopUp);
            for (int i = 0; i < names.size(); i++) {
                subs.put(names.get(i).getText(), amount.get(i).getText());
            }
        } while (openPage(++page));
        okBtn.findElement(driver).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(okBtn));
        return subs;
    }

    private boolean openPage(int page) {
        String tag = nameInPopUp.findElement(driver).getText();
        boolean opened = new PaginationUtil(driver).openPopUpPage(page);
        if (opened) {
            wait.until(ExpectedConditions.invisibilityOfElementWithText(nameInPopUp, tag));
        }
        return opened;
    }

    public SubscribersPage countSubscribers() {
        countBtn.findElement(driver).click();
        waitSeconds(5);
        return this;
    }

    public SubscribersPage selectTagsOption(String optionText) {
        Select select = new Select(driver.findElement(tagsSelect));
        select.selectByVisibleText(optionText);
        return this;
    }

    public SubscribersPage selectBrowser(String optionText) {
        Select select = new Select(driver.findElement(browserSelect));
        select.selectByVisibleText(optionText);
        return this;
    }

    public SubscribersPage selectCountry(String optionText) {
        Select select = new Select(driver.findElement(countrySelect));
        select.selectByVisibleText(optionText);
        return this;
    }

    public SubscribersPage selectCity(String optionText) {
        Select select = new Select(driver.findElement(citySelect));
        select.selectByVisibleText(optionText);
        return this;
    }

    public SubscribersPage selectOs(String optionText) {
        Select select = new Select(driver.findElement(osSelect));
        select.selectByVisibleText(optionText);
        return this;
    }

    public SubscribersPage selectAliases(String setNotSet) {
        Select select = new Select(driver.findElement(aliasSelect));
        select.selectByIndex(setNotSet.equals("Alias set") ? 1 :
                setNotSet.equals("Alias not set") ? 2 : 0);
        return this;
    }

    public List<SubscribersPage> selectByCondition(String key){
        return new ArrayList<>(Arrays.asList(
                selectBrowser(key),
                selectCountry(key),
                selectCity(key),
                selectOs(key),
                selectAliases(key)
        ));
    }

    public SubscribersPage resetSelects(){
        selectBrowser("All");
        selectAliases("All");
        selectCity("All");
        selectCountry("All");
        selectOs("All");
        selectTagsOption("All");
        return this;
    }

    public String getSubsCountBySelectedConditions(){

        waitSeconds(1);
        return driver.findElement(subsCalculationResult).getText();
    }


}
