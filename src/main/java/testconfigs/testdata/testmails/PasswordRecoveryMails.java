package testconfigs.testdata.testmails;

import com.selenium.enums.Server;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import static com.selenium.enums.Server.*;

public class PasswordRecoveryMails {
    private List<String> gravitecEN = new ArrayList<>(Arrays.asList(
            "Reset the password in Gravitec.net",
            "Someone (hopefully you) asked us for a password reset link for your account in Gravitec Dashboard",
            "To reset your password, please click the button below:",
            "Reset the password",
            "If you did not request a password reset, just ignore this email.",
            "Do you have any questions? Feel free to ask us by reply to this email.",
            "unsubscribe"
    ));
    private List<String> gravitecPL = new ArrayList<>(Arrays.asList(
            "Resetuj hasło w Gravitec.net",
            "Ktoś (mamy nadzieję, że ty) chce zresetować hasło swojego konta w panelu administracyjnym Gravitec",
            "W celu zmiany hasła kliknij poniższy przycisk:",
            "Resetuj hasło",
            "Jeśli to nie ty prosiłeś o zmianę hasła, zignoruj tą wiadomość.",
            "Jakieś pytania? Zadaj je odpowiadając na tego maila.",
            "desubskrybuj"
    ));
    private List<String> gravitecUA = new ArrayList<>(Arrays.asList(
            "Сброс пароля в Gravitec.net",
            "Здравствуйте! Для вашей учетной записи в сервисе Gravitec",
            "был запрошен сброс пароля. Чтобы задать новый пароль, перейдите по следующей ссылке:",
            "Сбросить пароль",
            "Если вы не запрашивали сброс пароля, просто проигнорируйте это письмо.",
            "Если у вас есть вопросы или вам нужна помощь в использовании сервиса, напишите нам в ответе на это письмо.",
            "отписаться от рассылок"
    ));
    private List<String> gravitecRU = new ArrayList<>(Arrays.asList(
            "Сброс пароля в Gravitec.net",
            "Здравствуйте! Для вашей учетной записи в сервисе Gravitec",
            "был запрошен сброс пароля. Чтобы задать новый пароль, перейдите по следующей ссылке:",
            "Сбросить пароль",
            "Если вы не запрашивали сброс пароля, просто проигнорируйте это письмо.",
            "Если у вас есть вопросы или вам нужна помощь в использовании сервиса, напишите нам в ответе на это письмо.",
            "отписаться от рассылок"
    ));
    private List<String> gravitecDE = new ArrayList<>(Arrays.asList(
            "Passwort in Gravitec.net zurücksetzen",
            "Jemand (hoffentlich du) möchte das Account-Passwort im Gravitec-Dashboard",
            "zurücksetzen. Um das Passwort zurückzusetzen, klicken bitte auf den Button unten:",
            "Passwort zurücksetzen",
            "Wenn du das Passwort nich zurücksetzen möchtest, ignorieren einfach diese E-Mail.",
            "Irgendwelche Fragen? Wir helfen gerne! Schreibe uns einfach eine E-mail.",
            "abmelden"
    ));

    private List<String> wpushUK = new ArrayList<>(Arrays.asList(


    ));
    private List<String> push2bRU = new ArrayList<>(Arrays.asList(
            "Сброс пароля в push2b.com",
            "Здравствуйте! Для вашей учетной записи в сервисе push2b.com",
            "был запрошен сброс пароля. Чтобы задать новый пароль, перейдите по следующей ссылке:",
            "Сбросить пароль",
            "Если вы не запрашивали сброс пароля, просто проигнорируйте это письмо.",
            "Если у вас есть вопросы или вам нужна помощь в использовании сервиса, напишите нам в ответе на это письмо.",
            "отписаться от рассылок"
    ));

    private HashMap<String, List> gravitecMails = new HashMap<>();
    private HashMap<String, List> wpushMails = new HashMap<>();
    private HashMap<String, List> push2bMails = new HashMap<>();
    private HashMap<String, List> mails;

    public HashMap<String, List> getMails(Server serverUnderTest) {
        if(serverUnderTest.equals(GRV)||
                serverUnderTest.equals(GRV_7700)||
                serverUnderTest.equals(GRV_7600)||
                serverUnderTest.equals(UBR)) {
            mails = getGravitecMails();
        }
        else if(serverUnderTest.equals(WPUSH)||
                serverUnderTest.equals(WPUSH_7700)){
            mails = getWpushMails();
        }
        else if(serverUnderTest.equals(P2B)){
            mails = getPush2bMails();
        }
        return mails;
    }
    private  HashMap<String, List> getWpushMails(){
        wpushMails.put("ru", wpushUK);
        wpushMails.put("uk", wpushUK);
        return wpushMails;
    }

    private  HashMap<String, List> getGravitecMails() {
        gravitecMails.put("en", gravitecEN);
        gravitecMails.put("ua", gravitecUA);
        gravitecMails.put("pl", gravitecPL);
        gravitecMails.put("ru", gravitecRU);
        gravitecMails.put("de", gravitecDE);
        return gravitecMails;
    }

    private  HashMap<String, List> getPush2bMails() {
        push2bMails.put("en", push2bRU);
        push2bMails.put("pl", push2bRU);
        push2bMails.put("ru", push2bRU);
        push2bMails.put("de", push2bRU);
        return push2bMails;
    }
}
