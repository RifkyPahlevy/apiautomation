package com.apiautomation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
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

    @JsonProperty("message")
    public String message;

    @JsonProperty ("data")
    public DataItem dataItem;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class DataItem {

        @JsonProperty ("color")
        public String color;

        @JsonProperty ("capacity GB")
        public String capacityGb;

        @JsonProperty ("capacity")
        public String capacity;

        @JsonProperty ("year")
        public int year;

        @JsonProperty ("generation")
        public String generation;

        @JsonProperty ("price")
        public double price;

        @JsonProperty ("CPU model")
        public String cpu;

        @JsonProperty ("Hard disk size")
        public String hardisk;

        
    }

    


}
