package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.common.AbstractPage;

public class BillingPage extends AbstractPage{
    private  By currentDebt = By.xpath("//*[@id=\"page-wrapper\"]//h4[1]/span");
    private  By payNowButton = By.cssSelector("a[ng-bind=\"'PAYMNT_PAY_NOW' | translate\"]");
    private  By popupAmountToPay = By.cssSelector("body > div.modal.fade.ng-scope.ng-isolate-scope.in > div > div > h3");
    private  By payPopUpButton = By.cssSelector("button[ng-bind*=\"'PAYMNT_PAY_NOW'\"]");

    public BillingPage(WebDriver driver){
        super(driver);
    }

    public BillingPage clickToPayNow() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(payNowButton)).click();
        return this;
    }

    public BillingPage clickPopUpToPay(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(payPopUpButton)).click();
        return this;
    }
}

//body > div.modal.fade.ng-scope.ng-isolate-scope.in > div > div > h3