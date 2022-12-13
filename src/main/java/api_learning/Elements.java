package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import url.Urls;

public class Elements {

    public static void main(String[] args) {

        /*WebDriver driver = DriverFactory.getChromeDriver();

        try {
            // Navigate to the target page
            driver.get(Urls.BASE_URL.concat(Urls.ADD_REMOVE_ELEMENT_SLUG));

            // Debug purpose only
            Thread.sleep(3000);

            By addBtnSel = By.cssSelector("button[onclick='addElement()']");
            WebElement addBtnEle = driver.findElement(addBtnSel);
            addBtnEle.click();

            // Debug purpose only
            Thread.sleep(3000);
            // if delete button display, click remove button
            By toBeRemovedSel = By.cssSelector("button[onclick='deleteElement()']");
            // Way 1
            *//*boolean removeBtnIsEnabled = driver.findElement(toBeRemovedSel).isEnabled();
            if (removeBtnIsEnabled) {
                driver.findElement(toBeRemovedSel).click();
            }*//*
            // Way 2
            WebElement removeBtnEle = driver.findElement(toBeRemovedSel);
            if (removeBtnEle.isEnabled()) {
                removeBtnEle.click();
            }


            // Debug purpose only
            Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();*/

    }
}
