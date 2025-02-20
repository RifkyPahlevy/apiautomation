package example;

import org.testng.annotations.DataProvider;

public class StaticProvider {

    @DataProvider (name= "dataProviderPositive")
    public Object [][] getDataPositive(){

        return new Object[][]{
            {"Rifky",1},
            {"Akmal",2}
        };

    }


    @DataProvider (name="dataProviderNegative")
    public Object [][] getDataNegative(){

        return new Object [][]{
            {"Akaml",1},
            {"Wahid",3}
        };
    }

}
