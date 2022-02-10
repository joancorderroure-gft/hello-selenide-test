package com.example.helloselenidetest.cucumber;

import com.example.helloselenidetest.CartPage;
import com.example.helloselenidetest.CheckoutPage;
import com.example.helloselenidetest.OrderPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;

public class RobobarStepDefinitions {
    CartPage cartPage;
    CheckoutPage checkoutPage;
    OrderPage orderPage;

    @Given("user opens robobar website")
    public void userOpensRobobarWebsite() {
        open("http://localhost:3000");
        cartPage = new CartPage();
        checkoutPage = new CheckoutPage();
        orderPage = new OrderPage();
    }

    @When("user adds a cola")
    public void userAddsACola() {
        cartPage.addCola();

    }

    @Then("total should be €{double}")
    public void totalShouldBe€(Double arg0) {
        cartPage.total().shouldBe(text(arg0.toString()));

    }

    @Then("button order is disabled")
    public void buttonOrderIsDisabled() {
        cartPage.btnCheck().shouldBe(disabled);
    }

    @Then("button order is enabled")
    public void buttonOrderIsEnabled() {
        cartPage.btnCheck().shouldBe(enabled);
    }

    @When("user clicks submit")
    public void userClicksSubmit() {
        cartPage.checkout();
    }

    @And("user gives his age as {string}")
    public void userGivesHisAgeAs(String arg0) {
        checkoutPage.age(arg0);
    }

    @And("user confirms the order")
    public void userConfirmsTheOrder() {
        checkoutPage.confirm();
    }

    @Then("website alert msg should appear")
    public void websiteAlertMsgShouldAppear() {
        orderPage.alert().shouldBe(text("Prrt!"));
    }

    @Then("website confirmation msg should appear")
    public void websiteConfirmationMsgShouldAppear() {
        orderPage.confirmationMessage().shouldBe(text("Coming right up! ~bzzzt~"));
    }

    @Then("total should be {string}")
    public void totalShoudBe(String total) {
        cartPage.total().shouldBe(exactText(total));
    }

    @Then("total should be €{double}")
    public void totalShouldBeDouble(Double total) {
        cartPage.total().shouldBe(exactText(String.format("€%.2f", total)));
    }

    @When("user adds a beer")
    public void userAddsABeer() {
        cartPage.addBeer();
    }

    @When("user adds a wine")
    public void userAddsAWine() {
        cartPage.addWine();
    }

    @When("user checks out")
    public void userChecksOut() {
        checkoutPage = cartPage.checkout();
    }

    @When("user is {int} years old")
    public void userIsYearsOld(int age) {
        checkoutPage.age(String.valueOf(age));
    }

    @Then("robobar does not allow checkout")
    public void robobarDoesNotAllowCheckout() {
        orderPage = checkoutPage.confirm();
        orderPage.alert().shouldNotBe(hidden);
    }

    @Then("robobar confirms order")
    public void robobarConfirmsOrder() {
        orderPage = checkoutPage.confirm();
        orderPage.alert().shouldBe(hidden);
        orderPage.confirmationMessage().shouldBe(matchText("Coming right up"));
    }

    @When("user adds a cola and a beer")
    public void userAddsAColaAndABeer() {
        cartPage.addCola();
        cartPage.addBeer();
    }

    @When("user adds {int} cola")
    public void userAddsCola(int n) {
        for (int i = 0; i < n; ++i) {
            cartPage.addCola();
        }
    }

    @When("user adds {int} beers")
    public void userAddsNBeers(int n) {
        for (int i = 0; i < n; ++i) {
            cartPage.addBeer();
        }
    }

    @When("user adds {int} wines")
    public void userAddsNWines(int n) {
        for (int i = 0; i < n; ++i) {
            cartPage.addWine();
        }
    }

    @When("user adds {int} cola {int} beer {int} wine")
    public void userAddsColaBeerWine(int cola, int beer, int wine) {
        userAddsCola(cola);
        userAddsNBeers(beer);
        userAddsNWines(wine);
    }
}