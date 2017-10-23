package com.selenium.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestNGListener;

public class SiteIdListener implements ITestNGListener{
    WebDriver driver;

    public SiteIdListener(WebDriver driver){
        this.driver = driver;
    }

    public void hadleErrorPopUp(){
        boolean displayed = false;
        try {
            displayed = driver.findElement(By.cssSelector("div[class=\"sa-icon sa-error\"]")).isDisplayed();
        }catch (NoSuchElementException el){
        }
        if(displayed){
            driver.findElement(By.cssSelector("button[ng-bind=\"ok | translate\"]")).click();
        }


    }
}
