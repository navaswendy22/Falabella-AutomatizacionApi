package steps.request_specification;

import context.ScenarioRequest;
import cucumber.api.java.en.Given;
import io.restassured.http.Header;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HeaderSteps {

    private ScenarioRequest scenarioRequest;

    public HeaderSteps(ScenarioRequest scenarioRequest) {
        this.scenarioRequest = scenarioRequest;
    }

    @Given("with headers")
    public void setHeaders(Map<String, String> headers) {
        List<Header> headerList = new ArrayList<>();
        headers.forEach( (name, value) -> headerList.add(new Header(name, value)));
        scenarioRequest.addHeaders(headerList);
    }
}