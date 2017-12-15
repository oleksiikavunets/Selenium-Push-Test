package webdriverconfiger;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import java.util.concurrent.TimeUnit;

public class WaitManager {
    public Wait<WebDriver> wait;
    private WebDriver driver;

    public WaitManager(WebDriver driver)
    {
        this.driver = driver;
    }

    public Wait<WebDriver> getWait(){
        wait = new FluentWait<>(driver).withMessage("Element was not found").withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);
        return wait;
    }

    public Wait<WebDriver> getWait(int timeoutSeconds){
        wait = new FluentWait<>(driver).withMessage("Element was not found").withTimeout(timeoutSeconds, TimeUnit.SECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);
        return wait;
    }

    public Wait<WebDriver> getWait(int timeoutSeconds, String message){
        wait = new FluentWait<>(driver).withMessage(message).withTimeout(timeoutSeconds, TimeUnit.SECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);
        return wait;
    }

    public Wait<WebDriver> getWait(int timeoutSeconds, String message, int polling){
        wait = new FluentWait<>(driver).withMessage(message).withTimeout(timeoutSeconds, TimeUnit.SECONDS)
                .pollingEvery(polling, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);
        return wait;
    }

}
