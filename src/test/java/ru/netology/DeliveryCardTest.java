package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

class DeliveryCardTest {

    @BeforeEach
    void setUp2() {
        open("http://localhost:9999");
    }

    @Test
    public void successfulAppointmentBooking() {

        LocalDate meetingDate = LocalDate.now().plusDays(4);
        String newDate = meetingDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder=\"Город\"]").setValue("Краснодар");// заполнить поле город
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);// очистить дату
        $("[placeholder=\"Дата встречи\"]").setValue(newDate);// заполнить поле дата
        $("[name = \"name\"]").setValue("Вера-Мира У");//заполнить ФИО
        $("[name = \"phone\"]").setValue("+71111111111");//заполнить телефон
        $("[data-test-id = \"agreement\"]").click();//нажать согласие
        $x("//*[text()=\"Забронировать\"]").click();//нажать забронировать
        $("[data-test-id=\"notification\"]").should(visible, Duration.ofSeconds(15));//дождаться завершения бронирования
    }

    @Test
    public void shouldTownNotAdmCenter() {

        LocalDate meetingDate = LocalDate.now().plusDays(4);
        String newDate = meetingDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder=\"Город\"]").setValue("Сочи");// заполнить поле город
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);// очистить дату
        $("[placeholder=\"Дата встречи\"]").setValue(newDate);// заполнить поле дата
        $("[name = \"name\"]").setValue("Вера-Мира У");//заполнить ФИО
        $("[name = \"phone\"]").setValue("+71111111111");//заполнить телефон
        $("[data-test-id = \"agreement\"]").click();//нажать согласие
        $x("//*[text()=\"Забронировать\"]").click();//нажать забронировать
        $(byText("Доставка в выбранный город недоступна")).should(visible);
    }

    @Test
    public void shouldTownNull() {

        LocalDate meetingDate = LocalDate.now().plusDays(4);
        String newDate = meetingDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder=\"Город\"]").setValue("");// заполнить поле город
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);// очистить дату
        $("[placeholder=\"Дата встречи\"]").setValue(newDate);// заполнить поле дата
        $("[name = \"name\"]").setValue("Вера-Мира У");//заполнить ФИО
        $("[name = \"phone\"]").setValue("+71111111111");//заполнить телефон
        $("[data-test-id = \"agreement\"]").click();//нажать согласие
        $x("//*[text()=\"Забронировать\"]").click();//нажать забронировать
        $(byText("Поле обязательно для заполнения")).should(visible);

    }


    @Test
    public void shouldDateBefore3Days() {

        LocalDate meetingDate = LocalDate.now().plusDays(2);
        String newDate = meetingDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder=\"Город\"]").setValue("Краснодар");// заполнить поле город
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);// очистить дату
        $("[placeholder=\"Дата встречи\"]").setValue(newDate);// заполнить поле дата
        $("[name = \"name\"]").setValue("Вера-Мира У");//заполнить ФИО
        $("[name = \"phone\"]").setValue("+71111111111");//заполнить телефон
        $("[data-test-id = \"agreement\"]").click();//нажать согласие
        $x("//*[text()=\"Забронировать\"]").click();//нажать забронировать
        $(byText("Заказ на выбранную дату невозможен")).should(visible);

    }

    @Test
    public void shouldDateNull() {

        LocalDate meetingDate = LocalDate.now().plusDays(2);
        String newDate = meetingDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder=\"Город\"]").setValue("Краснодар");// заполнить поле город
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);// очистить дату
        $("[name = \"name\"]").setValue("Вера-Мира У");//заполнить ФИО
        $("[name = \"phone\"]").setValue("+71111111111");//заполнить телефон
        $("[data-test-id = \"agreement\"]").click();//нажать согласие
        $x("//*[text()=\"Забронировать\"]").click();//нажать забронировать
        $(byText("Неверно введена дата")).should(visible);

    }

    @Test
    public void shouldFIOIncorrect() {

        LocalDate meetingDate = LocalDate.now().plusDays(5);
        String newDate = meetingDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder=\"Город\"]").setValue("Краснодар");// заполнить поле город
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);// очистить дату
        $("[placeholder=\"Дата встречи\"]").setValue(newDate);// заполнить поле дата
        $("[name = \"name\"]").setValue("Вера-Mira У");//заполнить ФИО
        $("[name = \"phone\"]").setValue("+71111111111");//заполнить телефон
        $("[data-test-id = \"agreement\"]").click();//нажать согласие
        $x("//*[text()=\"Забронировать\"]").click();//нажать забронировать
        $(byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).should(visible);

    }

    @Test
    public void shouldFIONull() {

        LocalDate meetingDate = LocalDate.now().plusDays(5);
        String newDate = meetingDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder=\"Город\"]").setValue("Краснодар");// заполнить поле город
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);// очистить дату
        $("[placeholder=\"Дата встречи\"]").setValue(newDate);// заполнить поле дата
        $("[name = \"name\"]").setValue("");//заполнить ФИО
        $("[name = \"phone\"]").setValue("+71111111111");//заполнить телефон
        $("[data-test-id = \"agreement\"]").click();//нажать согласие
        $x("//*[text()=\"Забронировать\"]").click();//нажать забронировать
        $(byText("Поле обязательно для заполнения")).should(visible);

    }

    @Test
    public void shouldPhoneIncorrect() {

        LocalDate meetingDate = LocalDate.now().plusDays(5);
        String newDate = meetingDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder=\"Город\"]").setValue("Краснодар");// заполнить поле город
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);// очистить дату
        $("[placeholder=\"Дата встречи\"]").setValue(newDate);// заполнить поле дата
        $("[name = \"name\"]").setValue("Вера-Мира У");//заполнить ФИО
        $("[name = \"phone\"]").setValue("+7111111111");//заполнить телефон
        $("[data-test-id = \"agreement\"]").click();//нажать согласие
        $x("//*[text()=\"Забронировать\"]").click();//нажать забронировать
        $(byText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).should(visible);

    }

    @Test
    public void shouldPhoneNull() {

        LocalDate meetingDate = LocalDate.now().plusDays(5);
        String newDate = meetingDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder=\"Город\"]").setValue("Краснодар");// заполнить поле город
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);// очистить дату
        $("[placeholder=\"Дата встречи\"]").setValue(newDate);// заполнить поле дата
        $("[name = \"name\"]").setValue("Вера-Мира У");//заполнить ФИО
        $("[name = \"phone\"]").setValue("");//заполнить телефон
        $("[data-test-id = \"agreement\"]").click();//нажать согласие
        $x("//*[text()=\"Забронировать\"]").click();//нажать забронировать
        $(byText("Поле обязательно для заполнения")).should(visible);

    }

    @Test
    public void shouldNotCheckbox() {

        LocalDate meetingDate = LocalDate.now().plusDays(5);
        String newDate = meetingDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder=\"Город\"]").setValue("Краснодар");// заполнить поле город
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);// очистить дату
        $("[placeholder=\"Дата встречи\"]").setValue(newDate);// заполнить поле дата
        $("[name = \"name\"]").setValue("Вера-Мира У");//заполнить ФИО
        $("[name = \"phone\"]").setValue("+71111111111");//заполнить телефон
        $x("//*[text()=\"Забронировать\"]").click();//нажать забронировать
        $("checkbox_checked").should(hidden);
    }


}
