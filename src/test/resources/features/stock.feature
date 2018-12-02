Feature: Stock Price Retrieval
  Scenario: Navigating to the application
    Given The browser is open
    Then I can navigate to Yahoo Finance
    And search for a stock

  Scenario: Retrieving the current stock price
    Given I am on a given stock’s page
    Then I can retrieve the current price
    And get the 52 week high and low
    Then I can calculate the current price relative to the 52 week high and low

  Scenario: Compare EPS of two stocks
    Given I have the EPS of two stocks
    Then I can determine which company has a higher EPS
