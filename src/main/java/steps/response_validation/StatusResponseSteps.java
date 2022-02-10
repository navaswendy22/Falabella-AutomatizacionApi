package steps.response_validation;

import context.ScenarioResponse;
import cucumber.api.java.en.Then;
import interfaces.CrudResponseValidatable;
import io.restassured.response.Response;
import steps.crud.ResponseObservable;

public class StatusResponseSteps implements CrudResponseValidatable {

    private Response response;

    public StatusResponseSteps(ScenarioResponse scenarioResponse, ResponseObservable responseObservable) {
        response = scenarioResponse
                .getResponse();

        responseObservable.addSubscriber(this);
    }

    @Then("status code should be {int}")
    public void statusCodeShouldBe(Integer expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
    }

    @Override
    public void updateResponse(Response response) {
        this.response = response;
    }
}