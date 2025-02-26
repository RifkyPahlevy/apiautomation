package apiengine;

import org.testng.Assert;

import com.apiautomation.model.ResponseObject;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import request.RequestObject;

public class ValidationTest {


    public void validationObject (Response response){

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(response.asPrettyString());
    }

    public void validationSuccessAddObject (Response response, ResponseObject responseObject, JsonPath jsonPath){

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(responseObject.id, jsonPath.get("id"));
        Assert.assertEquals(responseObject.dataItem.year, 2025);
        Assert.assertEquals(responseObject.dataItem.price, 1849.99);
        Assert.assertEquals(responseObject.dataItem.cpu, "Intel Ajah i9");
        Assert.assertEquals(responseObject.dataItem.hardisk, "1 TB");
    }


    public void validationSuccessAddWithData (Response response, ResponseObject responseObject, RequestObject requestObject, JsonPath jsonPath){

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(responseObject.id, jsonPath.get("id"));
        Assert.assertEquals(responseObject.dataItem.year, requestObject.dataRequest.year);
        Assert.assertEquals(responseObject.dataItem.price, requestObject.dataRequest.price);
        Assert.assertEquals(responseObject.dataItem.cpu, requestObject.dataRequest.cpuModel);
        Assert.assertEquals(responseObject.dataItem.hardisk, requestObject.dataRequest.hardiskSize);
    }

    public void validationSuccessAddNewData (Response response, ResponseObject responseObject, RequestObject requestObject, JsonPath jsonPath) {

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(responseObject.nama, requestObject.name);
        Assert.assertEquals(responseObject.dataItem.year, requestObject.dataRequest.year);
        Assert.assertEquals(responseObject.dataItem.price, requestObject.dataRequest.price);
        Assert.assertEquals(responseObject.dataItem.cpu, requestObject.dataRequest.cpuModel);
        Assert.assertEquals(responseObject.dataItem.hardisk, requestObject.dataRequest.hardiskSize);
    }

    public void validatesuccessDeleteObject (Response response, JsonPath jsonPath, String id){

        
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(jsonPath.get("message"), String.format("Object with id = %s has been deleted.", id));
    }
}
