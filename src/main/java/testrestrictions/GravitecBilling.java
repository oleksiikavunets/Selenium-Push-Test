package testrestrictions;


import com.selenium.ConfigTest;

public class GravitecBilling {
    ConfigTest configTest = new ConfigTest();
    String runFullBillingOnly = "7700";
    String runSmokeOnly = "prod";
    String runnigNow = configTest.iTest;

    public boolean verifyBillingToRun() {
        boolean check;
        if (runnigNow.equals(runFullBillingOnly))
            check = true;
        else {
            check = false;
        }
        return check;
    }

    public boolean verifySmokePaymentToExecute() {
        boolean check;

        if (runnigNow.equals(runFullBillingOnly) || runnigNow.equals(runSmokeOnly)) {
            check = true;
        } else {
            check = false;
        }
        return check;
    }
}
