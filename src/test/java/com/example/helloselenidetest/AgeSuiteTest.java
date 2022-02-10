package com.example.helloselenidetest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;

public class AgeSuiteTest {
    CartPage cartPage =  new CartPage();
    CheckoutPage checkoutPage =  new CheckoutPage();
    OrderPage orderPage =  new OrderPage();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        open("http://localhost:3000/");
    }

    @Test
    public void TestCola() {
        cartPage.btnCheck().shouldBe(disabled);
        cartPage.addCola();
        cartPage.btnCheck().shouldBe(enabled);
        cartPage.total().shouldBe(text("€1.25"));
        cartPage.addCola();
        cartPage.total().shouldBe(text("€2.50"));
        cartPage.checkout();
        checkoutPage.confirm();
        orderPage.confirmationMessage().shouldBe(text("Coming right up! ~bzzzt~"));
    }

    @Test
    public void underAgeWineTest() {
        cartPage.btnCheck().shouldBe(disabled);
        cartPage.addWine();
        cartPage.btnCheck().shouldBe(enabled);
        cartPage.total().shouldBe(text("€3.00"));
        cartPage.addWine();
        cartPage.total().shouldBe(text("€6.00"));
        cartPage.checkout();
        checkoutPage.age("17");
        checkoutPage.confirm();
        orderPage.alert().shouldBe(text("Prrt!"));

    }

    @Test
    public void adultAgeWineTest() {
        cartPage.btnCheck().shouldBe(disabled);
        cartPage.addWine();
        cartPage.btnCheck().shouldBe(enabled);
        cartPage.total().shouldBe(text("€3.00"));
        cartPage.addWine();
        cartPage.total().shouldBe(text("€6.00"));
        cartPage.checkout();
        checkoutPage.age("19");
        checkoutPage.confirm();
        orderPage.confirmationMessage().shouldBe(text("Coming right up! ~bzzzt~"));
    }

    @Test
    public void underAgeBeerTest() {
        cartPage.btnCheck().shouldBe(disabled);
        cartPage.addBeer();
        cartPage.btnCheck().shouldBe(enabled);
        cartPage.total().shouldBe(text("€2.00"));
        cartPage.addBeer();
        cartPage.total().shouldBe(text("€4.00"));
        cartPage.checkout();
        checkoutPage.age("17");
        checkoutPage.confirm();
        orderPage.alert().shouldBe(text("Prrt!"));
    }

    @Test
    public void adultAgeBeerTest() {
        cartPage.btnCheck().shouldBe(disabled);
        cartPage.addBeer();
        cartPage.btnCheck().shouldBe(enabled);
        cartPage.total().shouldBe(text("€2.00"));
        cartPage.addBeer();
        cartPage.total().shouldBe(text("€4.00"));
        cartPage.checkout();
        checkoutPage.age("19");
        checkoutPage.confirm();
        orderPage.confirmationMessage().shouldBe(text("Coming right up! ~bzzzt~"));
    }


}
