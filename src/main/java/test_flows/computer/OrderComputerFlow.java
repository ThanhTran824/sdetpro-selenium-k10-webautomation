package test_flows.computer;

import models.order.ComputerEssentialComponent;
import models.pages.ComputerItemDetailsPage;
import org.openqa.selenium.WebDriver;

public class OrderComputerFlow<T extends ComputerEssentialComponent> {

    private final WebDriver driver;
    private final Class<T> computerEssentialComponent;

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComponent) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
    }

    public void buildComputerSpecAndAddToCart() {
        // Build computer spec
        ComputerItemDetailsPage computerItemDetailsPage = new ComputerItemDetailsPage(driver);
        T computerEssentialComp = computerItemDetailsPage.computerComponent(computerEssentialComponent);
        computerEssentialComp.selectProcessorType("2.5GHz");
        computerEssentialComp.selectRAMType("2GB");
        // Add to cart
    }
}
