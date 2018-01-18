package pageobjects;

import actions.Timer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.common.AbstractAdminPage;
import pageobjects.common.annotations.PartialPath;

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
        wait.until(ExpectedConditions.visibilityOfElementLocated(todayBtn)).click();
        waitForChartToReload();
        return this;
    }

    public SubscribersPage waitForChartToReload() {
        Timer.waitSeconds(1);
        wait.until(ExpectedConditions.visibilityOfElementLocated(chartBar));
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
        todayBtn.findElement(driver).click();
        waitForChartToReload();
        return this;
    }

    public SubscribersPage switchYesterdayStats() {
        yesterdayBtn.findElement(driver).click();
        waitForChartToReload();
        return this;
    }

    public SubscribersPage swithWeekStats() {
        weekBtn.findElement(driver).click();
        waitForChartToReload();
        return this;
    }

    public SubscribersPage switchMonthStats() {
        monthBtn.findElement(driver).click();
        waitForChartToReload();
        return this;
    }

    public SubscribersPage switchLifeTimeStats() {
        lifetimeBtn.findElement(driver).click();
        waitForChartToReload();
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

}
