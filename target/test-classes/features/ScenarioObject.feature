Feature: add objects

  Scenario: user can add an objects to storage
    Given I check list objects are available in storage
    When I add an object
    Then I see object was added

  Scenario Outline: user can add some objects to storage
    Given I check list objects are available in storage
    When I add "<payload>" to object
    Then I see the object was added

    Examples:
      | payload |
      | data1   |
      | data2   |
