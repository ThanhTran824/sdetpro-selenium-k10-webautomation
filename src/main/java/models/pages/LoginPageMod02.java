package models.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageMod02 {
    private final WebDriver driver;
    private final static By usernameSel = By.cssSelector("#username");
    private final static By passwordSel = By.cssSelector("#password");
    private final static By loginBtnSel = By.cssSelector("button[type='submit']");

    public LoginPageMod02(WebDriver driver) {
        this.driver = driver;
    }

    public void inputUsernameElement(String username) {
        driver.findElement(usernameSel).sendKeys(username);
    }

    public void inputPasswordElement(String password) {
        driver.findElement(passwordSel).sendKeys(password);
    }

    public void clickLoginBtnElement() {
        driver.findElement(loginBtnSel).click();
    }

}
