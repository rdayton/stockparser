Feature: Stock Price Retrieval
  Scenario: Navigating to the application
    Given The browser is open
    Then I can navigate to Yahoo Finance

  Scenario: Searching for a stock
    Given The URL is finance.yahoo.com
    When I enter a stock code in the search field
    Then I can be directed to the stock’s page

  Scenario: Retrieving the current stock price
    Given I am on the given stock’s page
    Then I can retrieve the current price

  Scenario: Retrieving the 52 week high and low stock price
    Given I am on a given stock’s page
    Then I can retrieve the 52 week high and low

  Scenario: Calculating current price relative to 52 week high and low
    Given I have the current stock price and the 52 week high and low
    Then I can calculate the current price relative to the 52 week high and low

  Scenario: Calculate earnings per share
    Given I have the current stock price and number of shares
    Then I can calculate the earnings per share (EPS)

  Scenario: Compare EPS of two stocks
    Given I have the EPS of two stocks
    Then I can determine which company has a higher EPS
