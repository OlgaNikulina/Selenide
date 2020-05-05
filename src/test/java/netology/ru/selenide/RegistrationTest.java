package netology.ru.selenide;

import com.sun.tools.javac.comp.Enter;
import javafx.scene.control.Tab;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.security.Key;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTest {

    private LocalDate date = LocalDate.of(2020, 5, 02);
    private LocalDate localDate;

    @Test
    void shouldRegister() {
        open("http://localhost:7777");
        $("[placeholder= 'Город']").setValue("Санкт-Петербург");
        $("span.menu-item__control").click();
        $("[type='date']").sendKeys(Keys.CONTROL +"a");
        $("[type='date']").sendKeys(Keys.DELETE);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        localDate = date.plusDays(3);
        String futureDate = formatter.format(localDate);
        $("[type='date']").setValue(futureDate);
        $("[name='name']").setValue("Василий Шукшин");
        $("[name='phone']").setValue("+79123456789");
        $("span[class='checkbox__text']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Успешно! Встреча забронирована на 03.05.2020")).waitUntil(visible, 15000);
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
