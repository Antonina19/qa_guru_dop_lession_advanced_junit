package guru.qa.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class YandexMainPage {

    public static final String URL = "http://ya.ru";

    private SelenideElement searchInput = $(".input__control");
    private SelenideElement searchBtn = $("button[type='submit']");

    public YandexResultsPage doSearch(String querty) {
        searchInput.setValue(querty);
        searchBtn.click();
        return new YandexResultsPage();


    }

}
