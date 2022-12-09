package models.components.checkout;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@ComponentCssSelector(value = "#opc-payment_method")
public class PaymentMethodComponent extends Component {

    public final static By codSel = By.cssSelector("input[value='Payments.CashOnDelivery']");
    public final static By checkMoneyOrderSel = By.cssSelector("input[value='Payments.CheckMoneyOrder']");
    public final static By creditCardSel = By.cssSelector("input[value='Payments.Manual']");
    public final static By purchaseOrderSel = By.cssSelector("input[value='Payments.PurchaseOrder']");
    public final static By continueBtnSel = By.cssSelector("input[class*='payment-method-next-step-button']");

    public PaymentMethodComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void selectCODMethod() {
        findElement(codSel).click();
    }

    public void selectCheckMoneyOrderMethod() {
        findElement(checkMoneyOrderSel).click();
    }

    public void selectCreditCardMethod() {
        findElement(creditCardSel).click();
    }

    public void selectPurchaseOrderMethod() {
        findElement(purchaseOrderSel).click();
    }

    public void clickOnContinueBtn() {
        WebElement continueBtn = findElement(continueBtnSel);
        continueBtn.click();
        wait.until(ExpectedConditions.invisibilityOf(continueBtn));
    }

}