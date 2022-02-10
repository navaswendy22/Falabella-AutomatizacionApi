package steps.crud;

import interfaces.CrudResponseValidatable;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

public class ResponseObservable {

    public List<CrudResponseValidatable> subscriber = new ArrayList<>();

    public void addSubscriber(CrudResponseValidatable responseValidator) {
        this.subscriber.add(responseValidator);
    }

    public void updateResponse(Response response) {
        for(CrudResponseValidatable responseValidator: subscriber) {
            responseValidator.updateResponse(response);
        }
    }
}