package testrestrictions;

import com.selenium.ConfigTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BetaFeatures {

    static List<String> WMwithButtonsAndBigImage = new ArrayList<>(Arrays.asList(
            "kyivstar",
            "kyivstar7700",
            "push2b"

    ));

    static List<String> buttonsAndBigImage = new ArrayList<>(Arrays.asList(

            ));
    static List<String> UTM = new ArrayList<>(Arrays.asList(
            ));
    static List<String> copyCampaign = new ArrayList<>(Arrays.asList(
            ));

    static HashMap<String, List> betaFeatures = new HashMap<>();

    public static boolean verifyBetaToTest(String beta) {
        boolean check;

        betaFeatures.put("buttonsAndBigImage", buttonsAndBigImage);
        betaFeatures.put("UTM", UTM);
        betaFeatures.put("copyCampaign", copyCampaign);
        betaFeatures.put("WMwithButtonsAndBigImage", WMwithButtonsAndBigImage);

        List<String> portList = betaFeatures.get(beta);
        if (portList.contains(ConfigTest.iTest)) {
            check = false;
        } else {
            check = true;
        }
        return check;
    }
}

