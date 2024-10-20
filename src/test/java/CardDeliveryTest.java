import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;


public class CardDeliveryTest {
    private String generateDate() {
        return LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void shouldCardDeliveryTest() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Казань");
        String planingDate = generateDate();
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id=date] input").setValue(planingDate);
        $("[data-test-id=name] input").setValue("Петров-Иванов Тимофей");
        $("[data-test-id=phone] input").setValue("+79521361515");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planingDate));

    }

}