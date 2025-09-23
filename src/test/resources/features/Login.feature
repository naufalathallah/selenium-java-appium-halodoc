Feature: User Login
  As a user, I want to be able to login with valid credentials
  So that I can access the application features

  Background:
    Given Open Application

  @tc-1 @smoke @regression
  Scenario: Successful login with valid email and password
    When I enter valid email and password
    And I tap the login button
    Then I should be redirected to the home page

  @tc-2 @regression
  Scenario Outline: Login with different credentials
    When I enter email "<email>" and password "<password>"
    And I tap the login button
    Then I should see "<result>"

    Examples:
      | email               | password         | result                |
      | api1@yopmail.com    | Tdautomate123!   | homepage              |
      | api1@yopmail.com    | invalidpass      | validation error      |
      | empty               | Tdautomate123!   | validation error      |
      | api1@yopmail.com    | empty            | validation error      |