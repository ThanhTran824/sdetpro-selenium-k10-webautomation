package models.components.order;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class ComputerEssentialComponent extends BaseItemDetailsComponent {

    private static final By allOptionSel = By.cssSelector(".option-list input");

    public ComputerEssentialComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void unselectAllDefaultOptions(){
        List<WebElement> allOptionElem = findElements(allOptionSel);
        allOptionElem.forEach(option -> {
            if(option.getAttribute("checked") != null) {
                option.click();
            }
        });
    }

    public abstract String selectProcessorType(String type);
    public abstract String selectRAMType(String type);

    @Step("Select HDD with value {type}")
    public String selectHDD(String type){
        return selectCompOption(type);
    }

    @Step("Select OS with value {type}")
    public String selectOS(String type){
        return selectCompOption(type);
    }

    @Step("Select computer option with value {type}")
    protected String selectCompOption(String type){
        String selectorStr = "//label[contains(text(),\"" + type + "\")]";
        By optionSel = By.xpath(selectorStr);
        WebElement optionElem = null;

        try {
            optionElem = component.findElement(optionSel);
        } catch (Exception ignored){}

        if(optionElem != null){
            optionElem.click();
            return optionElem.getText();
        } else {
            throw new RuntimeException("The option: " + type + " is not existing to select!");
        }
    }

}