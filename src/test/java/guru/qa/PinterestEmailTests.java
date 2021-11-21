package guru.qa;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PinterestEmailTests {

    @ValueSource(strings = {"test.mail.com", "тест@mail.com", "te st@mail.com", "тест@mail.com", "te$t@mail.com", "test@mailcom"})
    @ParameterizedTest(name = "Отображение сообщения об ошибке при вводе мейла {0} при регистрации на pinterest.ru")
    void wrongEmailForRegistrationTest(String email) {
        open("https://www.pinterest.ru/");
        $(byText("Sign up")).click();
        $("#email").sendKeys(email);
        $(".SignupButton").click();
        $("#email-error").shouldHave(Condition.exactText("Hmm...that doesn't look like an email address"));
    }


    @CsvSource(value = {
            "test.mail.com|без собаки",
            "тест@mail.com| с кириллическими символами",
            "te st@mail.com| с пробелом"
    },
            delimiter = '|')
    @ParameterizedTest(name = "Отображение сообщения об ошибке при вводе мейла {1} {0} при регистрации на pinterest.ru")
    void wrongEmailForRegistrationTestwithCSVSource(String email, String description) {
        open("https://www.pinterest.ru/");
        $(byText("Sign up")).click();
        $("#email").sendKeys(email);
        $(".SignupButton").click();
        $("#email-error").shouldHave(Condition.exactText("Hmm...that doesn't look like an email address"));
    }


    @EnumSource(Email.class)
    @ParameterizedTest(name = "Отображение сообщения об ошибке при вводе мейла {0} при регистрации на pinterest.ru")
    void wrongEmailForRegistrationTestWithEnumSource(Email email) {
        open("https://www.pinterest.ru/");
        $(byText("Sign up")).click();
        $("#email").sendKeys(email.getTitle());
        $(".SignupButton").click();
        $("#email-error").shouldHave(Condition.exactText("Hmm...that doesn't look like an email address"));
    }
}
