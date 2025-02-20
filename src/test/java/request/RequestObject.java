package request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestObject {

   /*  "{\r\n"
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
                "}""
        
*/

    @JsonProperty("name")
    public String name;

    @JsonProperty("data")
    public DataRequest dataRequest;



    public class DataRequest {

        @JsonProperty("year")
        public int year;

        @JsonProperty("price")
        public Double price;

        @JsonProperty("CPU model")
        public String cpuModel;

        @JsonProperty("Hard disk size")
        public String hardiskSize;

    }

}
