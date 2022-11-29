package tests.computer;

import models.order.CheapComputerComponent;
import models.order.StandardComputerComponent;
import org.testng.annotations.Test;
import test_flows.computer.OrderComputerFlow;
import tests.BaseTest;
import url.Urls;

public class BuyingStandardComputerTest extends BaseTest {
    @Test
    public void testBuyingCheapComputer() {
        driver.get(Urls.BASE_URL.concat(Urls.BUILD_STANDARD_COMPUTER));
        OrderComputerFlow<StandardComputerComponent> orderComputerFlow =
                new OrderComputerFlow<>(driver, StandardComputerComponent.class);

        orderComputerFlow.buildComputerSpecAndAddToCart();

    }
}
