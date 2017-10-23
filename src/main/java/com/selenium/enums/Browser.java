package com.selenium.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RAYANT on 19.02.2016.
 */
public enum Browser {
    CHROME,
    FIREFOX,
    ANDROID,
    SAFARI,
    IOS,
    IOS_DEV,
    WEB,
    CHROME50,
    YANDEX,
    UNDEFINED,
    OPERA;

   public static List<Browser> getSupportedBrowsers(){
       ArrayList<Browser> browsers = new ArrayList<>();
       browsers.add(CHROME);
       browsers.add(FIREFOX);
       browsers.add(SAFARI);
       browsers.add(CHROME50);
       browsers.add(YANDEX);
       browsers.add(OPERA);
       return browsers;
   }

}
