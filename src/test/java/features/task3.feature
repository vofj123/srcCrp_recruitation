@srcCrp
Feature: Anaplan Task 3

  Scenario: Task 3
    Given I am on the website with URL: https://us1a.app.anaplan.com/
    And I log in with following credentials:
      | username | selenium_recrutation@source-corp.com |
      | password | D.Legowski_20200229_1                |
    And I clicked on the model "Supply Chain - For tests"
    And I am at "Detailed Demand Review" view
#    Not enough time to implement next steps, I will describe of I would do with notes inside the steps
    When I change filter to "H1 FY20"
    And I select 'Override?' checkbox in "Week 14 FY20"
    And I put value into corresponding Override Forecast