Feature: Stock Price Parsing
  Scenario Outline: Comparing two stocks
    Given I have stocks "<initial>" and "<compare>" I want to compare
    And The browser is open
    Then I can navigate to Yahoo Finance
    And search for a stock
    When I am on a given stock’s page
    Then I can retrieve the current price
    And get the 52 week high and low
    Then I can calculate the current price relative to the 52 week high and low
    When I have the EPS of two stocks
    Then I can determine which company has a higher EPS

    Examples:
    | initial   | compare   |
    | GOOG      | NFLX      |
    | AAPL      | TSLA      |
    | COF       | BAC      |