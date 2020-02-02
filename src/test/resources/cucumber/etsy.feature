Feature: Etsy search functionality
  Background:
    Given I have loaded "https://www.etsy.com/"

  Scenario: Sorting search results and validating cart
    When I search for "Sketchbook"
    And I sort the results by price ascending
    Then The items should be sorted accordingly
    And The most expensive item is added to the cart
    When I search for "turntable mat"
    And I add an item to the cart
    Then the cart should contain the correct items