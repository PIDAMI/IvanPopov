Feature: User Table page's table checkboxes log displayed
  Scenario: User Table checkboxes checked
    Given I open JDI GitHub site
    And I login as user "Roman Iovlev"
    And I click on "Service" button in Header
    And I click on "User Table" button in Service dropdown
    When I select 'Vip' checkbox for "Sergey Ivan"
    Then 1 log row has "Vip: condition changed to true" text in log section