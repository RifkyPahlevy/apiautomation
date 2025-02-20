package stepdefinition;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.testng.Assert;

import com.apiautomation.model.ResponseObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
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

    @Before
    public void prinLog(Scenario scenario) {

        this.scenario = scenario;
    }

   @Given("I check list of all objects") 
    public void checkStorage() throws JsonProcessingException {

        RestAssured.baseURI = "https://api.restful-api.dev";

        Response response = RestAssured
                .given()
                .pathParam("path", "objects")
                .log()
                .all()
                .get("{path}");

        System.out.println("Result :" + response.asPrettyString());

        // System.out.println("Result Name :"+getJsonPath.get("name"));

       jsonPath = response.jsonPath();

       List<ResponseObject> responseList = jsonPath.getList("");
       Assert.assertEquals(response.statusCode(), 200);
       Assert.assertNotNull(responseList.size());        


      

        
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

        RestAssured.baseURI = "https://api.restful-api.dev";

        Response response = RestAssured
                .given()
                .pathParam("path", "objects")
                .contentType("application/json")
                .body(json)
                .when()
                .post("{path}");

        jsonPath = response.jsonPath();

        responseObject = jsonPath.getObject("", ResponseObject.class);

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(responseObject.id, jsonPath.get("id"));
        Assert.assertEquals(responseObject.dataItem.year, 2025);
        Assert.assertEquals(responseObject.dataItem.price, 1849.99);
        Assert.assertEquals(responseObject.dataItem.cpu, "Intel Ajah i9");
        Assert.assertEquals(responseObject.dataItem.hardisk, "1 TB");

        id = responseObject.id;
        scenario.log(response.asPrettyString());
    }

    @When("I add some {string} to objects")
    public void userAddAnObject(String data) throws JsonProcessingException {
        // String json = "{\r\n"
        //         + //
        //         "   \"name\": \"Laptop Jadoel\",\r\n"
        //         + //
        //         "   \"data\": {\r\n"
        //         + //
        //         "      \"year\": 2025,\r\n"
        //         + //
        //         "      \"price\": 1849.99,\r\n"
        //         + //
        //         "      \"CPU model\": \"Intel Ajah i9\",\r\n"
        //         + //
        //         "      \"Hard disk size\": \"1 TB\"\r\n"
        //         + //
        //         "   }\r\n"
        //         + //
        //         "}";

        dataResource = new DataResource();

        for (Map.Entry<String, String> entry : dataResource.addObjectsCollections().entrySet()) {
            if (entry.getKey().equals(data)) {
                json = entry.getValue();
                break;
            }
        }

        RestAssured.baseURI = "https://api.restful-api.dev";

        Response response = RestAssured
                .given()
                .pathParam("path", "objects")
                .contentType("application/json")
                .body(json)
                .when()
                .post("{path}");

        ObjectMapper objectMapper = new ObjectMapper();
        requestObject = objectMapper.readValue(json, RequestObject.class);

        jsonPath = response.jsonPath();

        responseObject = jsonPath.getObject("", ResponseObject.class);

        // Assert.assertEquals(response.statusCode(), 200);
        // Assert.assertNotNull(responseObject.id, jsonPath.get("id"));
        // Assert.assertEquals(responseObject.dataItem.year, 2025);
        // Assert.assertEquals(responseObject.dataItem.price, 1849.99);
        // Assert.assertEquals(responseObject.dataItem.cpu, "Intel Ajah i9");
        // Assert.assertEquals(responseObject.dataItem.hardisk, "1 TB");
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(responseObject.id, jsonPath.get("id"));
        Assert.assertEquals(responseObject.dataItem.year, requestObject.dataRequest.year);
        Assert.assertEquals(responseObject.dataItem.price, requestObject.dataRequest.price);
        Assert.assertEquals(responseObject.dataItem.cpu, requestObject.dataRequest.cpuModel);
        Assert.assertEquals(responseObject.dataItem.hardisk, requestObject.dataRequest.hardiskSize);

        id = responseObject.id;
        scenario.log(response.asPrettyString());
        System.out.println(response.asPrettyString());
    }

    @Then("I check the new object was added")
    public void getNewData() throws JsonProcessingException {

        RestAssured.baseURI = "https://api.restful-api.dev";

        Response response = RestAssured
                .given()
                .log()
                .all()
                .pathParam("id", id)
                .pathParam("path", "objects")
                .get("{path}/{id}");

        
        ObjectMapper requestMapper = new ObjectMapper();

        requestObject = requestMapper.readValue(json, RequestObject.class);

        JsonPath jsonPath = response.jsonPath();

        responseObject = jsonPath.getObject("", ResponseObject.class);

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(responseObject.nama, requestObject.name);
        Assert.assertEquals(responseObject.dataItem.year, requestObject.dataRequest.year);
        Assert.assertEquals(responseObject.dataItem.price, requestObject.dataRequest.price);
        Assert.assertEquals(responseObject.dataItem.cpu, requestObject.dataRequest.cpuModel);
        Assert.assertEquals(responseObject.dataItem.hardisk, requestObject.dataRequest.hardiskSize);

        scenario.log(response.asPrettyString());
    }

}
