
import org.testng.Assert;
import testutils.Listeners.LogListener;
import com.selenium.utils.RandomGenerator;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.*;
import testdata.TestData;

import static testdatamanagers.TestSiteManager.getTestSiteUrl;

/**
 * Created by Oleksii on 03.07.2017.
 */
@Listeners(LogListener.class)
public class Test_Pos_SendMessage extends BaseTestClass {

    @Test(groups = {"send push"})
    public void sendMessage() throws Exception {
     LogInPage logInPage = new LogInPage(driver);
     String title = RandomGenerator.nextString();
        String text = RandomGenerator.nextString();
        String testSite = getTestSiteUrl();

        CampaignHistoryPage campaignHistoryPage = logInPage.login(TestData.email, TestData.pass).openSite(testSite)
                .openCreateCampaignPage().setTitle(title).setText(text)
                .sendPush();
        Assert.assertTrue(campaignHistoryPage.verifyMessageExists(title), "Could not find sent message");
    }


/**
 @Ignore("Not not checked on prod yet")
 @Test public void receivePushNotification() throws Exception{
 String email = "test@gravitec.net";
 String pass = "mpsprod7781";

 login(email, pass);
 wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("http://restapiprod.at.ua"))).click();
 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@ng-bind=\"'LMENU_NEW_CAMP' | translate\"]"))).click();
 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("push-title"))).sendKeys("Se Test. title");
 driver.findElement(By.name("text")).sendKeys("Se test. text");
 driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
 try {
 Thread.sleep(2000);
 } catch (InterruptedException e) {
 e.printStackTrace();
 }

 int i;
 Logs log = driver.manage().logs();
 for (i = 1; i < 100; i++) {
 List<LogEntry> logsEntries = log.get("browser").getAll();
 for (LogEntry entry : logsEntries) {
 String q = entry.getMessage();
 System.out.println(q);
 }
 for (LogEntry entry : logsEntries) {
 String s = entry.getMessage();
 if (s.contains("tl:") && s.contains("tx:") && s.contains("redirect:")) {
 System.out.println(s);
 System.out.println("PUSH MESSAGE RECEIVED");
 driver.quit();
 }
 }
 System.out.println(i + ".second  NOT RECEIVED ANY MESSAGE YET");
 try {
 Thread.sleep(1000);
 } catch (InterruptedException e) {
 e.printStackTrace();
 }
 }
 Assert.assertTrue(i != 100);
 }
 */
}
