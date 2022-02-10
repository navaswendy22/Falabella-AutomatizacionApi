package utilities.filters;

import cucumber.api.Scenario;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.filter.log.UrlDecoder;
import io.restassured.internal.print.RequestPrinter;
import io.restassured.internal.print.ResponsePrinter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import java.io.PrintStream;

public class ResponseLoggingScenarioFilter implements Filter {

    private Scenario scenario;
    private final PrintStream stream;
    private final LogDetail logDetail;
    private final boolean shouldPrettyPrint;

    public ResponseLoggingScenarioFilter(Scenario scenario) {
        this.scenario = scenario;
        this.stream = System.out;
        this.logDetail = LogDetail.ALL;
        this.shouldPrettyPrint = true;
    }

    @Override
    public Response filter(FilterableRequestSpecification requestSpecification,
                           FilterableResponseSpecification responseSpecification,
                           FilterContext filterContext) {
        Response response = filterContext.next(requestSpecification, responseSpecification);
        scenario.write(ResponsePrinter
                .print(response,
                        response,
                        this.stream,
                        this.logDetail,
                        this.shouldPrettyPrint));

        return response;
    }
}