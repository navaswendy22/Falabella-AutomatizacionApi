package steps.crud;

import context.ScenarioRequest;
import context.ScenarioResponse;
import cucumber.api.java.en.When;
import steps.ScenarioHook;

import static io.restassured.RestAssured.given;

public class PutSteps extends CrudScenario {

    public PutSteps(ScenarioRequest scenarioRequest,
                    ScenarioResponse scenarioResponse,
                    ScenarioHook scenarioHook,
                    ResponseObservable responseObservable) {
        super(scenarioRequest, scenarioResponse, scenarioHook, responseObservable);
    }

    @When("a PUT request is sent to endpoint {string}")
    public void putRequestMethod(String path) {
        scenarioResponse.setResponse(
                given()
                        .filters(filters)
                        .spec(scenarioRequest.getRequestSpecification())
                        .when()
                        .put(path)
                        .then()
                        .log().ifError()
                        .extract()
                        .response());

        responseObservable.updateResponse(scenarioResponse.getResponse());
    }

    @When("a PUT request is sent to endpoint {string} with body")
    public void putRequestMethodWithBody(String path, String requestBody) {
        scenarioResponse.setResponse(
                given()
                        .filters(filters)
                        .spec(scenarioRequest.getRequestSpecification())
                        .body(requestBody)
                        .when()
                        .put(path)
                        .then()
                        .log().ifError()
                        .extract()
                        .response());

        responseObservable.updateResponse(scenarioResponse.getResponse());
    }
}