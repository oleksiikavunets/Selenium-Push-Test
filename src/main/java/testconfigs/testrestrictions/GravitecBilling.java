package testconfigs.testrestrictions;


import testconfigs.baseconfiguration.TestServerConfiguretion;
import com.selenium.enums.Server;

import static com.selenium.enums.Server.GRV;
import static com.selenium.enums.Server.GRV_7700;

public class GravitecBilling {
    Server runFullBillingOnly = GRV_7700;
    Server runSmokeOnly = GRV;
    Server serverToTest= TestServerConfiguretion.iTest;

    public boolean verifyBillingToRun() {
        boolean check;
        if (serverToTest.equals(runFullBillingOnly))
            check = true;
        else {
            check = false;
        }
        return check;
    }

    public boolean verifySmokePaymentToExecute() {
        boolean check;

        if (serverToTest.equals(runFullBillingOnly) ||
                serverToTest.equals(runSmokeOnly)) {
            check = true;
        } else {
            check = false;
        }
        return check;
    }
}
