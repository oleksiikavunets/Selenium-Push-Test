import com.selenium.ConfigTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.BillingPage;
import pageobjects.LogInPage;
import testdata.TestData;
import testrestrictions.GravitecBilling;
import testutils.Listeners.LogListener;

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
        } else {
            throw new SkipException("Test " + Thread.currentThread().getClass().getSimpleName() +
                    " is not allowed to run on " + ConfigTest.iTest);
        }
    }
}
