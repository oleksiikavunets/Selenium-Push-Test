package testdata;

import java.util.HashMap;

public class ErrorMessages {
    HashMap<String, String> requiredField = new HashMap<>();
    HashMap<String, String> selectProtocol = new HashMap<>();
    HashMap<String, String> siteExists = new HashMap<>();
    HashMap<String, String> invalidLinkFormat = new HashMap<>();
    HashMap<String, String> incorrectEmail = new HashMap<>();
    HashMap<String, String> incorrectPassword = new HashMap<>();
    HashMap<String, String> emailNotExists = new HashMap<>();
    HashMap<String, String> invalidEmailFormat = new HashMap<>();
    HashMap<String, String> smallPasword = new HashMap<>();
    HashMap<String, String> passNotMatch = new HashMap<>();
    HashMap<String, String> emailxists = new HashMap<>();
    HashMap<String, String> noTags = new HashMap<>();
    HashMap<String, String> noName = new HashMap<>();

    public HashMap<String, String> getNoTags() {
        noTags.put("en", "Tag-list must contain at least 2 tags");
        noTags.put("pl", "Lista tagów musi zawierać minimum 2 tagi");
        noTags.put("ru", "Тег-лист должен содержать хотя бы 2 тега");
        noTags.put("de", "Tagliste muss mindestens 2 Tags beinhalten");

        return noTags;
    }

    public HashMap<String, String> getNoName() {
        noName.put("en", "Tag-list name is empty");
        noName.put("pl", "Nie wprowadzono nazwy dla lista tagów");
        noName.put("ru", "Не введено имя Тег-листа");
        noName.put("de", "Tagliste ist leer");
        return noName;
    }

    public HashMap<String, String> getInvalidEmailFormat() {
        invalidEmailFormat.put("en", "Invalid email format");
        invalidEmailFormat.put("pl", "Niewłaściwy format emaila");
        invalidEmailFormat.put("ru", "Неверный формат");
        invalidEmailFormat.put("de", "Ungültiges Email-Format");
        return invalidEmailFormat;
    }

    public HashMap<String, String> getPassNotMatch() {
        passNotMatch.put("en", "Passwords don't match");
        passNotMatch.put("pl", "Hasła nie zgadzają się");
        passNotMatch.put("ru", "Пароли не совпадают");
        passNotMatch.put("de", "Passwörter stimmen nicht überein");
        return passNotMatch;
    }

    public HashMap<String, String> getSmallPasword() {
        smallPasword.put("en", "Your password must be at least 8 characters long, must contain Latin symbols");
        smallPasword.put("pl", "Twoje hasło musi posiadać minimum 8 znaków");
        smallPasword.put("ru", "Ваш пароль должен быть не менее 8 символов и содержать латинские буквы");
        smallPasword.put("de", "Dein Passwort muss mindestens 8 Zeichen lang sein und muss lateinische Symbole enthalten");
        return smallPasword;
    }

    public HashMap<String, String> getEmailxists() {
        emailxists.put("en", "User email exists");
        emailxists.put("pl", "Ten użytkownik już istnieje");
        emailxists.put("ru", "Пользователь с таким емейлом уже существует");
        emailxists.put("de", "Benutzer-E-Mail existiert");
        return emailxists;
    }

    public HashMap<String, String> getRequiredField() {
        requiredField.put("en", "Required field");
        requiredField.put("pl", "Uzupełnij");
        requiredField.put("ru", "Обязательное поле");
        requiredField.put("de", "Pflichtfeld");
        return requiredField;
    }

    public HashMap<String, String> getSelectProtocol() {
        selectProtocol.put("en", "Select protocol");
        selectProtocol.put("pl", "Wybierz protokół");
        selectProtocol.put("ru", "Выберите протокол");
        selectProtocol.put("de", "Protokoll auswählen");
        return selectProtocol;
    }

    public HashMap<String, String> getSiteExists() {
        siteExists.put("en", "Site with such url already exists.");
        siteExists.put("pl", "Strona o podanym url już istnieje");
        siteExists.put("ru", "Сайт с таким URL уже существует");
        siteExists.put("de", "Seite mit einer solchen URL egzistiert bereits.");
        return siteExists;
    }

    public HashMap<String, String> getInvalidLinkFormat() {
        invalidLinkFormat.put("en", "Invalid link format");
        invalidLinkFormat.put("pl", "Niewłaściwy format linku");
        invalidLinkFormat.put("ru", "Неверный формат ссылки");
        invalidLinkFormat.put("de", "Ungültiges Link-Format");
        return invalidLinkFormat;
    }

    public HashMap<String, String> getIncorrectEmail() {
        incorrectEmail.put("en", "Incorrect email");
        incorrectEmail.put("pl", "Nieprawidłowy email");
        incorrectEmail.put("ru", "Неверный email");
        incorrectEmail.put("de", "Falsche e-Mail-Adresse");

        return incorrectEmail;
    }

    public HashMap<String, String> getIncorrectPassword() {
        incorrectPassword.put("en", "Incorrect password");
        incorrectPassword.put("pl", "Błędne hasło");
        incorrectPassword.put("ru", "Неверный пароль");
        incorrectPassword.put("de", "Falsches Passwort");
        return incorrectPassword;
    }

    public HashMap<String, String> getEmailNotExists() {
        emailNotExists.put("en", "User with e-mail wrongemail@email.pms does not exist");
        emailNotExists.put("ru", "Пользователей с электронной почтой wrongemail@email.pms не существует");
        emailNotExists.put("pl", "Użytkownik zarejestrowany jako wrongemail@email.pms nie istnieje");
        emailNotExists.put("de", "Benutzer mit dieser E-Mail wrongemail@email.pms existiert nicht");

        return emailNotExists;
    }
}