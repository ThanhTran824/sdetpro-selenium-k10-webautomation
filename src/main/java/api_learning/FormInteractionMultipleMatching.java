package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FormInteractionMultipleMatching {
    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getChromeDriver();
        try{
            // Navigate
            driver.get("http://the-internet.herokuapp.com/login");

            // Define selector value

            By inputFieldSel = By.tagName("input_taolao");
            //   WebElement usernameElem = driver.findElement(inputFieldSel);
            List<WebElement> inputFieldElem = driver.findElements(inputFieldSel);
            if(inputFieldElem.isEmpty()){
                throw new RuntimeException("[ERR] There is no input fields");
            }
            final int USENAME_INDEX = 0;
            final int PASSWORD_INDEX = 1;

            inputFieldElem.get(USENAME_INDEX).sendKeys("teo");
            inputFieldElem.get(PASSWORD_INDEX).sendKeys("12345678");

            //Debug purpose ONLY
            Thread.sleep(3000);

        }catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
