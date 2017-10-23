package actions;

import com.selenium.ConfigTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


public class JSRunner {


    public JSRunner(WebDriver driver){
        this.driver = driver;
    }

    WebDriver driver;
    String runningOn = ConfigTest.iTest;

    public void run(String script){
        ((JavascriptExecutor) driver).executeScript(script);
    }


    public void addNewTag(String newTag) throws Exception{
        if(runningOn.equals("prod") || runningOn.equals("7700") || runningOn.equals("7600")) {
            ((JavascriptExecutor) driver).executeScript("Gravitec.addTag('" + newTag + "')");
            System.out.println("Adding tag for Gravitec");
        }else{
            ((JavascriptExecutor) driver).executeScript("WLPush.addTag('" + newTag + "')");
            System.out.println("Adding tag for WL");
        }
        Timer.waitSeconds(0.3);
    }

    public void addNewAlias(String alias) throws Exception{
        if(runningOn.equals("prod") || runningOn.equals("7700") || runningOn.equals("7600")) {
            ((JavascriptExecutor) driver).executeScript("Gravitec.setAlias('" + alias + "')");
            System.out.println("Setting alias for Gravitec");
        }else{
            ((JavascriptExecutor) driver).executeScript("WLPush.setAlias('" + alias + "')");
            System.out.println("Setting alias for WL");
        }
        Timer.waitSeconds(0.3);
    }

    public void openNewTab() {
        ((JavascriptExecutor) driver).executeScript("window.open('','_blank');");
    }
    public void closeTab(){
        ((JavascriptExecutor)driver).executeScript("close();");
    }
}