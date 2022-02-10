package context;

import io.restassured.response.Response;

public class ScenarioResponse {
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}