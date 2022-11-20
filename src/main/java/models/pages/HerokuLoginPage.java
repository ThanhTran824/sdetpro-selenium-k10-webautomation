package models.pages;

import models.components.LoginFormComponent;
import org.openqa.selenium.WebDriver;

public class HerokuLoginPage extends BasePage {

    public HerokuLoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginFormComponent loginFormComponent() {
        return findComponent(LoginFormComponent.class, driver);
    }
}
