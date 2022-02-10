package steps.response_validation;

import context.ScenarioResponse;
import cucumber.api.java.en.Then;
import interfaces.CrudResponseValidatable;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.response.ValidatableResponseOptions;
import steps.crud.ResponseObservable;


import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasEntry;

public class JsonResponseSteps implements CrudResponseValidatable {

    private ValidatableResponseOptions<ValidatableResponse, Response> validatableJsonResponse;

    public JsonResponseSteps(ScenarioResponse scenarioResponse, ResponseObservable responseObservable) {
        validatableJsonResponse = scenarioResponse
                .getResponse()
                .then()
                .using()
                .parser("text/plain", Parser.JSON);

        responseObservable.addSubscriber(this);
    }

    @Then("response content type should be json")
    public void responseShouldBeJson() {
        validatableJsonResponse.assertThat().contentType(ContentType.JSON);
    }

    @Then("json response should contain key {string} with value number {int}")
    public void responseShouldContaintKeyValueInt(String key, int value) {
        validatableJsonResponse.assertThat().body(key, equalTo(value));
    }

    @Then("json response should contain key {string} with value string {string}")
    public void responseShouldContaintKeyValueString(String key, String value) {
        validatableJsonResponse.assertThat().body(key, equalTo(value));
    }

    @Then("json response should contain key {string} with value boolean {string}")
    public void responseShouldContainKeyValueBoolean(String key, String value) {
        validatableJsonResponse.assertThat().body(key, equalTo(Boolean.valueOf(value)));
    }

    @Then("json response key {string} should have size {int}")
    public void responseKeyShouldHaveSize(String key, int size) {
        validatableJsonResponse.assertThat().body(key + ".size()", equalTo(size));
    }

    @Then("json response element {string} should contain entry with key {string} and value number {int}")
    public void responsePropShouldContainEntryWithInt(String property, String key, int value) {
        validatableJsonResponse.assertThat().body(property, hasEntry(key, value));
    }

    @Then("json response element {string} should contain entry with key {string} and value string {string}")
    public void responsePropShouldContainEntryWithString(String property, String key, String value) {
        validatableJsonResponse.assertThat().body(property, hasEntry(key, value));
    }

    @Then("json response array {string} should contain entry with key {string} and value string {string}")
    public void responsePropShouldContainResponseArray(String property, String key, String value) {
        validatableJsonResponse.assertThat().body(property, contains(hasEntry(key, value)));
    }

    @Then("json response array should containt at least {int} elements")
    public void responseArrayShouldContainAtLeast(int value) {
        validatableJsonResponse.assertThat().body("size()", greaterThanOrEqualTo(value));
    }

    @Then("json response array should contain {int} element(s)")
    public void responseShouldContainXElements(int size) {
        validatableJsonResponse.assertThat().body("size()", equalTo(size));
    }

    @Then("json response array element {string} should contain items {string}")
    public void responseElementHasItems(String query, String items) {
        validatableJsonResponse.assertThat().body(query, hasItems(items));
    }

    @Then("json response array element {string} should contain at least {int} items")
    public void responseElementHasNItems(String query, int value) {
        validatableJsonResponse.assertThat().body(query + ".size()", greaterThanOrEqualTo(value));
    }

    @Then("json response array element {string} should contain venues category {string}")
    public void responseElementContainVenues(String query, String value) {
        List<String[]> venues_type =new ArrayList<>();
        venues_type.add(value.split(","));
        for (int n_venues = 0; n_venues < venues_type.size(); n_venues ++){
            validatableJsonResponse.assertThat().body(query, contains(venues_type.get(n_venues)));
        }
    }

    @Then("json response query {string} should return boolean {string}")
    public void responseQueryReturns(String query, String value) {
        validatableJsonResponse.assertThat().body(query, equalTo(Boolean.valueOf(value)));
    }

    @Override
    public void updateResponse(Response response) {
        this.validatableJsonResponse = response
                .then()
                .using()
                .parser("text/plain", Parser.JSON);
    }

}