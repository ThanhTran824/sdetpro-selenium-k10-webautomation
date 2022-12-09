package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import support.ui.SelectEx;
import url.Urls;

public class DropDown {

    public static void main(String[] args) {

        WebDriver driver = DriverFactory.getChromeDriver();

        try {
            // Navigate to the target page
            driver.get(Urls.BASE_URL.concat(Urls.DROPDOWN_SLUG));
            // Locate the select tag / Dropdown
            WebElement dropdownEle = driver.findElement(By.cssSelector("#dropdown"));
            Select selectOption = new Select(dropdownEle);
            // Select by visible text
            selectOption.selectByVisibleText("Option 1");
            Thread.sleep(2000);
            // Select by index
            selectOption.selectByIndex(2);
            Thread.sleep(2000);
            //  Select by value
            selectOption.selectByValue("1");
            Thread.sleep(2000);
            // Select with custom method
            SelectEx selectEx = new SelectEx(dropdownEle);
            selectEx.selectOption2();
            // Debug purpose only
            Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
