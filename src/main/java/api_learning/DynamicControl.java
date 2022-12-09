package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.ui.WaitForElementEnabled;
import url.Urls;

import java.time.Duration;

public class DynamicControl {

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getChromeDriver();


        try {
            // Navigate to the target page
            driver.get(Urls.BASE_URL.concat(Urls.DYNAMIC_CONTROL_SLUG));
            // Define 2 forms locator
            By checkboxFormSel = By.id("checkbox-example");
            By inputFormSel = By.cssSelector("#input-example");
            //Interact with the checkbox element
            WebElement checkboxFormElem = driver.findElement(checkboxFormSel);
            // If the checkbox is not selected then select it
            // Must select input field in checkbox to ensure checkbox can click
            WebElement checkboxElem = checkboxFormElem.findElement(By.tagName("input"));
            if (!checkboxFormElem.isSelected()) {
                checkboxFormElem.click();
            }

            // Debug purpose only
            Thread.sleep(3000);
            // Interact with the input form element
            WebElement inputFormElem = driver.findElement(inputFormSel);
            WebElement inputFieldElem = inputFormElem.findElement(By.tagName("input"));
            WebElement inputFormBtnElem = inputFormElem.findElement(By.tagName("button"));
            // If the text field is not enabled then click on the button
            if (!inputFieldElem.isEnabled()) {
                inputFormBtnElem.click();

                // Wait until the text field is enabled then sendkeys
                // Using asynchronously
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20L));
                wait.until(new WaitForElementEnabled(By.cssSelector("#input-example input")));
            }

            inputFieldElem.sendKeys("This is a new text!");

            // Debug purpose only
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
