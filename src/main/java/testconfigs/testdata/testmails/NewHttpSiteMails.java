package testconfigs.testdata.testmails;

import com.selenium.enums.Server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.selenium.enums.Server.*;

public class NewHttpSiteMails {

    private List<String> gravitecEN = new ArrayList<>(Arrays.asList(
            "The new website was registered!",
            "Dear Customer,",
            "You've just added a new website to Gravitec.net dashboard:",
            "To complete integration, please add the following code to the main HTML template of your website pages between the and tags. The higher the better:",
            "Go to site settings",
            "Best regards, Gravitec.net team",
            "Do you have any questions? Feel free to ask them by replying to this email.",
            "unsubscribe"
    ));
    private List<String> gravitecUA = new ArrayList<>(Arrays.asList(
            "Новий сайт створений у Вашому обліковому записі",
            "Доброго дня! У Вашому обліковому записі в Gravitec.net був доданий новий сайт:",
            "Щоб інтегрувати Ваш сайт з сервісом і розпочати збір push-підписників, скопіюйте вказаний нижче код (скрипт) в HTML шаблон Вашого сайту між тегами и . Чим вище тим краще:",
            "Відкрити налаштування сайту",
            "З повагою, команда Gravitec.net",
            "Якщо у вас є питання або вам потрібна допомога у використанні сервісу, напишіть нам у відповідь на цей лист.",
            "відписатися від розсилок"
    ));
    private List<String> gravitecRU = new ArrayList<>(Arrays.asList(
            "Новый сайт создан в Вашем аккаунте",
            "Здравствуйте! В Вашем аккаунте в Gravitec.net был добавлен новый сайт:",
            "Чтобы интегрировать Ваш сайт с сервисом и начать собирать push-подписчиков, скопируйте приведенный ниже код (скрипт) в HTML шаблон Вашего сайта между тегами и . Чем выше тем лучше:",
            "Открыть настройки сайта",
            "С уважением, команда Gravitec.net",
            "Если у Вас есть вопросы или Вам нужна помощь в использовании сервиса, напишите нам в ответе на это письмо.",
            "отписаться от рассылки"
    ));
    private List<String> gravitecPL = new ArrayList<>(Arrays.asList(
            "Nowa strona została zarejestrowana!",
            "Drogi Kliencie,",
            "Właśnie dodałeś nową stronę w Panelu Administracyjnym Gravitec.net",
            "Dodaj poniższy kod do głównego szablonu HTML pomiędzy znakami oraz . Im wyżej tym lepiej:",
            "Przejdź do ustawień strony",
            "Pozdrawiamy serdecznie, Zespół Gravitec.net",
            "Jakieś pytania? Zadaj je odpowiadając na tego maila.",
            "desubskrybuj"
    ));
    private List<String> gravitecDE = new ArrayList<>(Arrays.asList(
            "Die neue Webseite wurde registriert!",
            "Sehr geehrter Kunde,",
            "du hast soeben eine neue Website zum Gravitec.net Dashboard hinzugefügt:",
            "Fügen der HTML-Vorlage (zwischen den und -Tags) den folgenden Code hinzu. Je höher desto besser:",
            "Gehe zu den Seiteneinstellungen",
            "Mit freundlichen Grüßen, das Gravitec.net Team",
            "Irgendwelche Fragen? Wir helfen gerne! Schreibe uns einfach eine E-mail.",
            "abmelden"
    ));

    private List<String> push2bRU = new ArrayList<>(Arrays.asList(
            "Новый сайт создан в Вашем аккаунте",
            "Здравствуйте! В Вашем аккаунте в push2b.com был добавлен новый сайт:",
            "Чтобы интегрировать Ваш сайт с сервисом и начать собирать push-подписчиков, скопируйте приведенный ниже код (скрипт) в HTML шаблон Вашего сайта между тегами и . Чем выше тем лучше:",
            "Открыть настройки сайта",
            "С уважением, команда push2b.com",
            "Если у Вас есть вопросы или Вам нужна помощь в использовании сервиса, напишите нам в ответе на это письмо.",
            "отписаться от рассылки"
    ));

    private List<String> push2bEN = new ArrayList<>(Arrays.asList(
            "The new website was registered!",
            "Dear Customer,",
            "You've just added a new website to Push2B.com dashboard:",
            "To complete integration, please add the following code to the main HTML template of your website pages between the and tags. The higher the better:",
            "Go to site settings"
    ));

    private List<String> wpushUK = new ArrayList<>(Arrays.asList(
            "Новий сайт створений у Вашому обліковому записі",
            "Вітаю! До Вашого облікового запису в wpush.biz було додано новий сайт:",
            "Для того, щоб інтегрувати Ваш сайт з сервісом та почати збирати підписників, скопіюйте вказаний нижче код (скрипт) у HTML шаблон Вашого сайту між тегами и . Чим вище, тим краще:",
            "Відкрити налаштування сайту",
            "З повагою, команда wpush.biz",
            "Якщо у Вас виникли питання або Вам потрібна допомога у використанні сервісу, напишіть нам у відповідь на цей лист.",
            "Скасувати підписку"
    ));

    public HashMap<String, List> getMails(Server serverUnderTest) {
        return serverUnderTest.equals(WPUSH) || serverUnderTest.equals(WPUSH_7700) ? getWpushMails() :
                serverUnderTest.equals(P2B) ? getPush2bMails() : getGravitecMails();
    }

    private HashMap<String, List> getGravitecMails() {
        HashMap<String, List> gravitecMails = new HashMap<>();
        gravitecMails.put("en", gravitecEN);
        gravitecMails.put("pl", gravitecPL);
        gravitecMails.put("ua", gravitecUA);
        gravitecMails.put("ru", gravitecRU);
        gravitecMails.put("de", gravitecDE);
        return gravitecMails;
    }

    private HashMap<String, List> getWpushMails() {
        HashMap<String, List> wpushMails = new HashMap<>();
        wpushMails.put("ru", wpushUK);
        wpushMails.put("ua", wpushUK);
        return wpushMails;
    }

    private HashMap<String, List> getPush2bMails() {
        HashMap<String, List> push2bMails = new HashMap<>();
        push2bMails.put("en", push2bEN);
        push2bMails.put("pl", push2bRU);
        push2bMails.put("ru", push2bRU);
        push2bMails.put("de", push2bRU);
        return push2bMails;
    }
}
