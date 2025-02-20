Feature: add objects

  Scenario: user can add an objects to storage
    Given I check list of all objects
    When I add an object
    Then I check the new object was added

  Scenario Outline: user can add some objects to storage
    Given I check list of all objects
    When I add some "<data>" to objects
    Then I check the new object was added

    Examples:
      | data |
      | object1   |
      | object2   |
