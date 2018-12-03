package stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.testng.annotations.Test;
import org.testng.Reporter;

import pages.StockPage;

public class StepDefinitions {

    private StockPage initialStockPage;
    private StockPage comparisonPage;
    private Double initialEps;
    private Double comparisonEps;


    @Test(dataProvider = "scenarios")
    @Given("I have stocks \"(.*)\" and \"(.*)\" I want to compare")
    public void i_have_stocks_and_I_want_to_compare(String initial, String compare) {
        this.initialStockPage = new StockPage(initial);
        this.comparisonPage = new StockPage(compare);
        Reporter.log("========= Comparing "+ initial + " and "+ compare + " =========<br/>");
    }


    @Given("The browser is open")
    public void the_browser_is_open() {
        initialStockPage.openBrowser();
    }

    @Then("I can navigate to Yahoo Finance")
    public void i_can_navigate_to_Yahoo_Finance() {
        initialStockPage.goToHomePage();
    }


    @And("search for a stock")
    public void search_for_a_stock() {
        initialStockPage.searchForStock();
    }


    @Then("I can retrieve the current price")
    public void i_can_retrieve_the_current_price() {
        initialStockPage.setCurrentPrice();
        Reporter.log(initialStockPage.getSymbol() + "'s current price is "+ initialStockPage.getCurrentPrice()+"<br/>");
    }

    @When("I am on a given stock’s page")
    public void i_am_on_a_given_stock_s_page() {
        initialStockPage.getUrl();
    }

    @And("get the 52 week high and low")
    public void i_can_retrieve_the_week_high_and_low() {
        initialStockPage.setHighAndLowForYear();
        Reporter.log("The 52 week high is "+ initialStockPage.getHigh()+"<br/>");
        Reporter.log("The 52 week low is "+ initialStockPage.getLow()+"<br/>");
    }


    @Then("I can calculate the current price relative to the 52 week high and low")
    public void i_can_calculate_the_current_price_relative_to_the_52_week_high_and_low() {
        Integer pricePercentageAboveLow = initialStockPage.calculatePercentAboveLow();
        Integer pricePercentageBelowHigh = initialStockPage.calculatePercentBelowHigh();
        Reporter.log("Today’s price of "+ initialStockPage.getCurrentPrice()
                +" is "+ pricePercentageBelowHigh + "% lower than the 52 week high and "
                + pricePercentageAboveLow + "% higher than the 52 week low.<br/>");
    }


    @When("I have the EPS of two stocks")
    public void i_have_the_EPS_of_two_stocks() {
        initialEps = initialStockPage.setEarningsPerShare();
        comparisonPage.goToHomePage();
        comparisonPage.searchForStock();
        comparisonEps = comparisonPage.setEarningsPerShare();
        Reporter.log(initialStockPage.getSymbol() + " has an EPS of " + initialEps +".<br/>");
        Reporter.log(comparisonPage.getSymbol() + " has an EPS of " + comparisonEps +".<br/>");

    }

    @Then("I can determine which company has a higher EPS")
    public void i_can_determine_which_company_has_a_higher_EPS() {
        if(initialEps > comparisonEps){
            Reporter.log(initialStockPage.getSymbol() + " has higher EPS<br/>");
        } else if (initialEps < comparisonEps){
            Reporter.log(comparisonPage.getSymbol() + " has higher EPS<br/>");
        } else {
            Reporter.log(initialStockPage.getSymbol() + " and "+ comparisonPage.getSymbol() + " have equal EPS<br/>");
        }
    }
}
