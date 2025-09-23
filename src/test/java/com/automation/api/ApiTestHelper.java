package com.automation.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiTestHelper {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    static {
        RestAssured.baseURI = BASE_URL;
    }

    public static Response getUsers() {
        return RestAssured
                .given()
                .header("Content-Type", "application/json")
                .when()
                .get("/users")
                .then()
                .extract().response();
    }

    public static Response getUserById(int userId) {
        return RestAssured
                .given()
                .header("Content-Type", "application/json")
                .pathParam("id", userId)
                .when()
                .get("/users/{id}")
                .then()
                .extract().response();
    }

    public static Response createUser(String name, String email, String username) {
        String requestBody = String.format(
            "{ \"name\": \"%s\", \"email\": \"%s\", \"username\": \"%s\" }",
            name, email, username
        );

        return RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .extract().response();
    }

    public static Response updateUser(int userId, String name, String email) {
        String requestBody = String.format(
            "{ \"id\": %d, \"name\": \"%s\", \"email\": \"%s\" }",
            userId, name, email
        );

        return RestAssured
                .given()
                .header("Content-Type", "application/json")
                .pathParam("id", userId)
                .body(requestBody)
                .when()
                .put("/users/{id}")
                .then()
                .extract().response();
    }

    public static Response deleteUser(int userId) {
        return RestAssured
                .given()
                .pathParam("id", userId)
                .when()
                .delete("/users/{id}")
                .then()
                .extract().response();
    }
}