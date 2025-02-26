package stepdefinition;

import java.util.Map;

import com.apiautomation.model.ResponseObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import apiengine.Endpoints;
import apiengine.ValidationTest;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import request.RequestObject;
import resource.DataResource;

public class stepdefinitionimpl {

    String id;
    ResponseObject responseObject;
    JsonPath jsonPath;
    Scenario scenario;
    DataResource dataResource;
    String json;
    RequestObject requestObject;
    Endpoints endpoints;
    Response response;
    ValidationTest validation;

    @BeforeStep
    public void prinLog(Scenario scenario) {

        endpoints = new Endpoints();
        validation = new ValidationTest();
        this.scenario = scenario;
    }

    @Given("I check list of all objects")
    public void checkStorage() throws JsonProcessingException {

        response = endpoints.getAllObject("objects");

        jsonPath = response.jsonPath();

        validation.validationObject(response);

    }

    @When("I add an object")
    public void userAddAnObject() {
        json = "{\r\n"
                + //
                "   \"name\": \"Laptop Jadoel\",\r\n"
                + //
                "   \"data\": {\r\n"
                + //
                "      \"year\": 2025,\r\n"
                + //
                "      \"price\": 1849.99,\r\n"
                + //
                "      \"CPU model\": \"Intel Ajah i9\",\r\n"
                + //
                "      \"Hard disk size\": \"1 TB\"\r\n"
                + //
                "   }\r\n"
                + //
                "}";

        response = endpoints.addObject("objects", json);

        jsonPath = response.jsonPath();

        responseObject = jsonPath.getObject("", ResponseObject.class);

       validation.validationSuccessAddObject(response, responseObject, jsonPath);

        id = responseObject.id;
        scenario.log(response.asPrettyString());
    }

    @When("I add some {string} to objects")
    public void userAddAnObject(String data) throws JsonProcessingException {

        dataResource = new DataResource();

        for (Map.Entry<String, String> entry : dataResource.addObjectsCollections().entrySet()) {
            if (entry.getKey().equals(data)) {
                json = entry.getValue();
                break;
            }
        }

        response = endpoints.addObject("objects", json);

        ObjectMapper objectMapper = new ObjectMapper();
        requestObject = objectMapper.readValue(json, RequestObject.class);

        jsonPath = response.jsonPath();

        responseObject = jsonPath.getObject("", ResponseObject.class);

        validation.validationSuccessAddWithData(response, responseObject, requestObject, jsonPath);

        id = responseObject.id;
        
    }

    @And("I check the new object was added")
    public void getNewData() throws JsonProcessingException {

        endpoints.getNewData("objects", id);

        ObjectMapper requestMapper = new ObjectMapper();

        requestObject = requestMapper.readValue(json, RequestObject.class);

        JsonPath jsonPath = response.jsonPath();

        responseObject = jsonPath.getObject("", ResponseObject.class);

        validation.validationSuccessAddNewData(response, responseObject, requestObject, jsonPath);

        scenario.log(response.asPrettyString());
    }

    @Then ("I delete an object")
    public void deleteAnObject (){

        
        response = endpoints.deleteObject("objects",id);

        JsonPath jsonPath = response.jsonPath();

        validation.validatesuccessDeleteObject(response, jsonPath, id);

      

    }


}
