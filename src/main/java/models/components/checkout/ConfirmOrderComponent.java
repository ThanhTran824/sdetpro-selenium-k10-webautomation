package models.components.checkout;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(value = "#opc-payment_info")
public class ConfirmOrderComponent extends Component {

    //confirm-order-next-step-button
    public ConfirmOrderComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }
}