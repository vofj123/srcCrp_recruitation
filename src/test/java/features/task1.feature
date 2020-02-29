@srcCrp
Feature: Anaplan Task 1

  Scenario: Task 1
    Given I am on the website with URL: https://us1a.app.anaplan.com/
    When I log in with following credentials:
      | username | selenium_recrutation@source-corp.com |
      | password | D.Legowski_20200229_1                |
    Then I can see "Supply Chain - For tests" model displayed

