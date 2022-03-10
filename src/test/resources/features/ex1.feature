Feature: Selectable elements on Different Elements page

  Scenario: Click selectables
    Given I open JDI GitHub site
    And I login as user 'Roman Iovlev'
    And I go to Different Elements page
    When I select elements checkboxes with values Water, Wind
    And I select radio button with value 'Selen'
    And I select color option with value 'Yellow'
    Then Elements checkboxes log is displayed and its value corresponds to Water, Wind
    And Radio buttons log is displayed and its value corresponds to 'Selen'
    And Color option log is displayed and its value corresponds to 'Yellow'
