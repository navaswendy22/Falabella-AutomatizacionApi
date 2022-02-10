package steps.crud;

import context.ScenarioRequest;
import context.ScenarioResponse;
import io.restassured.filter.Filter;
import steps.ScenarioHook;
import utilities.filters.RequestLoggingScenarioFilter;
import utilities.filters.ResponseLoggingScenarioFilter;

import java.util.Arrays;
import java.util.List;

abstract class CrudScenario {
    protected ScenarioRequest scenarioRequest;
    ScenarioResponse scenarioResponse;
    List<Filter> filters;
    ResponseObservable responseObservable;

    CrudScenario(ScenarioRequest scenarioRequest,
                 ScenarioResponse scenarioResponse,
                 ScenarioHook scenarioHook,
                 ResponseObservable responseObservable) {
        this.scenarioRequest = scenarioRequest;
        this.scenarioResponse = scenarioResponse;
        this.responseObservable = responseObservable;
        this.filters = Arrays.asList(new RequestLoggingScenarioFilter(scenarioHook.getScenario()),
                new ResponseLoggingScenarioFilter(scenarioHook.getScenario()));
    }
}