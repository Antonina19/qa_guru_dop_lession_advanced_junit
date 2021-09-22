package guru.qa;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import guru.qa.page.YandexMainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

//@Execution(value = ExecutionMode.SAME_THREAD) // все что в этом классе, надо выполнить в одном потоке
@ExtendWith(SimpleCallback.class)
public class ParallelTest {

    private YandexMainPage ymp = new YandexMainPage();

    @ValueSource(strings = {
            "qa.guru",
            "selenide",
            "qameta",
            "allure"
    })
    // @ResourceLock("1")
    // @Execution(value = ExecutionMode.SAME_THREAD) // все 4 теста будут выполнены в одном потоке
    @ParameterizedTest(name = "{0} test")
    void yandexSearchTest(String searchQuery, TestInfo testInfo) {
        Configuration.startMaximized = true;
        Selenide.open(YandexMainPage.URL);

        ymp.doSearch(searchQuery).checkResults(searchQuery);
//        Selenide.$(".input__control").setValue(searchQuery);
//        Selenide.$("button[type='submit']").click();
//        Selenide.$$(".serp-item").shouldBe(CollectionCondition.sizeGreaterThan(0))
//                .get(1)
//                .shouldHave(Condition.text(searchQuery));
        System.out.println("Config for test: " + testInfo.getDisplayName() + " " + Configuration.startMaximized);
    }

    @DisplayName("JDI")
    @Test
    void minimizedWindowTest(TestInfo testInfo) {
        Configuration.startMaximized = false;
        Selenide.open(YandexMainPage.URL);
        ymp.doSearch("JDI").checkResults("JDI");
        System.out.println("Config for test: " + testInfo.getDisplayName() + " " + Configuration.startMaximized);
        ymp = null;
    }
}
