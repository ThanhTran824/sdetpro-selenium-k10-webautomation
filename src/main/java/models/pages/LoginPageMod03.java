package models.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageMod03 {

    private WebDriver driver;
    private final static By usernameSel = By.cssSelector("#username");
    private final static By passwordSel = By.cssSelector("#password");
    private final static By loginBtnSel = By.cssSelector("button[type='submit']");

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
    public LoginPageMod03 inputUsernameElement(String username) {
        driver.findElement(usernameSel).sendKeys(username);
        return this;
    }

    public LoginPageMod03 inputPasswordElement(String password) {
        driver.findElement(passwordSel).sendKeys(password);
        return this;
    }

    public LoginPageMod03 clickLoginBtnElement() {
        driver.findElement(loginBtnSel).click();
        return this;
    }

}
