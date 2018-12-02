package stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.StockPage;


public class StepDefinitions {

    @DataProvider(name = "Symbols")

    public static Object[][] credentials() {
        return new Object[][] { { "AAPL", "TSLA" }, { "GOOG", "AMZN" }, {"COF", "BAC"}};
    }

    private StockPage initialStockPage;
    private StockPage comparisonPage;
    private Double initialEps;
    private Double comparisonEps;

    @Test(dataProvider = "Symbols")
    public StepDefinitions(){
        this.initialStockPage = new StockPage("AAPL");
        this.comparisonPage = new StockPage("TSLA");
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
    }

    @Given("I am on a given stock’s page")
    public void i_am_on_a_given_stock_s_page() {
        initialStockPage.getUrl();
    }

    @Then("get the 52 week high and low")
    public void i_can_retrieve_the_week_high_and_low() {
        initialStockPage.setHighAndLowForYear();
        System.out.println("The 52 week high is "+ initialStockPage.getHigh());
        System.out.println("The 52 week low is "+ initialStockPage.getLow());
    }


    @Then("I can calculate the current price relative to the 52 week high and low")
    public void i_can_calculate_the_current_price_relative_to_the_52_week_high_and_low() {
        Integer pricePercentageAboveLow = initialStockPage.calculatePercentAboveLow();
        Integer pricePercentageBelowHigh = initialStockPage.calculatePercentBelowHigh();
        System.out.println("Today’s price of "+ initialStockPage.getCurrentPrice()
                +" is "+ pricePercentageBelowHigh + "% lower than the 52 week high and "
                + pricePercentageAboveLow + "% higher than the 52 week low.");
    }


    @Given("I have the EPS of two stocks")
    public void i_have_the_EPS_of_two_stocks() {
        initialEps = initialStockPage.setEarningsPerShare();
        comparisonPage.goToHomePage();
        comparisonPage.searchForStock();
        comparisonEps = comparisonPage.setEarningsPerShare();
        System.out.println(initialStockPage.getSymbol() + " has an EPS of " + initialEps +".");
        System.out.println(comparisonPage.getSymbol() + " has an EPS of " + comparisonEps +".");

    }

    @Then("I can determine which company has a higher EPS")
    public void i_can_determine_which_company_has_a_higher_EPS() {
        if(initialEps > comparisonEps){
            System.out.println(initialStockPage.getSymbol() + " has higher EPS");
        } else if (initialEps < comparisonEps){
            System.out.println(comparisonPage.getSymbol() + " has higher EPS");
        } else {
            System.out.println(initialStockPage.getSymbol() + " and "+ comparisonPage.getSymbol() + " have equal EPS");
        }
    }
}
