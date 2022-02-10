package interfaces;

import io.restassured.response.Response;

public interface CrudResponseValidatable {
    void updateResponse(Response response);
}