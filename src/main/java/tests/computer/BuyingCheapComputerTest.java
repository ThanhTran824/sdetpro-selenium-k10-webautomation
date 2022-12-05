package tests.computer;

import models.components.order.CheapComputerComponent;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test_data.DataObjectBuilder;
import test_data.computer.ComputerData;
import test_flows.computer.OrderComputerFlow;
import tests.BaseTest;
import url.Urls;

public class BuyingCheapComputerTest extends BaseTest {
    @Test(dataProvider = "computerData")
    public void testBuyingCheapComputer(ComputerData computerData) {

        driver.get(Urls.BASE_URL.concat(Urls.BUILD_YOUR_CHEAP_OWN_COMPUTER));

        OrderComputerFlow<CheapComputerComponent> orderComputerFlow =
                new OrderComputerFlow<>(driver, CheapComputerComponent.class, computerData);
        orderComputerFlow.buildComputerSpecAndAddToCart();

    }

    //using data driven of testNG
    @DataProvider
    private ComputerData[] computerData() {
        String fileLocation = "/src/main/java/test_data/computer/CheapComputerDataList.json";
        ComputerData[] computerDataList =
                DataObjectBuilder.buildDataObjectFrom(fileLocation, ComputerData[].class);
        return computerDataList;
    }

}
