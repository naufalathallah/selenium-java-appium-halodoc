package com.automation.stepdefinitions;

import com.automation.api.ApiTestHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

public class ApiStepDefinitions {

    private Response response;

    @When("I send GET request to {string} endpoint")
    public void iSendGETRequestToEndpoint(String endpoint) {
        if (endpoint.equals("/users")) {
            response = ApiTestHelper.getUsers();
        } else if (endpoint.equals("/users/1")) {
            response = ApiTestHelper.getUserById(1);
        }
    }

    @When("I create a new user with name {string} email {string} and username {string}")
    public void iCreateANewUserWithNameEmailAndUsername(String name, String email, String username) {
        response = ApiTestHelper.createUser(name, email, username);
    }

    @When("I update user with id {int} with name {string} and email {string}")
    public void iUpdateUserWithIdWithNameAndEmail(int userId, String name, String email) {
        response = ApiTestHelper.updateUser(userId, name, email);
    }

    @When("I delete user with id {int}")
    public void iDeleteUserWithId(int userId) {
        response = ApiTestHelper.deleteUser(userId);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode,
            "Expected status code " + expectedStatusCode + " but got " + response.getStatusCode());
    }

    @And("the response should contain users list")
    public void theResponseShouldContainUsersList() {
        Assert.assertTrue(response.jsonPath().getList("$").size() > 0,
            "Response should contain at least one user");
        Assert.assertNotNull(response.jsonPath().get("[0].id"),
            "First user should have an ID");
    }

    @And("the response should contain user with id {int}")
    public void theResponseShouldContainUserWithId(int userId) {
        Assert.assertEquals(response.jsonPath().getInt("id"), userId,
            "Response should contain user with ID " + userId);
    }

    @And("the user should have {string} field")
    public void theUserShouldHaveField(String fieldName) {
        Assert.assertNotNull(response.jsonPath().get(fieldName),
            "User should have " + fieldName + " field");
        Assert.assertFalse(response.jsonPath().getString(fieldName).isEmpty(),
            fieldName + " field should not be empty");
    }

    @And("the response should contain created user data")
    public void theResponseShouldContainCreatedUserData() {
        Assert.assertNotNull(response.jsonPath().get("id"),
            "Created user should have an ID");
        Assert.assertNotNull(response.jsonPath().get("name"),
            "Created user should have a name");
        Assert.assertNotNull(response.jsonPath().get("email"),
            "Created user should have an email");
    }

    @And("the response should contain updated user data")
    public void theResponseShouldContainUpdatedUserData() {
        Assert.assertEquals(response.jsonPath().getInt("id"), 1,
            "Updated user should have ID 1");
        Assert.assertEquals(response.jsonPath().getString("name"), "Updated User",
            "User name should be updated");
        Assert.assertEquals(response.jsonPath().getString("email"), "updated@example.com",
            "User email should be updated");
    }
}