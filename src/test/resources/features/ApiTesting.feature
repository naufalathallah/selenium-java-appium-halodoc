Feature: API Testing with RestAssured
  As a QA engineer
  I want to test APIs using RestAssured
  So that I can validate backend functionality

  @api @smoke
  Scenario: Get all users from API
    When I send GET request to "/users" endpoint
    Then the response status code should be 200
    And the response should contain users list

  @api @regression
  Scenario: Get specific user by ID
    When I send GET request to "/users/1" endpoint
    Then the response status code should be 200
    And the response should contain user with id 1
    And the user should have "name" field
    And the user should have "email" field

  @api @regression
  Scenario: Create new user via API
    When I create a new user with name "Test User" email "test@example.com" and username "testuser"
    Then the response status code should be 201
    And the response should contain created user data

  @api @regression
  Scenario: Update existing user
    When I update user with id 1 with name "Updated User" and email "updated@example.com"
    Then the response status code should be 200
    And the response should contain updated user data

  @api @regression
  Scenario: Delete user
    When I delete user with id 1
    Then the response status code should be 200