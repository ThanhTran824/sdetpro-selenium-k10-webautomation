package tests;

import driver.DriverFactory;
import models.components.LoginFormComponent;
import models.pages.HerokuLoginPage;
import org.openqa.selenium.WebDriver;
import url.Urls;

public class PageFooterTest {

    public static void main(String[] args) {

        WebDriver driver = DriverFactory.getChromeDriver();

        try {
            driver.get(Urls.BASE_URL.concat(Urls.LOGIN_SLUG));

            HerokuLoginPage loginPage = new HerokuLoginPage(driver);
            //System.out.println(loginPage.footerComponent().getLinkText());

            LoginFormComponent loginFormComponent = loginPage.loginFormComponent();
            //loginFormComponent.usernameElem().sendKeys("Dida");
            loginFormComponent.inputUsername("Dida");

            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            driver.quit();
        }
    }
}
