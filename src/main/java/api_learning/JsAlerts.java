package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import url.Urls;

import java.time.Duration;

public class JsAlerts {

    private final static By jsAlertSel = By.cssSelector("button[onclick='jsAlert()']");
    private final static By jsConfirmSel = By.cssSelector("button[onclick='jsConfirm()']");
    private final static By jsPromptSel = By.cssSelector("button[onclick='jsPrompt()']");
    private final static By resultSel = By.cssSelector("#result");
    private Boolean isAccepted = true;


    public static void main(String[] args) {
        /*WebDriver driver = DriverFactory.getChromeDriver();
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5L));

        try {
            // Navigate to the target page
            driver.get(Urls.BASE_URL.concat(Urls.JS_ALERT_SLUG));
            // Js Alert | OK
            //WebElement triggerJsAlertBtnEle = driver.findElement(jsAlertSel);
            //triggerJsAlertBtnEle.click();
            //Alert jsAlert = wait.until(ExpectedConditions.alertIsPresent());
            //jsAlert.accept();
            //System.out.println("Result: " + driver.findElement(resultSel).getText());

            handleJsAlert(driver, true);

            // Debug purpose only
            Thread.sleep(2000);
            // Js Confirm | Cancel
            //WebElement triggerJsConfirmBtnEle = driver.findElement(jsConfirmSel);
            //triggerJsConfirmBtnEle.click();
            //Alert jsConfirm = wait.until(ExpectedConditions.alertIsPresent());
            //jsConfirm.dismiss();
            //System.out.println("Result: " + driver.findElement(resultSel).getText());

            handleJsAlert(driver, false);
            // Debug purpose only
            Thread.sleep(2000);

            // Js Prompt | input text then accept
            //WebElement triggerJsPromptBtnEle = driver.findElement(jsPromptSel);
            //triggerJsPromptBtnEle.click();
            //Alert jsPrompt = wait.until(ExpectedConditions.alertIsPresent());
            //jsPrompt.sendKeys("My name is Thanh");
            //jsPrompt.accept();
            //System.out.println("Result: " + driver.findElement(resultSel).getText());

            handleJsAlert(driver, "My name is Thanh!");

            // Debug purpose only
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();*/
    }

    private static void handleJsAlert(WebDriver driver, Boolean isAccepted) {

        WebElement triggerJsAlertBtnEle = driver.findElement(jsAlertSel);
        triggerJsAlertBtnEle.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5L));
        Alert jsAlert = wait.until(ExpectedConditions.alertIsPresent());
        if (isAccepted) {
            jsAlert.accept();
        } else jsAlert.dismiss();
        System.out.println("Result: " + driver.findElement(resultSel).getText());
    }

    private static void handleJsAlert(WebDriver driver, String inputStr) {
        WebElement triggerJsPromptBtnEle = driver.findElement(jsPromptSel);
        triggerJsPromptBtnEle.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5L));
        Alert jsPrompt = wait.until(ExpectedConditions.alertIsPresent());
        jsPrompt.sendKeys(inputStr);
        jsPrompt.accept();
        System.out.println("Result: " + driver.findElement(resultSel).getText());
    }
}
