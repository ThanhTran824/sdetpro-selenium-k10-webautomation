package models.order;

import models.components.ComponentCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(value = ".product-essential")
public class StandardComputerComponent extends ComputerEssentialComponent {

    public StandardComputerComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    @Override
    public String selectProcessorType(String type) {
        return null;
    }

    @Override
    public String selectRAMType(String type) {
        return null;
    }
}
