package steps.request_specification;

import context.ScenarioRequest;
import cucumber.api.java.en.Given;
import org.apache.http.params.CoreConnectionPNames;

import static io.restassured.config.HttpClientConfig.httpClientConfig;

public class HttpClientSteps {

    private ScenarioRequest scenarioRequest;

    public HttpClientSteps(ScenarioRequest scenarioRequest) {
        this.scenarioRequest = scenarioRequest;
    }

    @Given("a timeout of {int} milliseconds")
    public void setTimeoutOf(int milliseconds) {

        scenarioRequest.setConfig((config) -> config
                .httpClient(httpClientConfig()
                        .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, milliseconds)
                        .setParam(CoreConnectionPNames.SO_TIMEOUT, milliseconds)));
    }
}