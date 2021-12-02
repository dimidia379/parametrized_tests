package guru.qa;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;

public class CoffeeSearchTests {

    static Stream<Arguments> relevantSearchResults() {
        return Stream.of(
                Arguments.of("Робуста", List.of("Весь кофе", "Кофе", "Классический", "Ароматизированный", "Кофе в пачках", "ТОП-наборы")),
                Arguments.of("Ямайка Блю Маунтин", List.of("Весь кофе", "Классический", "Кофе", "Кофе в пачках", "Все подарки", "Чайно-кофейные"))
        );
    }

    @MethodSource
    @ParameterizedTest(name = "В категориях поисковых запросов {0} отображается {1}")
    void relevantSearchResults(String searchQuery, List<String> categories) {
        open("https://www.cantata.ru/");
        $(".ui-icon--search").click();
        $(".multi-input").sendKeys(searchQuery);
        $$(".multi-cell a.multi-title span").shouldHave(CollectionCondition.texts(categories));

    }
}
