package models.order;

import models.components.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class ComputerEssentialComponent extends Component {

    public ComputerEssentialComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public abstract String selectProcessorType(String type);

    public abstract String selectRAMType(String type);

    protected String selectComputerOption(String type) {
        String selectStr = "//label[contains(text(),\"" + type + "\")]";
        By optionSel = By.xpath(selectStr);

        WebElement optionElem = null;

        try {
            optionElem = component.findElement(optionSel);

        } catch (Exception ignored) {
        }

        if (optionElem != null) {
            optionElem.click();
            return optionElem.getText();
        } else {
            throw new RuntimeException("[ERR] The option: " + type + " is not existing to select!");
        }
    }
}
