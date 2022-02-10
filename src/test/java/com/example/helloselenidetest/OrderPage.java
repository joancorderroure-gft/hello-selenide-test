package com.example.helloselenidetest;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

// http://localhost:3000/#!/success
public class OrderPage {
    private SelenideElement alertDiv = $(".alert-danger");

    private SelenideElement confirmationMessage = $x("//p");

    public SelenideElement alert() {
        return alertDiv;
    }

    public SelenideElement confirmationMessage() {
        return confirmationMessage;
    }


}