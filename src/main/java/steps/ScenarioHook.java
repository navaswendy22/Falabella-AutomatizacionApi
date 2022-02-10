package steps;

import cucumber.api.Scenario;
import cucumber.api.java.Before;

public class ScenarioHook {
    private Scenario scenario;

    @Before
    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public Scenario getScenario() {
        return scenario;
    }
}