@DemoQa @SelfHealing
Feature: Cross-Application AI Dynamic Self-Healing Verification across All Playwright Locator Strategies

  Background:
    Given I open the DemoQA Text Box page
    When the AI pre-flight agent validates the broken DemoQA page elements

  @CSS
  Scenario: AI Self-Healing recovers broken CSS ID and Class locators
    When I enter "CSS Healed User" into the broken CSS user name field
    And I click the broken CSS submit button
    Then the broken CSS user name field should be marked as healed with confidence >= 60%
    And the AI element healing JSON report should be generated in the demoqa project

  @XPath
  Scenario: AI Self-Healing recovers broken XPath attribute and text locators
    When I enter "xpath.user@testmail.com" into the broken XPath email field
    And I click the broken XPath submit button
    Then the broken XPath email field should be marked as healed with confidence >= 60%
    And the AI element healing JSON report should be generated in the demoqa project

  @TextEngine
  Scenario: AI Self-Healing recovers broken Playwright Text engine locators
    When I enter "Text Engine User" into the broken CSS user name field
    And I click the broken Text Engine submit button
    Then the broken Text Engine submit button should be marked as healed with confidence >= 60%
    And the AI element healing JSON report should be generated in the demoqa project

  @RoleEngine
  Scenario: AI Self-Healing recovers broken Playwright Role engine locators
    When I enter "742 Evergreen Terrace" into the broken Role current address field
    And I click the broken Role submit button
    Then the broken Role current address field should be marked as healed with confidence >= 60%
    And the AI element healing JSON report should be generated in the demoqa project

  @PlaceholderAndLabel
  Scenario: AI Self-Healing recovers broken Playwright Placeholder and Label engine locators
    When I enter "placeholder.user@testmail.com" into the broken Placeholder email field
    And I enter "Label Healed User" into the broken Label user name field
    And I click the broken CSS submit button
    Then the broken Placeholder email field should be marked as healed with confidence >= 60%
    And the broken Label user name field should be marked as healed with confidence >= 60%
    And the AI element healing JSON report should be generated in the demoqa project

  @ChainedAndPseudo
  Scenario: AI Self-Healing recovers broken Chained combinators and Pseudo-class locators
    When I enter "Chained Healed User" into the broken Chained user name field
    And I click the broken Chained submit button
    Then the broken Chained user name field should be marked as healed with confidence >= 60%
    And the broken Chained submit button should be marked as healed with confidence >= 60%
    And the AI element healing JSON report should be generated in the demoqa project

