Feature: Visual Testing with Percy
  As a QA engineer
  I want to perform visual regression testing
  So that I can detect UI changes and visual bugs

  Background:
    Given Open Application
    And Percy visual testing is initialized

  @vt-1 @visual @smoke
  Scenario: Capture login screen visual baseline
    When I am on the login screen
    Then I capture visual screenshot "Login Screen - Baseline"

  @vt-2 @visual @regression
  Scenario: Visual test after login
    When I enter valid email and password
    And I tap the login button
    Then I should be redirected to the home page
    And I capture visual screenshot "Home Page - After Login"

  @vt-2 @visual @regression
  Scenario: Visual test with different screen sizes
    When I am on the login screen
    Then I capture visual screenshot "Login Screen - Mobile View" with minimum height 800

  @vt-2 @visual @regression
  Scenario: Visual comparison of error states
    When I enter email "invalid@email.com" and password "wrongpassword"
    And I tap the login button
    Then I capture visual screenshot "Login Screen - Error State"