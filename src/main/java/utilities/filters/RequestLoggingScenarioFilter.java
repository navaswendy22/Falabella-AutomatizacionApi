package utilities.filters;

import cucumber.api.Scenario;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.UrlDecoder;
import io.restassured.internal.print.RequestPrinter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.commons.lang3.Validate;

import java.io.PrintStream;
import java.nio.charset.Charset;

public class RequestLoggingScenarioFilter implements Filter {
    private Scenario scenario;
    private final LogDetail logDetail;
    private final PrintStream stream;
    private final boolean shouldPrettyPrint;
    private final boolean showUrlEncodedUri;

    public RequestLoggingScenarioFilter(Scenario scenario) {
        this(LogDetail.ALL, System.out);
        this.scenario = scenario;
    }

    public RequestLoggingScenarioFilter(LogDetail logDetail, PrintStream stream) {
        this(logDetail, true, stream);
    }

    public RequestLoggingScenarioFilter(LogDetail logDetail, boolean shouldPrettyPrint, PrintStream stream) {
        this(logDetail, shouldPrettyPrint, stream, true);
    }

    public RequestLoggingScenarioFilter(LogDetail logDetail, boolean shouldPrettyPrint, PrintStream stream, boolean showUrlEncodedUri) {
        Validate.notNull(stream, "Print stream cannot be null", new Object[0]);
        Validate.notNull(logDetail, "Log details cannot be null", new Object[0]);
        if (logDetail == LogDetail.STATUS) {
            throw new IllegalArgumentException(String.format("%s is not a valid %s for a request.", LogDetail.STATUS, LogDetail.class.getSimpleName()));
        } else {
            this.stream = stream;
            this.logDetail = logDetail;
            this.shouldPrettyPrint = shouldPrettyPrint;
            this.showUrlEncodedUri = showUrlEncodedUri;
        }
    }

    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        String uri = requestSpec.getURI();
        if (!this.showUrlEncodedUri) {
            uri = UrlDecoder.urlDecode(uri, Charset.forName(requestSpec.getConfig().getEncoderConfig().defaultQueryParameterCharset()), true);
        }

        scenario.write(RequestPrinter.print(requestSpec, requestSpec.getMethod(), uri, this.logDetail, this.stream, this.shouldPrettyPrint));
        return ctx.next(requestSpec, responseSpec);
    }
}
