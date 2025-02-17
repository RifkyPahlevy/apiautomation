package com.apiautomation.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseObject {

    
/*
 * "{\r\n" + //
                        "   \"name\": \"Laptop Barbie\",\r\n" + //
                        "   \"data\": {\r\n" + //
                        "      \"year\": 2025,\r\n" + //
                        "      \"price\": 1849.99,\r\n" + //
                        "      \"CPU model\": \"Intel Ajah i9\",\r\n" + //
                        "      \"Hard disk size\": \"1 TB\"\r\n" + //
                        "   }\r\n" + //
                        "}";
 * 
 */

    @JsonProperty ("id")
    public String id ;

    @JsonProperty ("name")
    public String nama;

    @JsonProperty ("data")
    public DataItem dataItem;

    public class DataItem {

        @JsonProperty ("year")
        public String year;

        @JsonProperty ("price")
        public int price;

        @JsonProperty ("'CPU model'")
        public String cpu;

        @JsonProperty ("'Hard disk size'")
        public String hardisk;

        
    }

    


}
