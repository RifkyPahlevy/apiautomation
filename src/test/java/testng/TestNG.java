package testng;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNG {

    String name = "Rifky";

/*
 * Jadi before method akan exekusi tergantung pada jumlah method yang ada
 * Kalau before class tergantung jumlah kelas yang ada
 */

    @BeforeClass
       public void setUpClass (){
        System.out.println("Execute Before Class");
       }

    @BeforeMethod
    public void setUp (){
        System.out.println("Before Method Executed");
    }

    @Test
    public void scenarioTest1 (){
        Assert.assertEquals(name, "Rifky");
        System.out.println("Ini Scenario 1");
        System.out.println(Thread.currentThread().getId());
    }

    @Test
    public void scenarioTest2 (){
        Assert.assertEquals(name, "Rifky");
        System.out.println("Ini Scenario 2");
        System.out.println(Thread.currentThread().getId());
    }

    @Test
    public void scenarioTest3 (){
        Assert.assertEquals(name, "Rifky");
        System.out.println("Ini Scenario 3");
        System.out.println(Thread.currentThread().getId());
    }

    @AfterMethod
    public void closeUp (){
        System.out.println("After Method Executed");
    }

    @AfterClass
     public void closeUpClass (){
        System.out.println("Execute after class");
     }

    //use the dataprovider
     @Test (dataProvider =  "dataTest")
     public void getDataProvider (String name, int age){
        System.out.println("Name is : "+ name+ " and age : "+age);    

     }

     @DataProvider (name = "dataTest")
     public Object [][] dataTest (){
        return new Object [][] {
            {"Rifky",23},
            {"Akmal",20},
            {"Shadam",28}

        };
     }
}
