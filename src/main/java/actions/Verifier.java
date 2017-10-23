package actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import org.testng.collections.Maps;
import testdata.CreateSiteMails;
import testdata.PasswordRecoveryMails;
import testdata.RegistrationMails;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Verifier extends Assertion{
    public Verifier(){}

    private final Map<AssertionError, IAssert<?>> m_errors = Maps.newLinkedHashMap();

    protected void doAssert(IAssert<?> var1) {
        this.onBeforeAssert(var1);

        try {
            var1.doAssert();
            this.onAssertSuccess(var1);
        } catch (AssertionError var6) {
            this.onAssertFailure(var1, var6);
            this.m_errors.put(var6, var1);
        } finally {
            this.onAfterAssert(var1);
        }

    }

    public int verify(String actual, String expected) {
        int errorCount = 0;
        try {
            Assert.assertEquals(actual, expected);
        } catch (AssertionError error) {
            reportAnIssue(getClass().toString(), error);
            errorCount = 1;
        }
        return errorCount;
    }
    public int verify(String actual, String expected, String message) {
        int errorCount = 0;
        try {
            Assert.assertEquals(actual, expected, message);
        } catch (AssertionError error) {
            reportAnIssue(getClass().toString(), error);
            errorCount = 1;
        }
        return errorCount;
    }

    public int verify(Boolean b) {
        int errorCount = 0;
        try {
            Assert.assertTrue(b);
        } catch (AssertionError error) {
            reportAnIssue(getClass().toString(), error);
            errorCount = 1;
        }
        return errorCount;
    }

    public int verify(Boolean b, String message) {
        int errorCount = 0;
        try {
            Assert.assertTrue(b, message);
        } catch (AssertionError error) {
            reportAnIssue(getClass().toString(), error);
            errorCount = 1;
        }
        return errorCount;
    }

    public int verify(int i, int j) {
        int errorCount = 0;
        try {
            Assert.assertTrue(i == j);
        } catch (AssertionError error) {
            reportAnIssue(getClass().toString(), error);
            errorCount = 1;
        }
        return errorCount;
    }
    public int verify(int i, int j, String message) {
        int errorCount = 0;
        try {
            Assert.assertTrue(i == j, message);
        } catch (AssertionError error) {
            reportAnIssue(getClass().toString(), error);
            errorCount = 1;
        }
        return errorCount;
    }

    public void reportAnIssue(String r, AssertionError error) {
        Logger Log = LogManager.getLogger(Verifier.class);
        //Log.info(r + "\n" + error);
        System.out.println(r + "\n" + error);
    }
    public boolean verifyRegistrationMail(String message, String port,  String key ){
        RegistrationMails registrationMails = new RegistrationMails();
        HashMap<String, List> mails = registrationMails.getMails(port);
        List<String> mail = mails.get(key);
        boolean pass = false;
        int errorCount = 0;
        for(int j = 0; j < mail.size(); j++){
            System.out.println("Verifying: " + mail.get(j));
            errorCount += verify(message.contains(mail.get(j)), "Mail does not contain text: " + mail.get(j));
        }
        if (errorCount == 0)pass = true;
        return pass;
    }
    public boolean verifyRecoverPasswordMail(String message, String port, String key){
        PasswordRecoveryMails passwordRecoveryMails = new PasswordRecoveryMails();
        HashMap<String, List> mails = passwordRecoveryMails.getMails(port);
        List<String> mail = mails.get(key);
        boolean pass = false;
        int errorCount = 0;

        for(int j = 0; j < mail.size(); j++){
            System.out.println("Verifying: " + mail.get(j));
            errorCount += verify(message.contains(mail.get(j)), "Mail does not contain text: " + mail.get(j));
        }
        if(errorCount == 0 )pass = true;
        return pass;
    }
    public boolean verifyCreateSiteMail(String message, String port, String key){
        CreateSiteMails createSiteMails = new CreateSiteMails();
        HashMap<String, List> mails = createSiteMails.getMails(port);
        List<String> mail = mails.get(key);
        boolean pass = false;
        int errorCount = 0;

        for(int j = 0; j < mail.size(); j++){
            System.out.println("Verifying: " + mail.get(j));
            errorCount += verify(message.contains(mail.get(j)), "Mail does not contain text: " + mail.get(j));
        }
        if(errorCount == 0 )pass = true;
        return pass;
    }

    public void assertTestPassed() {
        if (!this.m_errors.isEmpty()) {

            StringBuilder var1 = new StringBuilder("The following asserts failed:");
            boolean var2 = true;
            Iterator var3 = this.m_errors.entrySet().iterator();

            while(var3.hasNext()) {
                Map.Entry var4 = (Map.Entry)var3.next();
                if (var2) {
                    var2 = false;
                } else {
                    var1.append(",");
                }

                var1.append("\n\t");
                var1.append(((AssertionError)var4.getKey()).getMessage());
            }
            throw new AssertionError(var1.toString());
        }
    }
}
