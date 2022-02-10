package com.example.helloselenidetest;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

// http://localhost:3000/#!/review
public class CheckoutPage {

    public SelenideElement btnConfirm = $(".btn-success");

    public SelenideElement ageInput = $("#ageInput" );


    public OrderPage confirm() {
        btnConfirm.click();
        return page(OrderPage.class);
    }

    public void age(String value){
        ageInput.sendKeys(value);
    }




}