package resource;

import java.util.HashMap;
import java.util.Map;

public class DataResource {

    public Map <String,String> addObjectsCollections (){

        Map <String, String> dataCollection = new HashMap<>();


        dataCollection.put("object1", "{\r\n"
                + //
                "   \"name\": \"Laptop JadoelQ Nih Yang Pertama\",\r\n"
                + //
                "   \"data\": {\r\n"
                + //
                "      \"year\": 1946,\r\n"
                + //
                "      \"price\": 1849.99,\r\n"
                + //
                "      \"CPU model\": \"Intel Ajah i9\",\r\n"
                + //
                "      \"Hard disk size\": \"1 TB\"\r\n"
                + //
                "   }\r\n"
                + //
                "}");

               dataCollection.put("object2", "{\r\n"
                + //
                "   \"name\": \"Laptop JadoelQ Nih yang kedua\",\r\n"
                + //
                "   \"data\": {\r\n"
                + //
                "      \"year\": 1945,\r\n"
                + //
                "      \"price\": 1849.99,\r\n"
                + //
                "      \"CPU model\": \"Intel Ajah i9\",\r\n"
                + //
                "      \"Hard disk size\": \"1 TB\"\r\n"
                + //
                "   }\r\n"
                + //
                "}") ;

        return dataCollection;
        
    }

}
