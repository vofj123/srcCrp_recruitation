@google
Feature: Demo on google

  Scenario: Demo search
    Given I am on the website with URL: https://www.google.com/
    When I search for: anaplan
    Then I see results for: anaplan.com