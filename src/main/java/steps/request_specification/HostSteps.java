package steps.request_specification;

import context.ScenarioRequest;
import cucumber.api.java.en.Given;
import io.restassured.RestAssured;

public class HostSteps {

    private ScenarioRequest scenarioRequest;

    public HostSteps(ScenarioRequest scenarioRequest) {
        this.scenarioRequest = scenarioRequest;
    }

    //kubernetes cluster ip
    @Given("host {string}")
    public void setHost(String host) {
        if (host.startsWith("https")){
            RestAssured.useRelaxedHTTPSValidation();
        }
        scenarioRequest.setHost(host);
    }


    @Given("base path {string}")
    public void setBasePath(String basePath) {
        scenarioRequest.setBasePath(basePath);
    }

    @Given("port {int}")
    public void setPort(int port) {
        scenarioRequest.setPort(port);
    }
}