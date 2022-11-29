package tests.computer;

import models.order.CheapComputerComponent;
import org.testng.annotations.Test;
import test_flows.computer.OrderComputerFlow;
import tests.BaseTest;
import url.Urls;

public class BuyingCheapComputerTest extends BaseTest {
    @Test
    public void testBuyingCheapComputer() {
        driver.get(Urls.BASE_URL.concat(Urls.BUILD_YOUR_CHEAP_OWN_COMPUTER));
        OrderComputerFlow<CheapComputerComponent> orderComputerFlow =
                new OrderComputerFlow<>(driver, CheapComputerComponent.class);

        orderComputerFlow.buildComputerSpecAndAddToCart();
    }
}
