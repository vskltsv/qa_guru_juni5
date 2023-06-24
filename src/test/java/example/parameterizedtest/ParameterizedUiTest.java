package example.parameterizedtest;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ParameterizedUiTest extends BaseTest {
    @DisplayName("Проверка поиска статьи на wikipedia")
    @ValueSource(strings = {"Владимир", "Казахстан"})
    @ParameterizedTest(name = "Проверка результата поиска для запроса {0}")
    void wikipediaSearchTest(String testData) {
        open("https://ru.wikipedia.org/");
        $("#searchInput").setValue(testData);
        $("#searchButton").click();
        $(".mw-page-title-main").shouldHave(text(testData));
    }


    static Stream<Arguments> getTopicCategories() {
        return Stream.of(
                Arguments.of("Новости", List.of("В мире", "Новости Москвы", "Политика", "Общество", "Происшествия", "Наука и техника", "Шоу-бизнес", "Военные новости", "Аналитика", "Игры")),
                Arguments.of("Спорт", List.of("Футбол", "Хоккей", "Бокс", "MMA", "Автоспорт", "Теннис", "Баскетбол", "Легкая атлетика", "Биатлон","Фигурное катание","Лыжный спорт","Шахматы","Ещё"))
        );
    }

    @ParameterizedTest(name = "Соответствие списка категорий заданному топику {0}")
    @MethodSource("getTopicCategories")
    public void categoryShouldBeOpenAfterClick(String categoryName, List<String> buttonName) {
        open("https://www.rambler.ru/");
        $(".rc__XaSn3").$(byText(categoryName)).click();
        $$("._3Ufez li").filter(visible).shouldHave(texts(buttonName));
    }
}
