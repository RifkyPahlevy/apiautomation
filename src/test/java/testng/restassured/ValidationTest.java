package testng.restassured;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.apiautomation.model.ResponseObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ValidationTest {

    
    ResponseObject responseObject;
    
    
    @Test
    public void addObjects (){

        String json = "{\r\n" + //
                        "   \"name\": \"Laptop Barbie\",\r\n" + //
                        "   \"data\": {\r\n" + //
                        "      \"year\": 2025,\r\n" + //
                        "      \"price\": 1849.99,\r\n" + //
                        "      \"CPU model\": \"Intel Ajah i9\",\r\n" + //
                        "      \"Hard disk size\": \"1 TB\"\r\n" + //
                        "   }\r\n" + //
                        "}";

        RestAssured.baseURI = "https://api.restful-api.dev";
        RequestSpecification requestSpecification = RestAssured.given();

        Response response = requestSpecification
                                                .pathParam("path", "objects")
                                                .contentType("application/json")
                                                .body(json)
                                                .when()
                                                .post("{path}");

                                                System.out.println("result :"+ response.asPrettyString());

                                                JsonPath addJsonPath = response.jsonPath();
         
                                                
        
        String name = addJsonPath.get("name") ;
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

}
