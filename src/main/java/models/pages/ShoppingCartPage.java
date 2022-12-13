package models.pages;

import models.components.cart.CartItemRowComponent;
import models.components.cart.TotalComponent;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class ShoppingCartPage extends BasePage {

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
        Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = caps.getBrowserName();
        if (browserName.equals("safari")) {
            try {
                Thread.sleep(3000);
            } catch (Exception ignored) {}
        }
    }

    public List<CartItemRowComponent> cartItemRowCompList(){
        return findComponents(CartItemRowComponent.class, driver);
    }

    public TotalComponent totalComp(){
        return findComponent(TotalComponent.class, driver);
    }

}