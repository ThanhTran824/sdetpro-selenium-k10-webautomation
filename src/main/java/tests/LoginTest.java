package tests;

import driver.DriverFactory;
import models.pages.LoginPageMod01;
import models.pages.LoginPageMod02;
import models.pages.LoginPageMod03;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import url.Urls;

public class LoginTest {

    public static void main(String[] args) {

        /*WebDriver driver = DriverFactory.getChromeDriver();

        try {
            // Navigate to the target page
            driver.get(Urls.BASE_URL.concat(Urls.LOGIN_SLUG));


            *//*LoginPageMod01 loginMode01 = new LoginPageMod01(driver);
            loginMode01.usernameElement().sendKeys("Teo");
            loginMode01.passwordElement().sendKeys("12345678");
            loginMode01.loginBtnElement().click();*//*



            *//*LoginPageMod02 loginMode02 = new LoginPageMod02(driver);
            loginMode02.inputUsernameElement("Teo");
            loginMode02.inputPasswordElement("12345678");
            loginMode02.clickLoginBtnElement();*//*

            // Chaining method
            LoginPageMod03 loginMod03 = new LoginPageMod03();
            loginMod03.setDriver(driver);
            loginMod03
                    .inputUsernameElement("admin")
                    .inputPasswordElement("admin")
                    .clickLoginBtnElement();


            // Debug purpose only
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();*/
    }
}
