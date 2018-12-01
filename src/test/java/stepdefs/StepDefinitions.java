package stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import pages.StockPage;

public class StepDefinitions {

    private StockPage stockPage;

    public StepDefinitions(StockPage stockPage){
        this.stockPage = stockPage;
    }

    @Given("The browser is open")
    public void the_browser_is_open() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("I can navigate to Yahoo Finance")
    public void i_can_navigate_to_Yahoo_Finance() {
       stockPage.goToHomePage();
    }

    @Given("The URL is finance.yahoo.com")
    public void the_URL_is_finance_yahoo_com() {
        stockPage.getUrl();
    }

    @When("I enter a stock code \"([^\"]*)\" in the search field")
    public void i_enter_a_stock_code_in_the_search_field(String symbol) {
        stockPage.searchForStock(symbol);
    }

    @Then("I can be directed to the stock’s page")
    public void i_can_be_directed_to_the_stock_s_page() {
        stockPage.getUrl();
    }

    @Given("I am on the given stock’s page")
    public void i_am_on_the_given_stock_s_page() {
        stockPage.getUrl();
    }

    @Then("I can retrieve the current price")
    public void i_can_retrieve_the_current_price() {
        stockPage.getCurrentPrice();
    }

    @Given("I am on a given stock’s page")
    public void i_am_on_a_given_stock_s_page() {
        stockPage.getUrl();
    }

    @Then("I can retrieve the {int} week high and low")
    public void i_can_retrieve_the_week_high_and_low(Integer int1) {
       stockPage.getHighandLowForYear();
    }

    @Given("I have the current stock price and the {int} week high and low")
    public void i_have_the_current_stock_price_and_the_week_high_and_low(Integer int1) {
        stockPage.getHighandLowForYear();
    }

    @Then("I can calculate the current price relative to the {int} week high and low")
    public void i_can_calculate_the_current_price_relative_to_the_week_high_and_low(Integer int1) {
        stockPage.calculatePercentAboveLow();
        stockPage.calculatePercentBelowHigh();
    }


    @Given("I have the EPS of two stocks")
    public void i_have_the_EPS_of_two_stocks() {
        stockPage.getEarningsPerShare();
        stockPage.goToHomePage();
        stockPage.searchForStock();
        stockPage.getEarningsPerShare();
    }

    @Then("I can determine which company has a higher EPS")
    public void i_can_determine_which_company_has_a_higher_EPS() {
        //TODO: Finish
    }
}
