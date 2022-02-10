package steps.crud;

import context.ScenarioRequest;
import context.ScenarioResponse;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import steps.ScenarioHook;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetSteps extends CrudScenario {

    public GetSteps(ScenarioRequest scenarioRequest,
                    ScenarioResponse scenarioResponse,
                    ScenarioHook scenarioHook,
                    ResponseObservable responseObservable) {
        super(scenarioRequest, scenarioResponse, scenarioHook, responseObservable);
    }


    @When("a GET request is sent to endpoint {string} with query parameters")
    public void getRequestWithQueryParameters(String path, Map<String, String> queryParams) {
        Map<String, String> queryParamMap = new HashMap<>();
        queryParams.forEach(queryParamMap::put);
        scenarioRequest.addQueryParams(queryParamMap);
        scenarioResponse.setResponse(
                given()
                        .filters(filters)
                        .spec(scenarioRequest.getRequestSpecification())
                        .when()
                        .get(path)
                        .then()
                        .log().ifError()
                        .extract()
                        .response());

        responseObservable.updateResponse(scenarioResponse.getResponse());
    }

    @When("the IDs are recorded for search purpose")
    public void idRecorder() throws IOException {
        FileWriter writer = new FileWriter("src/main/resources/feature/loan_manager/id-recorder.txt");
        JsonPath js = new JsonPath(scenarioResponse.getResponse().asString());

        int resultSize = js.getInt("result.size()");
        for (int i = 0; i < resultSize; i++) {
            String id = js.get("result.id[" + i + "]").toString();
            writer.write(id);
            writer.write("\n");
        }
        writer.close();

    }

    @When("a GET request is sent to endpoint {string}")
    public void getRequestMethod(String path) {

        scenarioResponse.setResponse(
                given()
                        .filters(filters)
                        .spec(scenarioRequest.getRequestSpecification())
                        .when()
                        .get(path)
                        .then()
                        .log().ifError()
                        .extract()
                        .response());

        responseObservable.updateResponse(scenarioResponse.getResponse());
    }
}