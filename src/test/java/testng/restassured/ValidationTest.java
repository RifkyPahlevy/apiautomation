package testng.restassured;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.apiautomation.model.ResponseObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ValidationTest {

    ResponseObject responseObject;

    @Test
    public void addObjects() {

        String json = "{\r\n"
                + //
                "   \"name\": \"Laptop Barbie\",\r\n"
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
        RequestSpecification requestSpecification = RestAssured.given();

        Response response = requestSpecification
                .pathParam("path", "objects")
                .contentType("application/json")
                .body(json)
                .when()
                .post("{path}");

        System.out.println("result :" + response.asPrettyString());

        JsonPath addJsonPath = response.jsonPath();

        String name = addJsonPath.get("name");
        var year = addJsonPath.get("data.year");
        var price = addJsonPath.getDouble("data.price");
        var cpuModel = addJsonPath.get("data.'CPU model'");
        var hardisk = addJsonPath.get("data.'Hard disk size'");

        //cara 2
        responseObject = addJsonPath.getObject("", ResponseObject.class);

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(responseObject.id, addJsonPath.get("id"));
        Assert.assertEquals(responseObject.dataItem.year, 2025);
        Assert.assertEquals(responseObject.dataItem.price, 1849.99);
        Assert.assertEquals(responseObject.dataItem.cpu, "Intel Ajah i9");
        Assert.assertEquals(responseObject.dataItem.hardisk, "1 TB");

    }

    @Test
    public void listOfAllObject() throws JsonProcessingException {

        RestAssured.baseURI = "https://api.restful-api.dev";

        Response response = RestAssured
                .given()
                .pathParam("path", "objects")
                .log()
                .all()
                .get("{path}");

        System.out.println("Result :" + response.asPrettyString());

        // System.out.println("Result Name :"+getJsonPath.get("name"));
        ObjectMapper objectMapper = new ObjectMapper();

        TypeReference<List<ResponseObject>> responseTypeReference = new TypeReference<List<ResponseObject>>() {
        };

        List<ResponseObject> responseList = objectMapper.readValue(response.asPrettyString(), responseTypeReference);

        System.out.println("Result " + responseList.get(0).dataItem.color);

    }

    @Test
    public void listOfObjectsById() throws JsonProcessingException {

        RestAssured.baseURI = "https://api.restful-api.dev";

        Response response = RestAssured
                .given()
                .pathParam("path", "objects")
                .queryParam("id", "3", "4", "5")
                .get("{path}");

        System.out.println(response.asPrettyString());

        ObjectMapper objectMapper = new ObjectMapper();

        TypeReference<List<ResponseObject>> typeReference = new TypeReference<List<ResponseObject>>() {
        };
        List<ResponseObject> responseList = objectMapper.readValue(response.asPrettyString(), typeReference);

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(responseList.get(0).nama, "Apple iPhone 12 Pro Max");
        Assert.assertEquals(responseList.get(0).dataItem.color, "Cloudy White");
        Assert.assertEquals(responseList.get(1).dataItem.price, 389.99);

    }

    @Test
    public void getSingleObject() throws JsonProcessingException {

        RestAssured.baseURI = "https://api.restful-api.dev";

        Response response = RestAssured
                .given()
                .log()
                .all()
                .pathParam("id", "7")
                .pathParam("path", "objects")
                .get("{path}/{id}");

        JsonPath jsonPath = response.jsonPath();

        responseObject = jsonPath.getObject("", ResponseObject.class);

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(responseObject.nama, "Apple MacBook Pro 16");
        Assert.assertEquals(responseObject.dataItem.year, 2019);
        Assert.assertEquals(responseObject.dataItem.price, 1849.99);
        Assert.assertEquals(responseObject.dataItem.cpu, "Intel Core i9");
        Assert.assertEquals(responseObject.dataItem.hardisk, "1 TB");

    }

    @Test
    public void updateObject (){

        String json = "{\r\n" + //
                        "   \"name\": \"Laptop Jadoel\",\r\n" + //
                        "   \"data\": {\r\n" + //
                        "      \"year\": 1945,\r\n" + //
                        "      \"price\": 2.99,\r\n" + //
                        "      \"CPU model\": \"Pentium\",\r\n" + //
                        "      \"Hard disk size\": \"1 TB\",\r\n" + //
                        "      \"color\": \"warna - warni\"\r\n" + //
                        "   }\r\n" + //
                        "}";

        RestAssured.baseURI = "https://api.restful-api.dev";
        
        Response response = RestAssured
            .given()
            .log()
            .all()
            .pathParam("path", "objects")
            .pathParam("id", "ff808181932badb6019518e7b9f25177")
            .contentType("application/json")
            .body(json)
            .when()
            .put("{path}/{id}");

            JsonPath jsonPath = response.jsonPath();
            
            responseObject = jsonPath.getObject("", ResponseObject.class);

            Assert.assertEquals(response.statusCode(), 200);
            Assert.assertNotNull(responseObject.id);
            Assert.assertEquals(responseObject.nama, "Laptop Jadoel");
            Assert.assertEquals(responseObject.dataItem.year, 1945);
            Assert.assertEquals(responseObject.dataItem.price, 2.99);
            Assert.assertEquals(responseObject.dataItem.cpu, "Pentium");
            Assert.assertEquals(responseObject.dataItem.hardisk, "1 TB");
            Assert.assertEquals(responseObject.dataItem.color, "warna - warni");




            

        
    }


    @Test
    public void updatePartialObject (){

        String json = "{\r\n" + //
                        "   \"name\": \"Apple MacBook Pro 16 (Updated Name)\"\r\n" + //
                        "}";

        RestAssured.baseURI = "https://api.restful-api.dev";
        
        Response response = RestAssured
                            .given()
                            .log()
                            .all()
                            .pathParam("path", "objects")
                            .pathParam("id", "ff808181932badb6019518e7b9f25177")
                            .contentType("application/json")
                            .body(json)
                            .when()
                            .patch("{path}/{id}");

                       JsonPath jsonPath = response.jsonPath();
                       
                       responseObject = jsonPath.getObject("", ResponseObject.class);

                        
                       Assert.assertEquals(response.statusCode(), 200);
                       Assert.assertNotNull(responseObject.id); 
                       Assert.assertEquals(responseObject.nama, "Apple MacBook Pro 16 (Updated Name)");
                       Assert.assertEquals(responseObject.dataItem.year, 1945 );
                       
                     

    }

    @Test
    public void deleteObjects (){
        RestAssured.baseURI = "https://api.restful-api.dev";

        Response response = RestAssured
        .given()
        .pathParam("path", "objects")
        .pathParam("id", "ff808181932badb60195198d63a4539a")
        .when()
        .delete("{path}/{id}");

        JsonPath jsonPath =  response.jsonPath();

        responseObject = jsonPath.getObject("", ResponseObject.class);
        
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(responseObject.message, "Object with id = ff808181932badb60195198d63a4539a, has been deleted.");

        


    }

}
