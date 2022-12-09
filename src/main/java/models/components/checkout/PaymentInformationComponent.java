package models.components.checkout;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import test_data.CreditCardType;

@ComponentCssSelector(value = "#opc-payment_info")
public class PaymentInformationComponent extends Component {

    private static final By creditCardDropdownSel = By.cssSelector("#CreditCardType");
    private static final By cardHolderNameSel = By.cssSelector("#CardholderName");
    private static final By cardHolderNumSel = By.cssSelector("#CardNumber");
    private static final By expiredMonthDropdownSel = By.cssSelector("#ExpireMonth");
    private static final By expiredYearDropdownSel = By.cssSelector("#ExpireYear");
    private static final By cardCodeSel = By.cssSelector("#CardCode");
    private static final By continueBtnSel = By.cssSelector("input[class*='payment-info-next-step-button']");

    public PaymentInformationComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void selectCardType(CreditCardType creditCardType) {
        if (creditCardType == null) {
            throw new IllegalArgumentException("[ERR] Credit card type can't be null!");
        }

        Select select = new Select(findElement(creditCardDropdownSel));
        switch (creditCardType) {
            case VISA:
                select.selectByVisibleText("Visa");
                break;
            case MASTER_CARD:
                select.selectByVisibleText("Master card");
                break;
            case AMEX:
                select.selectByVisibleText("Amex");
                break;
            case DISCOVER:
                select.selectByVisibleText("Discover");
                break;
        }
    }

    public void inputCardHolderName(String value){
        findElement(cardHolderNameSel).sendKeys(value);
    }

    public void inputCardNumber(String value){
        findElement(cardHolderNumSel).sendKeys(value);
    }

    public void inputExpiredMonth(String month){
        Select select = new Select(findElement(expiredMonthDropdownSel));
        select.selectByValue(month);
    }

    public void inputExpiredYear(String year){
        Select select = new Select(findElement(expiredYearDropdownSel));
        select.selectByValue(year);
    }

    public void inputCardCode(String cardCodeVal){
        findElement(cardCodeSel).sendKeys(cardCodeVal);
    }

    public void clickOnContinueBtn(){
        WebElement continueBtn = findElement(continueBtnSel);
        continueBtn.click();
        wait.until(ExpectedConditions.invisibilityOf(continueBtn));
    }

}