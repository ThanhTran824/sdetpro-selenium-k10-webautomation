package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v101.log.Log;
import url.Urls;

import java.util.List;

public class BrokenImage {

    /*
    To find broken images using the Selenium WebDriver,
    we would be using the 4xx class of status code,
    indicating that the particular page or the complete website is not reachable.

    Step 1: import HttpClient dependency to pom.xml file
    Step 2: find all images will check
    Step 3: Create a new instance of HttpClient
    Step 4: Create a new instance of HttpGet
    Step 5: Retrieve the response object
    Step 6: Read the status code
     */

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getChromeDriver();

        try {
            // Locate example class
            //By exampleClassSel = By.cssSelector(".example");
            //WebElement exampleClassEle = driver.findElement(exampleClassSel);
            // Locate all image elements
            //By imagesSel = By.tagName("img");
            //List<WebElement> imagesEle = exampleClassEle.findElements(imagesSel);

            DevTools devTools = ((ChromeDriver) driver).getDevTools();
            devTools.createSession();
            devTools.send(Log.enable());
            devTools.addListener(Log.entryAdded(),
                    logEntry -> {
                        System.out.println("Url: " + logEntry.getUrl());
                        System.out.println("log: " + logEntry.getText());
                        System.out.println("level: " + logEntry.getLevel());
                    });
            driver.get(Urls.BASE_URL.concat(Urls.BROKEN_IMAGE_SLUG));

            /*for (WebElement image : imagesEle) {
                //SendRequest request = new SendRequest(image.getAttribute("src").toString());
                log.checkImageStatus(image.getAttribute("src").toString());
            }*/

            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
