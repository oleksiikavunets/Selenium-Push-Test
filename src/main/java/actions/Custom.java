package actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Custom {
    WebDriver driver;
    public Custom(WebDriver driver){
        this.driver = driver;
    }

    public void clickAt(WebElement element){
        JSRunner jsRunner = new JSRunner(driver);
        String el = element.toString().split("selector: ")[1].split("]")[0] + "]";
        jsRunner.run("document.querySelector('" + el + "').click();");
    }





}
