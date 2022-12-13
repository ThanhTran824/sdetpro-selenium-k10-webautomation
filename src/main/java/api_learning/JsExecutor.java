package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import url.Urls;

public class JsExecutor {

    public static void main(String[] args) {
        /*WebDriver driver = DriverFactory.getChromeDriver();

        try {
            // Navigate to the target page
            driver.get(Urls.BASE_URL.concat(Urls.FLOATING_MENU_SLUG));
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            PageHelper pageHelper = new PageHelper(jsExecutor);

            // Scroll to bottom
            //jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            pageHelper.scrollToBottom();


            // Debug purpose only
            Thread.sleep(2000);

            // Scroll to top
            //jsExecutor.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
            pageHelper.scrollToTop();

            // Debug purpose only
            Thread.sleep(2000);

            // Go to the Dynamic Controls page
            driver.get(Urls.BASE_URL.concat(Urls.DYNAMIC_CONTROL_SLUG));
            // Define Locator
            By checkboxFormSel = By.cssSelector("#checkbox-example");
            By inputFormSel = By.cssSelector("#input-example");
            // Find WebElement
            WebElement checkboxFormElm = driver.findElement(checkboxFormSel);
            WebElement inputFormElm = driver.findElement(inputFormSel);


            // Execute Js
            //jsExecutor.executeScript("arguments[0].setAttribute('style', 'background: blue; border: 4px solid red')", checkboxFormElm);
            //jsExecutor.executeScript("arguments[0].remove()", inputFormElm);
            pageHelper.removeElement(inputFormElm).changeElementStyle(checkboxFormElm);

            // Debug only purpose
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();*/
    }
}
