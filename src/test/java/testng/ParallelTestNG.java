package testng;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import example.StaticProvider;

public class ParallelTestNG {

        String name = "Rifky";
    
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
        @Test
         public void scenarioTest4 (){
            Assert.assertEquals(name, "Rifky");
            System.out.println("Ini Scenario 4");
            System.out.println(Thread.currentThread().getId());
        }
        @Test
         public void scenarioTest5 (){
            Assert.assertEquals(name, "Rifky");
            System.out.println("Ini Scenario 5");
            System.out.println(Thread.currentThread().getId());
        } 
        @Test
         public void scenarioTest6 (){
            Assert.assertEquals(name, "Rifky");
            System.out.println("Ini Scenario 6");
            System.out.println(Thread.currentThread().getId());
        }
    
        //Grouping
    @Test(groups = {"group","scenario1"}) 
    public void scenarioTestGroup1(){
        System.out.println("Ini adalah scenario test group1");
        System.out.println(Thread.currentThread().getId());
    }

    @Test(groups = {"group", "scenario2"}) 
    public void scenarioTestGroup2(){
        System.out.println("Ini adalah scenario test group2");
        System.out.println(Thread.currentThread().getId());
    }

    @Test(groups = {"funcast"}) 
    public void scenarioTestGroupFuncast(){
        System.out.println("Ini adalah scenario test groups");
        System.out.println(Thread.currentThread().getId());
    }


    //parameter
    @Parameters ({"program"})
    @Test
    public void getValueParam (String program){
        System.out.println("Value dari parameter program adalah "+program);
        Assert.assertEquals(program, "Bootcamp API Automation");

    }

   


    @Test (dataProvider="dataProviderPositive", dataProviderClass=StaticProvider.class)
    public void getValueProvider (String name, int age){
        System.out.println(name+" "+age);

    }
    
}
