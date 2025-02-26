package apiengine;

import com.apiautomation.constants.Constant;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Endpoints {

    RequestSpecification requestSpecification;

    public Endpoints() {

        RestAssured.baseURI = Constant.BASE_URL;

        requestSpecification = RestAssured.given();

    }

    public Response getAllObject(String path) {

        Response response = requestSpecification.
                when()
                .get(path);

        return response;
    }

    public Response addObject(String path, String json) {

        Response response = requestSpecification
                .pathParam("path", path)
                .contentType("application/json")
                .body(json)
                .when()
                .post("{path}");

        return response;

    }

    public Response updateObject(String path, String id) {
        Response response = requestSpecification
                .pathParam("path", path)
                .pathParam("id", id)
                .contentType("application/json")
                .when()
                .put("{path}/{id}");

        return response;
    }

    public Response deleteObject(String path, String id) {
        Response response = requestSpecification
                .pathParam("path", path)
                .pathParam("id", id)
                .log()
                .all()
                .when()
                .delete("{path}/{id}");

        return response;
    }

    public Response getNewData(String path, String id) {

        Response response = requestSpecification
                .pathParam("path", path)
                .pathParam("id", id)
                .when()
                .get("{path}/{id}");

                System.out.println(response.asPrettyString());

        return response;
    }

}
