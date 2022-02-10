package Runner;

import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.RestAssured;
import org.apache.http.params.CoreConnectionPNames;

import java.util.ArrayList;
import java.util.List;

public class RunnerTest {
    public static void main (String[] args) {

        List<String> optionsList = new ArrayList<>();
        optionsList.add("--glue");
        optionsList.add("steps");
        if(args.length > 0) {
            optionsList.add("--tags");
            optionsList.add(args[0]);
            System.out.println("Running tags: " + args[0]);
        } else {
            System.out.println("Running all features!");
        }
        optionsList.add("/features");

        //default filters
        RestAssured.filters(new RequestLoggingFilter(), new ErrorLoggingFilter());

        //default timeout of 5 seconds
        RestAssured.config = RestAssuredConfig
                .config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000)
                        .setParam(CoreConnectionPNames.SO_TIMEOUT, 5000));

        io.cucumber.core.cli.Main.main(optionsList.toArray(new String[0]));
    }
}

