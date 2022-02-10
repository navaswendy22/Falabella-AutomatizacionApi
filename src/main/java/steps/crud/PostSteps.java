package steps.crud;

import context.ScenarioRequest;
import context.ScenarioResponse;
import cucumber.api.java.en.When;
import steps.ScenarioHook;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostSteps extends CrudScenario {

    public PostSteps(ScenarioRequest scenarioRequest,
                     ScenarioResponse scenarioResponse,
                     ScenarioHook scenarioHook,
                     ResponseObservable responseObservable) {
        super(scenarioRequest, scenarioResponse, scenarioHook, responseObservable);
    }

    @When("a POST request is sent to endpoint {string} with body")
    public void postRequestMethod(String path, String requestBody) {
        scenarioResponse.setResponse(
                given()
                        .filters(filters)
                        .spec(scenarioRequest.getRequestSpecification())
                        .body(requestBody)
                        .when()
                        .post(path)
                        .then()
                        .log().ifError()
                        .extract()
                        .response());

        responseObservable.updateResponse(scenarioResponse.getResponse());
    }


    @When("a POST request is sent to endpoint {string} with query parameters")
    public void postWithQueryParametersMethod(String path,Map<String, String> queryParams) {
        Map<String, String> queryParamMap = new HashMap<>();
        queryParams.forEach(queryParamMap::put);
        scenarioRequest.addQueryParams(queryParamMap);
        scenarioResponse.setResponse(
                given()
                        .filters(filters)
                        .spec(scenarioRequest.getRequestSpecification())
                        .body("")
                        .when()
                        .post(path)
                        .then()
                        .log().ifError()
                        .extract()
                        .response());

        responseObservable.updateResponse(scenarioResponse.getResponse());
    }
}


