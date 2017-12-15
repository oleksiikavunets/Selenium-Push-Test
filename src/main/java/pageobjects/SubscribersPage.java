package pageobjects;

import actions.Timer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.common.AbstractPage;

public class SubscribersPage extends AbstractPage{

    public By totalSubscribersAmount = By.cssSelector("span[ng-bind*=\"Subscribers.daystat.total \"]");
    public By todayBtn = By.cssSelector("[ng-bind-html*='STAT_TODAY']");
    public By yesturdayBtn = By.cssSelector("[ng-bind-html*='STAT_YESTERD']");
    public By weekBtn = By.cssSelector("[ng-bind-html*='STAT_WEEK']");
    public By monthBtn = By.cssSelector("[ng-bind-html*='STAT_MONTH']");
    public By lifitimeBtn = By.cssSelector("[ng-bind-html*='STAT_ALL_TIME']");

    public SubscribersPage(WebDriver driver){
        super(driver);
    }

    public SubscribersPage clickTodayBtn(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(todayBtn)).click();
        Timer.waitSeconds(1);
        return this;
    }

    public int getAmountOfSubscribers(){
        int amount = Integer.valueOf(wait.until(ExpectedConditions.visibilityOfElementLocated(totalSubscribersAmount)).getText());
        return amount;
    }
}
