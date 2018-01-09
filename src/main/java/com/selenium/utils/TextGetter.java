package pageutils;

import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class TextGetter {

    public static List<String> textOf(List<WebElement> col) {
//        List<String> names = col.stream().map(item-> item.getText()).collect(Collectors.toList());
        return col.stream().map(WebElement :: getText).collect(Collectors.toList());
    }

    public static String textOf(WebElement element){
        return element.getText();
    }
}
