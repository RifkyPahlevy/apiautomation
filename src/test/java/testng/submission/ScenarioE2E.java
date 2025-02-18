package testng.submission;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.apiautomation.model.ResponseObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ScenarioE2E {

    String id;
    ResponseObject responseObject;
    JsonPath jsonPath;

    @Test
    public void scenatioE2ETest() {

        String json = "{\r\n"
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

        response = RestAssured
                .given()
                .log()
                .all()
                .pathParam("id", id)
                .pathParam("path", "objects")
                .get("{path}/{id}");

        responseObject = jsonPath.getObject("", ResponseObject.class);

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(responseObject.nama, "Laptop Jadoel");
        Assert.assertEquals(responseObject.dataItem.year, 2025);
        Assert.assertEquals(responseObject.dataItem.price, 1849.99);
        Assert.assertEquals(responseObject.dataItem.cpu, "Intel Ajah i9");
        Assert.assertEquals(responseObject.dataItem.hardisk, "1 TB");

        response = RestAssured
                .given()
                .pathParam("path", "objects")
                .pathParam("id", id)
                .when()
                .delete("{path}/{id}");

        jsonPath = response.jsonPath();
        responseObject = jsonPath.getObject("", ResponseObject.class);

        Assert.assertEquals(response.statusCode(), 200);

        Assert.assertEquals(responseObject.message, String.format("Object with id = %s has been deleted.", id));

        response = RestAssured
                .given()
                .log()
                .all()
                .pathParam("id", id)
                .pathParam("path", "objects")
                .get("{path}/{id}");

        jsonPath = response.jsonPath();
        responseObject = jsonPath.getObject("", ResponseObject.class);

        Assert.assertEquals(response.statusCode(), 404);
        Assert.assertEquals(responseObject.error, String.format("Oject with id=%s was not found.", id));

    }

}
