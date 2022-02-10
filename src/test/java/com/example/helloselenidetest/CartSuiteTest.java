package com.example.helloselenidetest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CartSuiteTest {
    CartPage cartPage =  new CartPage();

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
    public void colaTest() {
        cartPage.btnCheck().shouldBe(disabled);
        cartPage.addCola();
        cartPage.btnCheck().shouldBe(enabled);
        cartPage.total().shouldBe(text("€1.25"));
        cartPage.addCola();
        cartPage.total().shouldBe(text("€2.50"));
    }

    @Test
    public void beerTest() {
        cartPage.btnCheck().shouldBe(disabled);
        cartPage.addBeer();
        cartPage.btnCheck().shouldBe(enabled);
        cartPage.total().shouldBe(text("€2.00"));
        cartPage.addBeer();
        cartPage.total().shouldBe(text("€4.00"));
    }

    @Test
    public void beerColaTest() {
        cartPage.btnCheck().shouldBe(disabled);
        cartPage.addBeer();
        cartPage.btnCheck().shouldBe(enabled);
        cartPage.total().shouldBe(text("€2.00"));
        cartPage.addCola();
        cartPage.total().shouldBe(text("€3.25"));
    }

    @Test
    public void wineTest() {
        cartPage.btnCheck().shouldBe(disabled);
        cartPage.addWine();
        cartPage.btnCheck().shouldBe(enabled);
        cartPage.total().shouldBe(text("€3.00"));
        cartPage.addWine();
        cartPage.total().shouldBe(text("€6.00"));
    }
}
