package context;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ScenarioRequest {
    private EndpointServer endpointServer = new EndpointServer();
    private List<Header> headers = new ArrayList<>();
    private List<Cookie> cookies = new ArrayList<>();
    private Map<String, String> queryParams = new HashMap<>();
    private RestAssuredConfig config = RestAssuredConfig.config();

    public void setHost(String host) { endpointServer.setHost(host); }

    public void setPort(int port) { endpointServer.setPort(port); }

    public void setBasePath(String basePath) { endpointServer.setBasePath(basePath); }

    public void addHeaders(List<Header> headers) {
        this.headers.clear();
        this.headers.addAll(headers);
    }

    public void addQueryParams(Map<String, String> queryParams) {
        this.queryParams = queryParams;
    }

    public void setConfig(Function<RestAssuredConfig, RestAssuredConfig> config) {
        this.config = config.apply(this.config);
    }

    public RequestSpecification getRequestSpecification() {

        if(endpointServer.getPort() == 0)
            return new RequestSpecBuilder()
                    .setBaseUri(endpointServer.getHost())
                    .setBasePath(endpointServer.getBasePath())
                    .addCookies(new Cookies(cookies))
                    .addHeaders(headers.stream().collect(Collectors.toMap(Header::getName, Header::getValue)))
                    .addQueryParams(queryParams)
                    .setConfig(config)
                    .build();
        else
            return new RequestSpecBuilder()
                    .setBaseUri(endpointServer.getHost())
                    .setPort(endpointServer.getPort())
                    .setBasePath(endpointServer.getBasePath())
                    .addCookies(new Cookies(cookies))
                    .addHeaders(headers.stream().collect(Collectors.toMap(Header::getName, Header::getValue)))
                    .addQueryParams(queryParams)
                    .setConfig(config)
                    .build();


    }

}