package Airlines_UI_Tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class Ryanair {

    @Test
    public void ticketsReservationRyanair() {
        Configuration.startMaximized = true;
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;

        open("https://www.ryanair.com/");
        $("button.cookie-popup-with-overlay__button").click();
        $x("//button[@class='language-selector__button ng-star-inserted']").click();
        $("a[href='/gb/en']").click();

        $("#input-button__departure").clear();
        $("#input-button__departure").click();
        $("#input-button__departure").val("Vienna");
        $("span[data-id='VIE']").click();
        $("#input-button__destination").click();
        $("#input-button__destination").val("Kyiv");
        $("span[data-id='KBP']").click();

        $("div[data-ref='calendar-btn-next-month']").click();
        $("div[data-id='2021-08-19']").click();
        $("div[data-id='2021-08-22']").click();

        $x("(//div[@data-ref='counter.counter__increment'])[1]").click();
        $x("//button[@class='passengers__confirm-button ry-button--anchor-blue ry-button--anchor']").click();
        $("button[data-ref='flight-search-widget__cta']").click();

        $$("journey-container>journey").shouldHaveSize(2);
        $x("(//div[contains(.,'Return')])[4]").shouldHave(Condition.text("19 Aug"));
        $x("(//div[contains(.,'Return')])[4]").shouldHave(Condition.text("22 Aug"));

        $x("//button[contains(text(),'Edit search')]").click();
        $x("(//div[@class='input-button__input input-button__display-value--truncate-text ng-star-inserted'])[3]").shouldHave(Condition.text("2 Adults"));
    }
}
