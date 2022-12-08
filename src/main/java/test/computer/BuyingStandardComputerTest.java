package test.computer;

import models.components.order.StandardComputerComponent;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.BaseTest;
import test_data.computer.ComputerData;
import test_data.DataObjectBuilder;
import test_flows.computer.OrderComputerFlow;
import url.Urls;

import java.security.SecureRandom;

public class BuyingStandardComputerTest extends BaseTest implements Urls {

    @Test(dataProvider = "computerData")
    public void testStandardBuying(ComputerData computerData) {
        driver.get(BASE_URL.concat("/build-your-own-computer"));
        int randomQuantity = new SecureRandom().nextInt(100) + 2;
        OrderComputerFlow<StandardComputerComponent> orderComputerFlow =
                new OrderComputerFlow<>(driver, StandardComputerComponent.class, computerData, randomQuantity);

        orderComputerFlow.buildComputerSpecAndAddToCart();
        orderComputerFlow.verifyShoppingCartPage();
        orderComputerFlow.agreeTOSAndCheckOut();
        orderComputerFlow.inputBillingAddress();
        orderComputerFlow.inputShippingAddress();
        orderComputerFlow.selectPaymentMethod();
    }

    @DataProvider
    public ComputerData[] computerData() {
        String fileLocation = "/src/test/java/test_data/computer/StandardComputerDataList.json";
        return DataObjectBuilder.buildDataObjectFrom(fileLocation, ComputerData[].class);
    }
}