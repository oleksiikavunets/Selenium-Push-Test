package pageutils;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class PushHandler {

    String path = "src/main/resources/sikuliscreens/";



    public void clickOnPush(){
//        Pattern pattern = new Pattern("src/main/resources/sikuliscreens/SimplePush.png");
        waitAndClick("C:\\Users\\OleksiiKavunets\\Documents\\Selenium\\src\\main\\resources\\sikuliscreens\\Screenshot_9.png");
    }

    public void closePush(){
        Pattern pattern = new Pattern();
        waitAndClick("C:\\Users\\OleksiiKavunets\\Documents\\Selenium\\src\\main\\resources\\sikuliscreens\\Screenshot_9.png");
    }

    public Match verifyPushReceived(){
        Screen screen = new Screen();

        return screen.exists(new Pattern("C:\\Users\\OleksiiKavunets\\Documents\\Selenium\\src\\main\\resources\\sikuliscreens\\Screenshot_9.png"), 30);
    }


    public void clickFirstButton(){
        Pattern pattern = new Pattern();
        waitAndClick("");
    }

    public void clickSecondButton(){

        waitAndClick("");
    }

    private void waitAndClick(String img){
        Screen screen = new Screen();
        try {

            Pattern pattern = new Pattern(img);
            screen.wait(pattern);
            screen.click(pattern);
        } catch (FindFailed findFailed) {

            findFailed.printStackTrace();
        }
    }
}
