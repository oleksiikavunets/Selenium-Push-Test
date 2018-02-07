package tests.billing;

import actions.UserActions;
import testconfigs.baseconfiguration.TestServerConfiguretion;
import com.selenium.GravitecServer;
import com.selenium.pojo.User;
import com.selenium.utils.Log;
import com.selenium.utils.RandomGenerator;
import common.BaseTestClass;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.LogInPage;
import testconfigs.testrestrictions.GravitecBilling;

import java.net.MalformedURLException;
import java.time.LocalDate;

/**
 * Created by Rayant on 18.04.2017.
 */
public class BillingTestsTest extends BaseTestClass {

    GravitecServer gravitecServer;
    GravitecBilling gravitecBilling = new GravitecBilling();
    boolean run = gravitecBilling.verifyBillingToRun();

    @BeforeMethod
    public void connectToServer(){

        TestServerConfiguretion testServerConfiguretion = new TestServerConfiguretion();
        GravitecServer gravitecServer = new GravitecServer(testServerConfiguretion.getPort(), testServerConfiguretion.getDirectPort());
        String adminLogin = testServerConfiguretion.getAdminLogin();
        String adminPass = testServerConfiguretion.getAdminPass();
//        String testSiteUrl = testServerConfiguretion.getOldTestSiteUrl();

        System.out.println(gravitecServer.getPort());
        gravitecServer.login(adminLogin,adminPass);
        /*gravitecServer.setTestSiteUrl(testSiteUrl);
        gravitecServer.setTestSiteId(gravitecServer.getSiteId(testSiteUrl));*/

    }



    @Test
    public void newUserFreeTariff() throws Exception {
        if (run) {
            TestServerConfiguretion testServerConfiguretion = new TestServerConfiguretion();
            new UserActions(driver, wait).createNewUser();
            int emailNumber = Integer.valueOf(testServerConfiguretion.getEmailNumber()) - 2;
            String pass = testServerConfiguretion.getPassword();
            new LogInPage(driver).login("grovitek+" + emailNumber + "@gmail.com", pass);
            User user = gravitecServer.getUserFromStatus();
            Assert.assertEquals(user.getTariff().getName(), "Free");
            Assert.assertEquals(user.getNewTariff().getName(), "Free");
        }else {
            Log.info(this.getClass().getSimpleName() + ": Current feature is not allowed to test on " + TestServerConfiguretion.iTest);
            throw new SkipException("Skipped");        }

    }

    @Test
    public void changeTariffFromFreeToEnterpriseScheduled() throws Exception {
        if (run) {

            String siteUrl = "http://" + RandomGenerator.nextString() + ".com";
            new UserActions(driver, wait).createSite(siteUrl);
            Long siteId = null;
            changeTariff("Free");
            User userBefore = gravitecServer.getUserFromStatus();
            long debt = userBefore.getCurrentDebt();
            for (int i = 0; i < 5; i++) {
                siteId = gravitecServer.getSiteId(siteUrl);
                if (siteId != null) break;
                Thread.sleep(500);
            }
            changeSiteFollowersAmount(siteId, 1001);
            executeBilling("updateFollowersAmount");
            executeBilling("updateTariff");
            User userAfter = gravitecServer.getUserFromStatus();
            Assert.assertEquals("Enterprise", userAfter.getTariff().getName());
            Assert.assertEquals("Enterprise", userAfter.getNewTariff().getName());
            Assert.assertEquals((userAfter.getNewTariff().getOverPrice() * (userAfter.getFollowersAmount() + 999) / 1000), (long) userAfter.getCurrentPrice());
            long newDebt = userAfter.getCurrentDebt();
            Assert.assertEquals((userAfter.getNewTariff().getOverPrice() * (userAfter.getFollowersAmount() + 999) / 1000), newDebt - debt);
            changeSiteFollowersAmount(siteId, 0);
        }else {
            Log.info(this.getClass().getSimpleName() + ": Current feature is not allowed to test on " + TestServerConfiguretion.iTest);
            throw new SkipException("Skipped");        }
    }

    @Test
    public void changeTariffFromFreeToEnterpriseByUser() throws Exception {
        if (run) {
            TestServerConfiguretion testServerConfiguretion = new TestServerConfiguretion();

            int emailNumber = Integer.valueOf(testServerConfiguretion.getEmailNumber()) - 2;
            String pass = testServerConfiguretion.getPassword();
            new LogInPage(driver).login("grovitek+" + emailNumber + "@gmail.com", pass);
            changeTariff("Free");
            setPrice(100);
            driver.navigate().to(gravitecServer.getHostUrl() + "/price");
            User userBefore = gravitecServer.getUserFromStatus();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'Enterprise')]")));
            driver.findElement(By.xpath("//button[contains(text(),'Enterprise')]")).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='ok-sweet-alert ng-binding']")));
            driver.findElement(By.xpath("//button[@class='ok-sweet-alert ng-binding']")).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='submit_button']")));
        }else {
            Log.info(this.getClass().getSimpleName() + ": Current feature is not allowed to test on " + TestServerConfiguretion.iTest);
            throw new SkipException("Skipped");        }
    }

    @Test
    public void updatePrice() throws Exception {
        if (run) {

            String siteUrl = "http://" + RandomGenerator.nextString() + ".com";
            new UserActions(driver, wait).createSite(siteUrl);
            Long siteId = null;
            changeTariff("Free");
            for (int i = 0; i < 5; i++) {
                siteId = gravitecServer.getSiteId(siteUrl);
                if (siteId != null) break;
                Thread.sleep(500);
            }
            changeSiteFollowersAmount(siteId, 1001);
            executeBilling("updatePrice");
            User user = gravitecServer.getUserFromStatus();
            setDiscount(90);
            Assert.assertEquals(0L, (long) user.getCurrentPrice());

            changeTariff("SMB");
            executeBilling("updatePrice");
            user = gravitecServer.getUserFromStatus();
            System.out.println("SMB " + user.getCurrentPrice());
            Assert.assertEquals(Math.round((float) user.getNewTariff().getOverPrice() * (LocalDate.now().lengthOfMonth() - LocalDate.now().getDayOfMonth() + 1) / LocalDate.now().lengthOfMonth() * (100 - user.getDiscount()) / 100 * ((user.getFollowersAmount() + 999) / 1000)), (long) user.getCurrentPrice());

            changeTariff("Enterprise");
            executeBilling("updatePrice");
            user = gravitecServer.getUserFromStatus();
            System.out.println("Enterprise " + user.getCurrentPrice());
            Assert.assertEquals(Math.round((float) user.getNewTariff().getOverPrice() * (LocalDate.now().lengthOfMonth() - LocalDate.now().getDayOfMonth() + 1) / LocalDate.now().lengthOfMonth() * (100 - user.getDiscount()) / 100 * ((user.getFollowersAmount() + 999) / 1000)), (long) user.getCurrentPrice());

            changeSiteFollowersAmount(siteId, 0);
        }else {
            Log.info(this.getClass().getSimpleName() + ": Current feature is not allowed to test on " + TestServerConfiguretion.iTest);
            throw new SkipException("Skipped");        }
    }

    @Test
    public void calculateDebt() throws Exception {
        if (run) {
            TestServerConfiguretion testServerConfiguretion = new TestServerConfiguretion();

            int emailNumber = Integer.valueOf(testServerConfiguretion.getEmailNumber()) - 2;
            String pass = testServerConfiguretion.getPassword();
            new LogInPage(driver).login("grovitek+" + emailNumber + "@gmail.com", pass);
            setDebt(100);
            setPrice(200);
            setDiscount(50);
            changeTariff("Enterprise");
            executeBilling("calculateDebt");
            User user = gravitecServer.getUserFromStatus();
            Assert.assertEquals(300L, (long) user.getCurrentDebt());
            Assert.assertEquals(7, user.getTariffExpiringDate().getDayOfYear() - LocalDate.now().getDayOfYear());
        }else {
            Log.info(this.getClass().getSimpleName() + ": Current feature is not allowed to test on " + TestServerConfiguretion.iTest);
            throw new SkipException("Skipped");        }
    }

    @Test
    public void changeTariffSmbToEnterprise() throws Exception {
        if (run) {

            String siteUrl = "http://" + RandomGenerator.nextString() + ".com";
            new UserActions(driver, wait).createSite(siteUrl);
            Long siteId = null;
            changeTariff("Free");
            for (int i = 0; i < 5; i++) {
                siteId = gravitecServer.getSiteId(siteUrl);
                if (siteId != null) break;
                Thread.sleep(500);
            }
            changeSiteFollowersAmount(siteId, 1001);
            changeTariff("SMB");
            setDebt(0);
            setPrice(0);
            setDiscount(0);
            User user = gravitecServer.getUserFromStatus();


            Assert.assertEquals((user.getNewTariff().getOverPrice() * (user.getFollowersAmount() + 999) / 1000), (long) user.getCurrentPrice());

            changeSiteFollowersAmount(siteId, 0);
        }else {
            Log.info(this.getClass().getSimpleName() + ": Current feature is not allowed to test on " + TestServerConfiguretion.iTest);
            throw new SkipException("Skipped");        }
    }


    private void changeTariff(String name) throws MalformedURLException {
        int status = gravitecServer.changeTariff(name, null, null, null);
        Assert.assertEquals(status, 200);
    }

    private void setPrice(Integer price) throws MalformedURLException {
        int status = gravitecServer.changeTariff(null, price, null, null);
        Assert.assertEquals(status, 200);
    }

    private void setDebt(Integer debt) throws MalformedURLException {
        int status = gravitecServer.changeTariff(null, null, debt, null);
        Assert.assertEquals(status, 200);
    }

    private void setDiscount(Integer discount) throws MalformedURLException {
        int status = gravitecServer.changeTariff(null, null, null, discount);
        Assert.assertEquals(status, 200);
    }

    private void executeBilling(String method) {
        int status = gravitecServer.executeBillingMethod(method);
        Assert.assertEquals(status, 200);
    }

    private void changeSiteFollowersAmount(Long siteId, int amount) {
        int status = gravitecServer.changeFollowers(siteId, amount);
        Assert.assertEquals(status, 200);
    }
}


