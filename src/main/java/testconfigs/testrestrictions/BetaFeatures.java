package testconfigs.testrestrictions;

import com.selenium.enums.Server;
import testconfigs.baseconfiguration.TestServerConfiguretion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BetaFeatures {

    static List<Server> WMwithButtonsAndBigImage = new ArrayList<>(Arrays.asList());
    static List<Server> buttonsAndBigImage = new ArrayList<>(Arrays.asList());
    static List<Server> UTM = new ArrayList<>(Arrays.asList());
    static List<Server> copyCampaign = new ArrayList<>(Arrays.asList());
    static List<Server> imageCropper = new ArrayList<>(Arrays.asList(
    ));

    static HashMap<String, List> betaFeatures = new HashMap<>();

    public static boolean verifyBetaToTest(String beta) {
        boolean check;

        betaFeatures.put("buttonsAndBigImage", buttonsAndBigImage);
        betaFeatures.put("UTM", UTM);
        betaFeatures.put("copyCampaign", copyCampaign);
        betaFeatures.put("WMwithButtonsAndBigImage", WMwithButtonsAndBigImage);
        betaFeatures.put("imageCropper", imageCropper);

        List<Server> portList = betaFeatures.get(beta);
        if (portList.contains(TestServerConfiguretion.iTest)) {
            check = false;
        } else {
            check = true;
        }
        return check;
    }
}

