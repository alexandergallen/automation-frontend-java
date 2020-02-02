Feature: Git api testing
  Background:
    Given I access github api

  Scenario: Create a gist
    When I create a gist
    Then The gist was created
    And It contains correct information

  Scenario: Retrieve a gist
    When I retrieve a gist
    Then The correct gist is retrieved
    And It contains all fields specified in the documentation

  Scenario: Delete a gist
    Given I get gist to delete
    When I delete the gist
    Then The gist doesn't exist anymore