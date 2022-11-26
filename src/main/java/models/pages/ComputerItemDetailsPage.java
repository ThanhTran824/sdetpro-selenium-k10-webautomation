package models.pages;

import models.order.ComputerEssentialComponent;
import org.openqa.selenium.WebDriver;

public class ComputerItemDetailsPage extends BasePage {

    public ComputerItemDetailsPage(WebDriver driver) {
        super(driver);
    }

    public <T extends ComputerEssentialComponent> T computerComponent(Class<T> computerEssentialComponentClass) {
        return findComponent(computerEssentialComponentClass, driver);
    }
}
