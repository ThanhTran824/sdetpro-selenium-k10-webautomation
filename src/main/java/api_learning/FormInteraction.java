package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FormInteraction {
    public static void main(String[] args) {
        // open Chrome browser
        WebDriver driver = DriverFactory.getChromeDriver();

        try{
            // Navigate to the target page
            driver.get("http://the-internet.herokuapp.com/login");

            // Define selectors
            // NoSuchElementException is thrown
            // to indicate that the element being requested does not exist
            By usernameSel = By.id("username");
            By pwdSel = By.name("password");
            By loginBtnSel = By.cssSelector("button[type='submit']");

            // Find element
            WebElement usernameElement = driver.findElement(usernameSel);
            WebElement pwdElement = driver.findElement(pwdSel);
            WebElement loginBtnElement = driver.findElement(loginBtnSel);

            // Interact with elements
            usernameElement.sendKeys("tomsmith");
            pwdElement.sendKeys("SuperSecretPassword!");

            // Refresh page then re-input
            // StaleElementReferenceException is thrown
            // to refers to something which is not new and perished

            driver.navigate().refresh();
            usernameElement = driver.findElement(usernameSel);
            pwdElement = driver.findElement(pwdSel);
            loginBtnElement = driver.findElement(loginBtnSel);

            usernameElement.sendKeys("tomsmith");
            pwdElement.sendKeys("SuperSecretPassword!");
            loginBtnElement.click();

            // User Dashboard Page

            By headingWithTagName = By.tagName("h2");
            By headingWithCSS = By.cssSelector("h2");

            // Explicit wait only wait for only an element
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(headingWithCSS)));

            WebElement headingElement = driver.findElement(headingWithCSS);

            System.out.println("Heading is: " + headingElement.getText());

            //
            // wait.until(ExpectedConditions.urlToBe("driver.findElement(headingSel)"));

            // Find by link text, partial link text
            System.out.println(driver.findElement(By.linkText("Elemental Selenium")).getText());
            System.out.println(driver.findElement(By.partialLinkText("Elemental")).getText());
            System.out.println(driver.findElement(By.linkText("Elemental Selenium")).getAttribute("href"));
            System.out.println(driver.getCurrentUrl());
            System.out.println(driver.getTitle());

            // Check ton tai $(#username)
            // Check button $('button[type="submit"]')

            Thread.sleep(3000);

        }catch (Exception ex){
            ex.printStackTrace();
        }

        // close browser
        driver.quit();
    }
}
