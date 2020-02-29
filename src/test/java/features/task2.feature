@srcCrp
Feature: Anaplan Task 2

  Scenario: Task 2
    Given I am on the website with URL: https://us1a.app.anaplan.com/
    And I log in with following credentials:
      | username | selenium_recrutation@source-corp.com |
      | password | D.Legowski_20200229_1                |
    And I clicked on the model "Supply Chain - For tests"
    When I open Contents section if closed
#    I dislike hardcoding below step, it should be parametrized, depending on system behaviour (dont know if these sections are constants or can be changed frequently)
#    However for lack of knowledge on the system and more importantly lack of time I hardcoded choosing this section
    And I open Detailed Demand Review
    Then I verify the title is "Detailed Demand Review"