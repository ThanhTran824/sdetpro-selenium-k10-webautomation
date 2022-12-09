package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import url.Urls;

public class IFrame {

    public static void main(String[] args) {
        // iframe[id^='mce'] // find an id startWith 'mce'
        // iframe[id$='mce'] // find an id endWith 'mce'

        WebDriver driver = DriverFactory.getChromeDriver();

        try {
            // Navigate to the target page
            driver.get(Urls.BASE_URL.concat(Urls.IFRAME_SLUG));
            // Locate the iframe
            WebElement iFrameEle = driver.findElement(By.cssSelector("iframe[id^='mce']"));
            //WebElement iFrameEle = driver.findElement(By.id("mce_0_ifr"));
            // Switch to the iframe
            driver.switchTo().frame(iFrameEle);
            // Input text into the input textarea
            WebElement editInputEle = driver.findElement(By.cssSelector("#tinymce"));
            editInputEle.click();
            editInputEle.clear();
            editInputEle.sendKeys("This is a new value!");
            // Switch to the parent frame
            driver.switchTo().defaultContent();
            // Debug purpose only
            Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
