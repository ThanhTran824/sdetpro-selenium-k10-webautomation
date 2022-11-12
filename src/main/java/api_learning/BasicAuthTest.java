package api_learning;

import Hearders.BasicAuthentication;
import driver.DriverFactory;
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

public class BasicAuthTest {

    private final static String USERNAME = "admin";
    private final static String PASSWORD = "admin";

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30L));

        try {

            // Get the devtools from the running driver and create a session
            //DevTools devTools = ((ChromeDriver) driver).getDevTools();
            //devTools.createSession();
            // Enable the Network domain of devtools
            //devTools.send(Network.enable(Optional.of(100000), Optional.of(100000),
            //        Optional.of(100000)));
            //String auth = USERNAME + ":" + PASSWORD;

            // Encoding the username and password using Base64 (java.util)
            //String encodeToString = Base64.getEncoder().encodeToString(auth.getBytes());

            // Pass the network header -> Authorization : Basic <encode String>
            //Map<String, Object> headers = new HashMap<>();
            //headers.put("Authorization", "Basic " + encodeToString);
            //devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));
            BasicAuthentication header = new BasicAuthentication();
            header.credentials(driver, USERNAME, PASSWORD);

            // Navigate to the target page
            driver.get(Urls.BASE_URL.concat(Urls.BASIC_AUTHENTICATION_SLUG));

            wait.until(new WaitForElementEnabled(By.cssSelector(".example")));
            By basicAuthSel = By.cssSelector("h3");
            WebElement basicAuthEle = driver.findElement(basicAuthSel);
            String result = basicAuthEle.getText();
            System.out.println(result);

            // Debug purpose only
            Thread.sleep(5000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
