package pageobjects;

import actions.Timer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class SubscribersPage {


    public By totalSubscribersAmount = By.cssSelector("span[ng-bind*=\"Subscribers.daystat.total \"]");
    public By todayBtn = By.cssSelector("[ng-bind-html*='STAT_TODAY']");
    public By yesturdayBtn = By.cssSelector("[ng-bind-html*='STAT_YESTERD']");
    public By weekBtn = By.cssSelector("[ng-bind-html*='STAT_WEEK']");
    public By monthBtn = By.cssSelector("[ng-bind-html*='STAT_MONTH']");
    public By lifitimeBtn = By.cssSelector("[ng-bind-html*='STAT_ALL_TIME']");
    WebDriver driver;
    Wait<WebDriver> wait;

    public SubscribersPage(WebDriver driver, Wait<WebDriver> wait){
        this.driver = driver;
        this.wait = wait;
    }

    public void clickTodayBtn(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(todayBtn)).click();
        Timer.waitSeconds(1);
    }

    public int getAmountOfSubscribers(){
        int amount = Integer.valueOf(wait.until(ExpectedConditions.visibilityOfElementLocated(totalSubscribersAmount)).getText());
        return amount;
    }
}
