package Airlines_UI_Tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class Wizzair {

    @Test
    public void ticketsReservationWizzair() {
        Configuration.startMaximized = true;
        Configuration.browser = "chrome";
        Configuration.timeout = 70000;

        open("https://wizzair.com/");
        $("#search-departure-station").val("Vienna");
        $x("//mark[contains(text(),'Vienna')]").click();
        $("#search-arrival-station").val("Kyiv");
        $x("//mark[contains(text(),'Kyiv')]").click();

        $("#search-departure-date").click();
        $("button.pika-next").click();
        $x("//button[@data-pika-year='2021'][@data-pika-month='7'][@data-pika-day='19']").click();
        $x("//button[@data-pika-year='2021'][@data-pika-month='7'][@data-pika-day='22']").click();
        $x("//button[@data-test='calendar-shrinkable-ok-button']").click();

        $("#search-passenger").click();
        $x("//span[contains(text(),' adult ')]/../../button[@data-test='services-flight-search-increment']").click();
        $x("//button[@data-test='flight-search-submit']").click();

        switchTo().window(1);
        $$x("//div[@class='flight-select__fare-selector transition-fade-in transition-zoom-in transition-fade-in-enter transition-zoom-in-enter']").shouldHaveSize(2);
        $$x("//time[@data-test='flight-select-flight-info-details-time']").get(0).shouldHave(Condition.text(" Thu, 19 Aug 2021 "));
        $$x("//time[@data-test='flight-select-flight-info-details-time']").get(1).shouldHave(Condition.text(" Sun, 22 Aug 2021 "));
        $x("//aside//div[@data-test='search-change__details__passengers']").shouldHave(Condition.text(" 2 Passengers "));
    }
}
