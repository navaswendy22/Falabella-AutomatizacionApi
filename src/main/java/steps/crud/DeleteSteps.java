package steps.crud;

import context.ScenarioRequest;
import context.ScenarioResponse;
import cucumber.api.java.en.When;
import steps.ScenarioHook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static io.restassured.RestAssured.given;

public class DeleteSteps extends CrudScenario {

    private String file = "";
    private String initialPath = "";

    public DeleteSteps(ScenarioRequest scenarioRequest,
                       ScenarioResponse scenarioResponse,
                       ScenarioHook scenarioHook,
                       ResponseObservable responseObservable) {
        super(scenarioRequest, scenarioResponse, scenarioHook, responseObservable);
    }

    @When("a DELETE request is sent to endpoint {string}")
    public void deleteRequestMethod(String path) {
        scenarioResponse.setResponse(
                given()
                        .filters(filters)
                        .spec(scenarioRequest.getRequestSpecification())
                        .when()
                        .delete(path)
                        .then()
                        .log().ifError()
                        .extract()
                        .response());

        responseObservable.updateResponse(scenarioResponse.getResponse());
    }

    @When("a DELETE request is sent to remove created endpoints {string}")
    public void deleteSpecificIdFromTxtRequestMethod(String path) {
        if (path.startsWith("/setting")) {
            file = "src/main/resources/feature/loan_manager/id-recorder.txt";
            initialPath = "/setting/";
        }

        List<String> ids = convertFileIntoList(file);
        for (String id : ids) {
            scenarioResponse.setResponse(
                    given()
                            .filters(filters)
                            .spec(scenarioRequest.getRequestSpecification())
                            .when()
                            .delete(initialPath + id)
                            .then()
                            .log().ifError()
                            .statusCode(200)
                            .extract()
                            .response());

            responseObservable.updateResponse(scenarioResponse.getResponse());
        }
        deleteFile(file);
    }

    public List<String> convertFileIntoList(String file) {
        try {
            File myObj = new File(file);
            myObj.createNewFile();
            Scanner myReader = new Scanner(myObj);
            List<String> list = new ArrayList<>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                list.add(data);
            }
            myReader.close();
            return list;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteFile(String fileToDelete) {
        File myObj = new File(fileToDelete);
        myObj.delete();
    }


}