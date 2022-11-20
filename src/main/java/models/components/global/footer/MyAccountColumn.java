package models.components.global.footer;

import models.components.ComponentCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(value = ".column.my-account")
public class MyAccountColumn extends FooterColumnComponent {

    public MyAccountColumn(WebDriver driver, WebElement component) {
        super(driver, component);
    }
}
