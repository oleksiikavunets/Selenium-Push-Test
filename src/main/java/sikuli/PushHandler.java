package sikuli;

import actions.BrowserMaster;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import java.io.File;

public class PushHandler {

    String path = new File("src/main/resources/sikuliscreens").getAbsolutePath() + "/";
    private WebDriver driver;

    public PushHandler(WebDriver driver){
        this.driver = driver;
    }

    public void clickOnPush(){
        waitAndClick(getScreenShotName());
    }

    public void closePush(){
        waitAndClick("close_chrome.png");
    }

    public Match verifyPushReceived(){
        Screen screen = new Screen();

        return screen.exists(new Pattern(path + "Screenshot_9.png"), 30);
    }




    public String getPushRedirectUrl(int numOfWindows) {
        BrowserMaster browser = new BrowserMaster(driver);
        browser.waitForNumberOfWindowsToBe(numOfWindows + 1)
                .switchToLastTub();
        String url = driver.getCurrentUrl();
        browser.switchToMainTab();
        return url;
    }



    public void clickFirstButton(){
        waitAndClick("button1.png");
    }

    public void clickSecondButton(){
            waitAndClick("button2.png");
    }

    private void waitAndClick(String img){
        Screen screen = new Screen();
        try {
            Pattern pattern = new Pattern(path + img);
            screen.wait(pattern);
            screen.click(pattern);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

    private String getScreenShotName(){
        return Thread.currentThread().getStackTrace()[3].getMethodName() + ".png";
    }
}
