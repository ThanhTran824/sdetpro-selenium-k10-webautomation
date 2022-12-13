package api_learning;

import hearder.BasicAuthentication;
import driver.DriverFactory;
import hearder.DigestAuthentication;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.ui.WaitForElementEnabled;
import url.Urls;

import java.time.Duration;

/**
 * Project Name    : basic-authentication-handling-selenium-demo
 * Developer       : Thanh Tran
 * Version         : 1.0.0
 * Date            : 11/12/2022
 * Time            : 12:26 PM
 * Description     :
 **/

public class LoginAuthTest {

    private final static String USERNAME = "admin";
    private final static String PASSWORD = "admin";

    public static void main(String[] args) {
        /*WebDriver driver = DriverFactory.getChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30L));

        try {

            *//*BasicAuthentication basicHeader = new BasicAuthentication();
            basicHeader.setCredentials(driver, USERNAME, PASSWORD);

            // Navigate to the target page
            driver.get(Urls.BASE_URL.concat(Urls.BASIC_AUTHENTICATION_SLUG));

            wait.until(new WaitForElementEnabled(By.cssSelector(".example")));
            By basicAuthSel = By.cssSelector("h3");
            WebElement basicAuthEle = driver.findElement(basicAuthSel);
            String result = basicAuthEle.getText();
            System.out.println(result);

            // Debug purpose only
            Thread.sleep(5000);*//*

            DigestAuthentication digestAuthentication = new DigestAuthentication();
            digestAuthentication.setCredentials(driver, USERNAME, PASSWORD);

            // Navigate to the target page
            driver.get(Urls.BASE_URL.concat(Urls.DIGEST_AUTHENTICATION_SLUG));

            wait.until(new WaitForElementEnabled(By.cssSelector(".example")));
            By digestAuthenticationSel = By.cssSelector("h3");
            WebElement digestAuthenticationEle = driver.findElement(digestAuthenticationSel);
            String result = digestAuthenticationEle.getText();
            System.out.println(result);

            // Debug purpose only
            Thread.sleep(5000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();*/
    }
}
