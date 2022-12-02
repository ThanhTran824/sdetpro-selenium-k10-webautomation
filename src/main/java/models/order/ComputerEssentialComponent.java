package models.order;

import models.components.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class ComputerEssentialComponent extends Component {

    public ComputerEssentialComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public abstract String selectProcessorType(String type);

    public abstract String selectRAMType(String type);

    public String selectHDD(String type){
        return  selectComputerOption(type);
    }

    public String selectOS(String type){
        return  selectComputerOption(type);
    }

    public String selectSoftware(String type){
        return  selectComputerOption(type);
    }


    protected String selectComputerOption(String type) {
        String selectStr = "//label[contains(text(),\"" + type + "\")]";
        By optionSel = By.xpath(selectStr);

        /*WebElement optionElem = null;

        try {
            optionElem = component.findElement(optionSel);

        } catch (Exception ignored) {
        }

        if (optionElem != null) {
            optionElem.click();
            return optionElem.getText();
        } else {
            throw new RuntimeException("[ERR] The option: " + type + " is not existing to select!");
        }*/

        List<WebElement> optionsElem = null;
        optionsElem = component.findElements(optionSel);
        if(!optionsElem.isEmpty()){
            optionsElem.get(0).click();
            return optionsElem.get(0).getText();
        }
        else throw new RuntimeException("[ERR] The option: " + type + " is not existing to select!");
    }
}
