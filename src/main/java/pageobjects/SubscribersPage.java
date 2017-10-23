package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class SubscribersPage {


    public By totalSubscribersAmount = By.cssSelector("span[ng-bind*=\"Subscribers.daystat.total \"]");
    WebDriver driver;
    Wait<WebDriver> wait;

    public SubscribersPage(WebDriver driver, Wait<WebDriver> wait){
        this.driver = driver;
        this.wait = wait;
    }

    public int getAmountOfSubscribers(){
        int amount = Integer.valueOf(wait.until(ExpectedConditions.visibilityOfElementLocated(totalSubscribersAmount)).getText());
        return amount;
    }
}
