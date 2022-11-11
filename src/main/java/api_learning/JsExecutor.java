package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import url.Urls;

public class JsExecutor {

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getChromeDriver();

        try {
            // Navigate to the target page
            driver.get(Urls.BASE_URL.concat(Urls.FLOATING_MENU_SLUG));
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

            // Scroll to bottom
            jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");

            // Debug purpose only
            Thread.sleep(2000);

            // Scroll to top
            jsExecutor.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
            // Debug purpose only
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
