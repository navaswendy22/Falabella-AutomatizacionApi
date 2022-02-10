package steps;

import cucumber.api.java.en.Given;
import java.util.concurrent.TimeUnit;

public class GenericSteps {
    @Given("there is a wait for {int} seconds")
    public void waitTimeInSeconds(long seconds) throws InterruptedException {
        Thread.sleep(TimeUnit.SECONDS.toMillis(seconds));
    }
}