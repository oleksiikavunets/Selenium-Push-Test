package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class Clicker {
    WebDriver driver;
    public Clicker(WebDriver driver){
        this.driver = driver;
    }

    public void clickJS(WebElement element){
        JSRunner jsRunner = new JSRunner(driver);
        String el = element.toString().split("selector: ")[1].split("]")[0] + "]";
        jsRunner.run("document.querySelector('" + el + "').click();");
    }

    public void clickJS(By locator){
        JSRunner jsRunner = new JSRunner(driver);
        String el = locator.toString().split("cssSelector: ")[1].split("]")[0] + "]";
        jsRunner.run("document.querySelector('" + el + "').click();");
    }

    public void click(By locator){
        for(int i = 0; i < 2; i++) {
            try {
                driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
                locator.findElement(driver).click();
            }catch (org.openqa.selenium.NoSuchElementException e){
                break;
            }
        }
    }

    public void click(WebElement element){
        for(int i = 0; i < 2; i++) {
            try {
                driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
                element.click();
            }catch (org.openqa.selenium.NoSuchElementException e){
                break;
            }
        }
    }





}
