package testconfigs.testdata.testmails;

import testconfigs.baseconfiguration.TestServerConfiguretion;
import com.selenium.enums.Server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.selenium.enums.Server.*;

public class RegistrationMails {

    private List<String> gravitecEN = new ArrayList<>(Arrays.asList(
            "Complete Registration in Gravitec.net",
            "You've just registered in Gravitec Dashboard. Please open the link (click the button) below to verify your email address:",
            "Complete registration",
            "Someone (hopefully you) has used this email to register in gravitec.net. If it wasn't you - just ignore this email. In this case, your account will not be activated.",
            "Any questions? Feel free to ask us by reply to this email.",
            "unsubscribe"));
    private List<String> gravitecPL = new ArrayList<>(Arrays.asList(
            "Zakończ Rejestrację w Gravitec.net",
            "Zarejestrowałeś się do Panelu Administracyjnego Gravitec. Otwórz proszę poniższy link (kliknij w przycisk) w celu weryfikacji Twojego adresu mailowego:",
            "Zakończ rejestrację",
            "Ktoś (mamy nadzieję, że ty) użył tego maila do rejestracji w Gravitec.net. Jeśli to nie ty, po prostu zignoruj tego maila. W tym wypadku konto nie będzie aktywowane.",
            "Jakieś pytania? Zadaj je odpowiadając na tego maila.",
            "desubskrybuj"));
    private List<String> gravitecUA = new ArrayList<>(Arrays.asList(
            "Активація вашого облікового запису в Gravitec.net",
            "Доброго дня! Ваш email було використано для реєстрації в сервісі Gravitec.net. Щоб активувати ваш обліковий запис, будь ласка, перейдіть за посиланням:",
            "Активувати обліковий запис",
            "Якщо ви не реєструвалися в сервісі, просто проігноруйте цей лист: в такому випадку ваш обліковий запис не буде створений.",
            "Якщо у вас є питання або вам потрібна допомога у використанні сервісу, напишіть нам у відповідь на цей лист.",
            "відписатися від розсилок"
    ));
    private List<String> gravitecRU = new ArrayList<>(Arrays.asList(
            "Активация вашего аккаунта в Gravitec.net",
            "Здравствуйте! Ваш email был использован для регистрации в сервисе Gravitec.net. Чтобы активировать вашу учетную запись, пожалуйста, перейдите по следующей ссылке:",
            "Активировать учетную запись",
            "Если вы не регистрировались в сервисе, просто проигнорируйте это письмо: в таком случае ваша учетная запись не будет создана.",
            "Если у вас есть вопросы или вам нужна помощь в использовании сервиса, напишите нам в ответе на это письмо.",
            "отписаться от рассылок"));
    private List<String> gravitecDE = new ArrayList<>(Arrays.asList(
            "Vollständige Anmeldung bei Gravitec.net",
            "Du hast dich gerade bei Gravitec angemeldet. Bitte öffnen den Link (klicken auf den Button) unten, um deine E-Mail-Adresse zu bestätigen:",
            "Vollständige Anmeldung",
            "Jemand (hoffentlich du) hat diese E-Mail verwendet, um sich bei gravitec.net anzumelden. Wenn du es nihct wars - ignorieren einfach diese E-Mail. Dein Konto wird nicht aktiviert.",
            "Irgendwelche Fragen? Wir helfen gerne! Schreibe uns einfach eine E-mail.",
            "abmelden"));
    private List<String> wpushUK = new ArrayList<>(Arrays.asList(
            "Активація Вашого облікового запису в wpush.biz",
            "Вітаю! Ваш e-mail був використаний для реєстрації в сервісі wpush.biz. Для того, щоб активувати Ваш обліковий запис, будь ласка, перейдіть по наступному посиланню:",
            "Активувати обліковий запис",
            "Якщо Ви не реєструвалися в сервісі, просто проігноруйте цей лист: в такому випадку, Ваш обліковий запис не буде створений.",
            "З повагою, команда wpush.biz",
            "Якщо у Вас виникли питання або Вам потрібна допомога у використанні сервісу, напишіть нам у відповідь на цей лист.",
            "Скасувати підписку"));
    private List<String> push2bEN = new ArrayList<>(Arrays.asList(
            "Complete Registration in Push2b.com",
            "You've just registered in Push2b Dashboard. Please open the link (click the button) below to verify your email address:",
            "Complete registration",
            "Someone (hopefully you) has used this email to register in Push2b.com. If it wasn't you - just ignore this email. In this case, your account will not be activated.",
            "Any questions? Feel free to ask us by reply to this email.",
            "unsubscribe"
    ));
    private List<String> push2bRU = new ArrayList<>(Arrays.asList(
            "Активация вашего аккаунта в push2b.com",
            "Здравствуйте! Ваш email был использован для регистрации в сервисе push2b.com Чтобы активировать вашу учетную запись, пожалуйста, перейдите по следующей ссылке:",
            "Активировать учетную запись",
            "Если вы не регистрировались в сервисе, просто проигнорируйте это письмо: в таком случае ваша учетная запись не будет создана.",
            "Если у вас есть вопросы или вам нужна помощь в использовании сервиса, напишите нам в ответе на это письмо.",
            "отписаться от рассылок"));
    private List<String> push2bPL = new ArrayList<>(Arrays.asList(
            "Zakończ Rejestrację w push2b.com",
            "Zarejestrowałeś się do Panelu Administracyjnego push2b.com. Otwórz proszę poniższy link (kliknij w przycisk) w celu weryfikacji Twojego adresu mailowego:",
            "Zakończ rejestrację",
            "Ktoś (mamy nadzieję, że ty) użył tego maila do rejestracji w push2b.com. Jeśli to nie ty, po prostu zignoruj tego maila. W tym wypadku konto nie będzie aktywowane.",
            "Jakieś pytania? Zadaj je odpowiadając na tego maila.",
            "desubskrybuj"
    ));
    private List<String> push2bDE = new ArrayList<>(Arrays.asList(
            "Vollständige Anmeldung bei push2b.com",
            "Du hast dich gerade bei push2b.com angemeldet. Bitte öffnen den Link (klicken auf den Button) unten, um deine E-Mail-Adresse zu bestätigen:",
            "Vollständige Anmeldung",
            "Jemand (hoffentlich du) hat diese E-Mail verwendet, um sich bei push2b.com anzumelden. Wenn du es nihct wars - ignorieren einfach diese E-Mail. Dein Konto wird nicht aktiviert.",
            "Irgendwelche Fragen? Wir helfen gerne! Schreibe uns einfach eine E-mail.",
            "abmelden"
    ));
    private HashMap<String, List> gravitecMails = new HashMap<>();
    private HashMap<String, List> wpushMails = new HashMap<>();
    private HashMap<String, List> push2bMails = new HashMap<>();
    private HashMap<String, List> mails;

    public HashMap<String, List> getMails(Server serverUnderTest) {
        if(serverUnderTest.equals(GRV)||
                serverUnderTest.equals(GRV_7700)||
                serverUnderTest.equals(GRV_7600)){
            mails = getGravitecMails();

        }else if(serverUnderTest.equals(WPUSH)||
                serverUnderTest.equals(WPUSH_7700)){
            mails = getWpushMails();

        }else if(serverUnderTest.equals(P2B)){
            mails = getPush2bMails();
        }
        return mails;
    }
    public String getActivate(String key){
        HashMap<String, List> mails = getMails(TestServerConfiguretion.iTest);
        List<String> mail = mails.get(key);
        String a = mail.get(2);
        return a;
    }

    private  HashMap<String, List> getWpushMails(){
        wpushMails.put("ru", wpushUK);
        wpushMails.put("ua", wpushUK);
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
        push2bMails.put("en", push2bEN);
        push2bMails.put("pl", push2bPL);
        push2bMails.put("ru", push2bRU);
        push2bMails.put("de", push2bDE);
        return push2bMails;
    }
}
