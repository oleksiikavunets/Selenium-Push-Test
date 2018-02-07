package tests.billing;

import common.BaseTestClass;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.BillingPage;
import pageobjects.LogInPage;
import testconfigs.baseconfiguration.TestServerConfiguretion;
import testconfigs.testdata.TestData;
import testconfigs.testrestrictions.GravitecBilling;
import testutils.Listeners.LogListener;

@Listeners(LogListener.class)
public class Test_Pos_SmokePayment extends BaseTestClass {

    @Test(groups = "billing")
    public void smokePaymentTest() throws Exception {
        LogInPage logInPage = new LogInPage(driver);
        GravitecBilling gravitecBilling = new GravitecBilling();

        if (gravitecBilling.verifySmokePaymentToExecute()) {
            BillingPage billingPage = new BillingPage(driver);
            logInPage.login(TestData.debtorEmail, TestData.pass);
            billingPage.clickToPayNow()
                    .clickPopUpToPay();
            wait.until(ExpectedConditions.urlContains("fondy"));
            String url = driver.getCurrentUrl();
            System.out.println(url);
            Assert.assertTrue(url.contains("fondy"));
            driver.navigate().back();
        } else {
            throw new SkipException("Test " + Thread.currentThread().getClass().getSimpleName() +
                    " is not allowed to run on " + TestServerConfiguretion.iTest);
        }
    }
}
