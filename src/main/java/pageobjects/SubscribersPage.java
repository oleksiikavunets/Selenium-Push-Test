package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.common.AbstractAdminPage;
import pageobjects.common.annotations.PartialPath;

import java.util.List;

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

    public SubscribersPage(WebDriver driver) {
        super(driver);
    }

    public SubscribersPage clickTodayBtn() {
        clickAndWaitForChartToReload(wait.until(ExpectedConditions.visibilityOfElementLocated(todayBtn)));
        return this;
    }

    public SubscribersPage clickAndWaitForChartToReload(WebElement element) {
        List<String> graph = getAllDatesInGraph();

        element.click();
        for(int i = 0; i < 30; i++){
            waitSeconds(0.5);
            List<String> newGraph = getAllDatesInGraph();
            if(!graph.equals(newGraph)|| newGraph.size() == 0) break;
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

    public List<String> getAllDatesInGraph(){
        return textOf(driver.findElements(charDate));
    }


}
