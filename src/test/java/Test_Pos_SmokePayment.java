import testutils.Listeners.LogListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.BillingPage;
import pageobjects.HeaderMenu;
import pageobjects.LogInPage;
import testdata.TestData;
import testrestrictions.GravitecBilling;

@Listeners(LogListener.class)
public class Test_Pos_SmokePayment extends SeleniumBaseClass {

    @Test(groups = "billing")
    public void smokePayment() throws Exception {
        LogInPage logInPage = new LogInPage(driver, wait);
        GravitecBilling gravitecBilling = new GravitecBilling();

        if (gravitecBilling.verifySmokePaymentToExecute()) {
            BillingPage billingPage = new BillingPage(driver, wait);
            logInPage.login(TestData.debtorEmail, TestData.pass);
            billingPage.clickToPayNow();
            billingPage.clickPopUpToPay();
            wait.until(ExpectedConditions.urlContains("fondy"));
            String url = driver.getCurrentUrl();
            System.out.println(url);
            Assert.assertTrue(url.contains("fondy"));
            driver.navigate().back();
            new HeaderMenu(driver, wait).logout();
        }
    }
}
