package api.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class gitApi {
    private Response response;
    private String existingGistId;
    private String userName = System.getProperty("username");
    private String password = System.getProperty("password");
    private File postBody;
    private String gistId;

    @Given("I access github api")
    public void i_access_github_api() {
        // Specify the base URL to the RESTful web service
        RestAssured.baseURI = "https://api.github.com";
    }

    @When("I create a gist")
    public void i_create_a_gist() {
        // Retrieve POST body from json file
        postBody = new File("src/test/resources/postBody.json");
        response =
                given().
                        // Basic HTTP authentication is used.
                        // Github's api requires preemptive authorization.
                        auth().preemptive().basic(userName, password).with().
                        contentType("application/json").with().
                        body(postBody).
                when().
                        post("/gists").
                then().
                        statusCode(201).
                        extract().response();
        // gistID is saved for next step
        gistId = response.path("id").toString();
    }

    @Then("The gist was created")
    public void the_gist_was_created() {
        // Checks that gist with previously saved ID exists
        given().
                contentType("application/json").
        when().
                get("/gists/"+gistId).
        then().
                statusCode(200).
                body(("id"), equalTo(gistId));

    }

    @Then("It contains correct information")
    public void it_contains_correct_information() {
        // Checks that information of the created gist is equal to values provided via postBody.json and authorized user
        response.
                then().
                        body("$", hasEntry("public",true)).
                        body("$", hasEntry("description","API automation test file")).
                        body("$", hasKey("files")).
                        // Checks that files exist
                        body("files", hasKey("test.feature")).
                        body("files", hasKey("test.txt")).
                        // Checks that files have correct name and content
                        body("files.'test.feature'", hasEntry("filename","test.feature")).
                        body("files.'test.feature'", hasEntry("content","//Empty feature file")).
                        body("files.'test.txt'", hasEntry("filename","test.txt")).
                        body("files.'test.txt'", hasEntry("content","Testing testing 123 123")).
                        // Checks that owner equals authorized user
                        body("owner", hasEntry("login",userName));
    }

    @When("I retrieve a gist")
    public void i_retrieve_a_gist() {
        // Already existing public gist owned by "alexandergallen"
        existingGistId =  "cd3dc2f6f888c5e9d51a8731363a4db6";
        response =
                given().
                        contentType("application/json").
                when().
                        get("/gists/"+existingGistId).
                then().
                        statusCode(200).
                        extract().response();
    }

    @Then("The correct gist is retrieved")
    public void the_correct_gist_is_retrieved() {
        response.
                then().
                        body(("id"), equalTo(existingGistId));
    }

    @Then("It contains all fields specified in the documentation")
    public void it_contains_all_fields_specified_in_the_documentation() {
        // Checks that all required fields are present and populated.
        // I created a JsonSchema file using Json-schema-generator: https://www.npmjs.com/package/json-schema-generator
        // The schema was created based on the example response on: https://developer.github.com/v3/gists/#get-a-single-gist
        // I cross-referenced it with a response from a minimalistic gist I created to see which values were
        // required and which were optional. This allowed me to set realistic required/minValue tags in the schema
        // Schema file "git_api_gist.json" can be found in this projects classpath (src/test/java/resources)
        response.
                then().
                        body(matchesJsonSchemaInClasspath("git_api_gist.json"));
    }

    @Given("I get gist to delete")
    public void i_get_gist_to_delete() {
        // Retrieve ID of most recently created gist of the user, i.e. the one created in the previous scenario.
        // This is required to re-do as part of this scenario since variable states aren't stored between scenarios.
        // This keeps the scenarios independent.
        response =
                given().
                        auth().preemptive().basic(userName, password).with().
                        contentType("application/json").
                when().
                        get("/gists").
                then().
                        statusCode(200).
                        extract().response();
        // Save id for use in next step.
        gistId = response.path("[0].id").toString();
    }

    @When("I delete the gist")
    public void i_delete_the_gist() {
        // Delete gist and check response code.
        response =
                given().
                        auth().preemptive().basic(userName, password).with().
                        contentType("application/json").
                when().
                        delete("/gists/"+gistId).
                then().
                        statusCode(204).
                        extract().response();
    }

    @Then("The gist doesn't exist anymore")
    public void the_gist_doesn_t_exist_anymore() {
        // Check that trying to get previously deleted gist returns page not found.
                given().
                        contentType("application/json").
                when().
                        get("/gists/"+gistId).
                then().
                        statusCode(404);
    }
}
