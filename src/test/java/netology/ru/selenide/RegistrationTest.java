package netology.ru.selenide;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTest {

    private LocalDate date = LocalDate.now();
    private LocalDate localDate;

    @Test
    void shouldRegister() {
        open("http://localhost:7777");
        $("[placeholder= 'Город']").setValue("Санкт-Петербург");
        $("span.menu-item__control").click();
        $("[placeholder='Дата встречи']").sendKeys(Keys.CONTROL +"a");
        $("[placeholder='Дата встречи']").sendKeys(Keys.DELETE);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        localDate = date.plusDays(3);
        String futureDate = formatter.format(localDate);
        $("[placeholder='Дата встречи']").setValue(futureDate);
        $("[name='name']").setValue("Василий Шукшин");
        $("[name='phone']").setValue("+79123456789");
        $("span[class='checkbox__text']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("div.notification__title").waitUntil(visible, 15000);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}
