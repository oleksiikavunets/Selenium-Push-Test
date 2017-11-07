
import com.selenium.ConfigTest;
import com.selenium.pojo.Alias;
import com.selenium.pojo.Message;
import com.selenium.pojo.Tag;
import com.selenium.utils.RandomGenerator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.Parameters;

import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SeleniumSubscriberTests extends SeleniumBaseClass {
@Parameters("browser")
    @Before
    public void configureWebDriver(String browser) {
        driver = getConfiguredWebDriver(browser, false);
        wait = new FluentWait<WebDriver>(driver).withMessage("Element was not found").withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(100, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);
    }

    @Test
    public void chromeTestSubscription() throws InterruptedException {
        long followers = gravitecServer.getTestSiteFollowersCount();
        subscribe();
        Assert.assertEquals(followers, gravitecServer.getTestSiteFollowersCount() - 1);
    }

    @Test
    public void chromeTestReceived() throws InterruptedException {
        subscribe();
        long messageId = gravitecServer.sendMessage();
        Message message = gravitecServer.getMessage(messageId);
        for (int i = 0; i < 30; i++) {
            Thread.sleep(1000L);
            message = gravitecServer.getMessage(messageId);
            if (message.getReceived() >= 1) break;
        }
        System.out.println(message);
        Assert.assertEquals(message.getClicked(), 0);
        Assert.assertTrue(message.getReceived() >= 1);
        Assert.assertTrue(message.getSend() >= 1);
    }

    @Test
    public void chromeTestTags() throws InterruptedException {
        subscribe();
        String randomTagName1 = RandomGenerator.nextString();
        ((JavascriptExecutor) driver).executeScript("Gravitec.addTag('" + randomTagName1 + "')");
        Thread.sleep(500);
        Assert.assertTrue(gravitecServer.getTags().contains(new Tag(1L, randomTagName1)));
        String randomTagName2 = String.valueOf(new Random().nextInt());
        ((JavascriptExecutor) driver).executeScript("Gravitec.setTags(['" + randomTagName2 + "'])");
        Thread.sleep(500);
        Assert.assertFalse(gravitecServer.getTags().contains(new Tag(1L, randomTagName1)));
        Assert.assertTrue(gravitecServer.getTags().contains(new Tag(1L, randomTagName2)));
        ((JavascriptExecutor) driver).executeScript("Gravitec.removeTag('" + randomTagName2 + "')");
        Thread.sleep(500);
        Assert.assertFalse(gravitecServer.getTags().contains(new Tag(1L, randomTagName2)));
    }

    @Test
    public void testDuplicatedTags() throws InterruptedException {
        subscribe();
        String randomTagName1 = RandomGenerator.nextString();
        ((JavascriptExecutor) driver).executeScript("for (var i = 0; i < 10; i++) Gravitec.addTag('" + randomTagName1 + "')");
        Assert.assertTrue(gravitecServer.getTags().contains(new Tag(1L, randomTagName1)));
        Assert.assertEquals(gravitecServer.getTags().size(), new HashSet<Tag>(gravitecServer.getTags()).size());
        ((JavascriptExecutor) driver).executeScript("Gravitec.removeTag('" + randomTagName1 + "')");
    }

    @Test
    public void chromeTestAliases() throws InterruptedException {
        subscribe();
        String randomAliasName1 = RandomGenerator.nextString();
        ((JavascriptExecutor) driver).executeScript("Gravitec.setAlias('" + randomAliasName1 + "')");
        Thread.sleep(2000L);
        Assert.assertTrue(gravitecServer.getAliases(randomAliasName1).contains(new Alias(1L, randomAliasName1)));
    }


    public void test() {
        driver.navigate().to(new ConfigTest().getTestSiteUrl());
        driver.findElement(By.className("modal-body-button-text")).click();
        ((JavascriptExecutor) driver).executeScript("alert('hello world');");
    }

    public void tags() {
    }

    private  void startFF() {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();
        profile.setAcceptUntrustedCertificates(true);
        profile.setAssumeUntrustedCertificateIssuer(true);
        options.setProfile(profile);
        System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");

        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);

        driver = new FirefoxDriver(capabilities);
        driver.get("https://google.com");
    }

    private void subscribe() throws InterruptedException {
        driver.navigate().to(new ConfigTest().getTestSiteUrl());
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-body-button-text")));
        driver.findElement(By.className("modal-body-button-text")).click();
        Thread.sleep(2000L);
    }
}