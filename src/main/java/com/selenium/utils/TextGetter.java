package com.selenium.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class TextGetter {

    public static List<String> textOf(List<WebElement> col) {
        return col.stream().map(WebElement :: getText).collect(Collectors.toList());
    }

    public static String textOf(WebElement element){
        return element.getText();
    }

    public static String textOf(By locator, WebDriver driver){
        String text = locator.findElement(driver).getText();
        return text;
    }


}
