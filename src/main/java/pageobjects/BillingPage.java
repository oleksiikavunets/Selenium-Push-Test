package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class BillingPage {
    public By currentDebt = By.xpath("//*[@id=\"page-wrapper\"]//h4[1]/span");
    public By payNowButton = By.cssSelector("a[ng-bind=\"'PAYMNT_PAY_NOW' | translate\"]");
    public By popupAmountToPay = By.cssSelector("body > div.modal.fade.ng-scope.ng-isolate-scope.in > div > div > h3");
    public By payPopUpButton = By.cssSelector("button[ng-bind*=\"'PAYMNT_PAY_NOW'\"]");

    WebDriver driver;
    Wait<WebDriver> wait;

    public BillingPage(WebDriver driver, Wait<WebDriver> wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void clickToPayNow() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(payNowButton)).click();
    }

    public void clickPopUpToPay(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(payPopUpButton)).click();
    }
}

//body > div.modal.fade.ng-scope.ng-isolate-scope.in > div > div > h3