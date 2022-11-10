package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import url.Urls;

import javax.swing.*;
import java.util.List;

public class MouseHover {

    private static final By figureSel = By.className("figure");
    private static final By profileNameSel = By.cssSelector(".figcaption h5");
    private static final By profileLinkSel = By.cssSelector(".figcaption a");


    public static void main(String[] args) {

        WebDriver driver = DriverFactory.getChromeDriver();

        try {

            // Navigate to the target page
            driver.get(Urls.BASE_URL.concat(Urls.HOVERS_SLUG));
            // Locate all image elements
            List<WebElement> figuresElem = driver.findElements(figureSel);
            if(figuresElem.isEmpty()){
               throw new RuntimeException("[Err] There is no figure to test");
            }
            //
            Actions actions = new Actions(driver);
            // Debug purpose only
            Thread.sleep(2000);

            for (WebElement figureElem : figuresElem) {
                WebElement profileNameElem = figureElem.findElement(profileNameSel);
                WebElement profileLinkElem = figureElem.findElement(profileLinkSel);

                // BEFORE Mouse Hover

                System.out.println("===========BEFORE MOUSE HOVER===========");
                System.out.println((profileNameElem.getText() + ": "
                        + profileLinkElem.getAttribute("href")));
                /*
                // Notes
                // element.getText() only work after mouse hover to it
                // element.getAttribute() work because it located in DOM Tree
                */
                System.out.println(profileLinkElem.isDisplayed());
                // Debug purpose only
                Thread.sleep(2000);

                // Mouse Hover
                actions.moveToElement(figureElem).perform();
                System.out.println("===========AFTER MOUSE HOVER===========");
                // AFTER Mouse Hover
                System.out.println((profileNameElem.getText() + ": "
                        + profileLinkElem.getAttribute("href")));

                System.out.println(profileLinkElem.isDisplayed());
            }

            // Mouse hover and locate the username and profile links

            // Debug purpose only
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
