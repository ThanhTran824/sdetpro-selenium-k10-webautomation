package tests.testng;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Verifier;

public class TestNGOrder {

    /* Most used to
    * assertEquals
    * assertTrue
    * assertFalse
    * Assert.Fail()
    * They called Hard Assertion
    */
    @Test()
    public void testB() {
        System.out.println("Test B");
        //Verifier.equals(2, 2);
        //Assert.assertEquals(1, 2);

        // another way to use is called Soft Assertion
        // if this step make mistake, it will log and go to the next step
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(1, 2);
        softAssert.assertEquals(1, 1);

        System.out.println("Hello");
        softAssert.assertAll();// this method should call in last step to execute all Assert

    }

    //wait method B finish without error before execute test A
    //@Test
    public void testA() {
        System.out.println("Test A");
    }


}
