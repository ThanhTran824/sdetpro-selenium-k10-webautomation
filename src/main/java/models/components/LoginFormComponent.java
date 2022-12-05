package models.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(value = "#login")
public class LoginFormComponent extends Component {

    private final static By usernameSel = By.id("username");
    private final static By passwordSel = By.cssSelector("#password");
    private final static By loginBtnSel = By.cssSelector("[type='submit']");

    public LoginFormComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void inputUsername(String usernameTxt){
        driver.findElement(usernameSel).sendKeys(usernameTxt);
    }

    public void inputPassword(String passwordTxt){
        driver.findElement(passwordSel).sendKeys(passwordTxt);
    }

    public void clickOLoginBtn(){
        driver.findElement(loginBtnSel).click();
    }

}