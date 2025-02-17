package testng.restassured;

import com.sun.net.httpserver.Request;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredImpl {

    public static void main(String[] args) {
        getListOfAllObject();
       //getSingleObject();
       //listOfObjectsById(new String []{"1","4","5"});
      // addObjects();
       //updateObject();
       //partialUpdateObjects();
       //deleteObjects();

        
    }

    //Materi Rest Assured
    //given-when-then

    /*
     * https://api.restful-api.dev/objects/1
     * base uri =  https://api.restful-api.dev
     * path = objects
     * pathparam = 1
     * 
     */

    static void getListOfAllObject (){
        RestAssured.baseURI = "https://api.restful-api.dev";

        RequestSpecification requestSpecification = RestAssured.given();
        
        Response response = requestSpecification.log().all().get("objects");

        //cara 2
        Response response2 = RestAssured
                                .given()
                                .log()
                                .all()
                                .get("objects");

        System.out.println("The Result Is :"+ response2.asPrettyString());


        JsonPath jsonPath = response.jsonPath();

        System.out.println("Data id 1 "+ jsonPath.get("id[1]"));
        System.out.println("Product Name :"+ jsonPath.get("data[0].color"));

    }

    static void getSingleObject (){

        RestAssured.baseURI = "https://api.restful-api.dev";
        
        RequestSpecification requestSpecification = RestAssured.given();

        Response response = requestSpecification
                                        .pathParam("path", "objects")
                                        .pathParam("idObject", 7)
                                        .log()
                                        .all()
                                        .when()
                                        .get("{path}/{idObject}");
        System.out.println("The Result is :"+ response.asPrettyString());
    }

    static void listOfObjectsById (String [] id){

        RestAssured.baseURI = "https://api.restful-api.dev";
        RequestSpecification requestSpecification = RestAssured.given();

        Response response = requestSpecification
                                                .pathParam("path", "objects")
                                                .queryParam("id",  id)
                                                .when()
                                                .get("{path}");
                                                
    System.out.println("Result "+ response.asPrettyString());                                                      
                                                
    }

    static void addObjects (){

        String json = "{\r\n" + //
                        "   \"name\": \"Apple MacBook Pro 16\",\r\n" + //
                        "   \"data\": {\r\n" + //
                        "      \"year\": 2019,\r\n" + //
                        "      \"price\": 1849.99,\r\n" + //
                        "      \"CPU model\": \"Intel Core i9\",\r\n" + //
                        "      \"Hard disk size\": \"1 TB\"\r\n" + //
                        "   }\r\n" + //
                        "}";
        
        RestAssured.baseURI = "https://api.restful-api.dev";

        RequestSpecification requestSpecification = RestAssured.given();

        Response response = requestSpecification
                                                .pathParam("path", "objects")
                                                .contentType("application/json")
                                                .log()
                                                .all()
                                                .body(json)
                                                .when()
                                                .post("{path}");

        System.out.println("Result : "+ response.asPrettyString() );   
        
        JsonPath jsonPath = response.jsonPath();
        
        System.out.println("Id :"+ jsonPath.get("id"));

    }

    static void updateObject (){

        String json = "{\r\n" + //
                        "   \"name\": \"Apple MacBook Pro 16 Ajjah\",\r\n" + //
                        "   \"data\": {\r\n" + //
                        "      \"year\": 2019,\r\n" + //
                        "      \"price\": 2049.99,\r\n" + //
                        "      \"CPU model\": \"Intel Core i9\",\r\n" + //
                        "      \"Hard disk size\": \"1 TB\",\r\n" + //
                        "      \"color\": \"silver\"\r\n" + //
                        "   }\r\n" + //
                        "}";
        
        RestAssured.baseURI = "https://api.restful-api.dev";

        RequestSpecification requestSpecification = RestAssured.given();

        Response response = requestSpecification
                            .pathParam("path", "objects")
                            .pathParam("id", "ff808181932badb60195134ca3d04101")
                            .contentType("application/json")
                            .body(json)
                            .when()
                            .put("{path}/{id}");

        System.out.println("The Result :" +response.asPrettyString());
    }

    static void partialUpdateObjects (){

        String json = "{\r\n" + //
                        "   \"name\": \"Apple MacBook Pro 16 (Updated Name)\"\r\n" + //
                        "}\r\n" + //
                        "\r\n" + //
                        "";

               RestAssured.baseURI = "https://api.restful-api.dev" ;
               
               RequestSpecification requestSpecification = RestAssured.given();

               Response response = requestSpecification
                                                      .pathParam("path", "objects")
                                                      .pathParam("id", "ff808181932badb60195134ca3d04101")
                                                      .contentType("application/json")
                                                      .body(json)
                                                      .put("{path}/{id}");

                             System.out.println("Result : "+ response.asPrettyString());                         

    }

    static void deleteObjects (){
        RestAssured.baseURI = "https://api.restful-api.dev";

        RequestSpecification requestSpecification = RestAssured.given();
        
        Response response = requestSpecification
                                                .pathParam("path", "objects")
                                                .pathParam("id", "ff808181932badb60195134ca3d04101")
                                                .when()
                                                .delete("{path}/{id}");

        System.out.println("Result : "+ response.asPrettyString());                                        
    }

}
