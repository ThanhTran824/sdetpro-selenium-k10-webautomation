package test_flows.computer;

import io.qameta.allure.Step;
import models.components.cart.CartItemRowComponent;
import models.components.cart.TotalComponent;
import models.components.checkout.BillingAddressComponent;
import models.components.checkout.PaymentInformationComponent;
import models.components.checkout.PaymentMethodComponent;
import models.components.checkout.ShippingMethodComponent;
import models.components.order.ComputerEssentialComponent;
import models.pages.*;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import test_data.CreditCardType;
import test_data.DataObjectBuilder;
import test_data.PaymentMethodType;
import test_data.computer.ComputerData;
import test_data.user.UserDataObject;

import java.security.SecureRandom;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderComputerFlow<T extends ComputerEssentialComponent> {

    private final WebDriver driver;
    private final Class<T> computerEssentialComponent;
    private ComputerData computerData;
    private int quantity = 1;
    private double totalItemPrice;
    private UserDataObject defaultCheckoutUser;
    private PaymentMethodType paymentMethod;
    private CreditCardType creditCardType;
    private String browserName;

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComponent, ComputerData computerData) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
        this.computerData = computerData;
        Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
        browserName = caps.getBrowserName();
    }

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComponent, ComputerData computerData, int quantity) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
        this.computerData = computerData;
        this.quantity = quantity;
        Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
        browserName = caps.getBrowserName();
    }

    @Step("Build computer spec and add to cart")
    public void buildComputerSpecAndAddToCart() {
        ComputerItemDetailsPage computerItemDetailsPage = new ComputerItemDetailsPage(driver);
        T computerEssentialComp = computerItemDetailsPage.computerComp(computerEssentialComponent);

        // Unselect all default options
        computerEssentialComp.unselectAllDefaultOptions();

        String processorFullStr = computerEssentialComp.selectProcessorType(computerData.getProcessorType());
        double processorAddedPrice = extractAdditionalPrice(processorFullStr);
        String ramFullStr = computerEssentialComp.selectRAMType(computerData.getRam());
        double ramAddedPrice = extractAdditionalPrice(ramFullStr);
        String fullHDDStr = computerEssentialComp.selectHDD(computerData.getHdd());
        double hddAddedPrice = extractAdditionalPrice(fullHDDStr);

        double osAddedPrice = 0;
        if (computerData.getOs() != null) {
            String osFullStr = computerEssentialComp.selectOS(computerData.getOs());
            osAddedPrice = extractAdditionalPrice(osFullStr);
        }

        if (this.quantity != 1) {
            computerEssentialComp.inputQuantity(this.quantity);
        }

        double totalAddedPrice = processorAddedPrice + ramAddedPrice + hddAddedPrice + osAddedPrice;
        totalItemPrice = (computerEssentialComp.basePrice() + totalAddedPrice) * this.quantity;

        // Add to cart
        computerEssentialComp.clickOnAddToCartBtn();
        computerItemDetailsPage.barNotificationComp().waitUntilItemAddedToCart();
        computerItemDetailsPage.barNotificationComp().clickOnCloseBtn();

        // Navigate to shopping cart
        computerItemDetailsPage.headerComp().clickOnShoppingCartLink();
    }

    private double extractAdditionalPrice(String itemStr) {
        double price = 0;
        int factor = 1;
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(itemStr);
        if (matcher.find()) {
            String priceStr = matcher.group(1);
            if (priceStr.startsWith("-")) factor = -1;
            price = Double.parseDouble(matcher.group(1).replaceAll("[+-]", ""));
        }
        return price * factor;
    }

    @Step("Verify shopping cart")
    public void verifyShoppingCartPage() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        List<CartItemRowComponent> cartItemRowCompList = shoppingCartPage.cartItemRowCompList();
        if (cartItemRowCompList.isEmpty()) {
            Assert.fail("[ERR] There is no item displayed in the shopping cart");
        }

        double allSubTotal = 0;
        for (CartItemRowComponent cartItemRowComp : cartItemRowCompList) {
            double currentSubtotal = cartItemRowComp.subTotal();
            double expectedSubTotal = cartItemRowComp.quantity() * cartItemRowComp.unitPrice();
            Assert.assertEquals(currentSubtotal, expectedSubTotal, "[ERR] The subtotal on the item is incorrect");
            allSubTotal = allSubTotal + currentSubtotal;
        }
        TotalComponent totalComp = shoppingCartPage.totalComp();
        Map<String, Double> priceCategories = totalComp.priceCategories();
        double checkoutSubTotal = 0;
        double checkoutOtherFeesTotal = 0;
        double checkoutTotal = 0;
        for (String priceType : priceCategories.keySet()) {
            double priceValue = priceCategories.get(priceType);
            if (priceType.startsWith("Sub-Total")) {
                checkoutSubTotal = priceValue;
            } else if (priceType.startsWith("Total")) {
                checkoutTotal = priceValue;
            } else {
                checkoutOtherFeesTotal = checkoutOtherFeesTotal + priceValue;
            }
        }

        Assert.assertEquals(allSubTotal, checkoutSubTotal, "[ERR] Checking out Subtotal value is incorrect");
        Assert.assertEquals((checkoutSubTotal + checkoutOtherFeesTotal), checkoutTotal, "[ERR] Checking out Total value is incorrect");

    }

    @Step("Agree TOS and checkout")
    public void agreeTOSAndCheckOut() {
        if (browserName.equals("safari")) {
            try {
                Thread.sleep(3000);
            } catch (Exception ignored) {}
        }
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.totalComp().agreeTOS();
        shoppingCartPage.totalComp().clickOnCheckOutBtn();
        new CheckoutOptionsPage(driver).checkoutAsGuest();
    }

    @Step("Input Billing address")
    public void inputBillingAddress() {
        if (browserName.equals("safari")) {
            try {
                Thread.sleep(3000);
            } catch (Exception ignored) {}
        }
        String defaultCheckoutUserDataFileLoc = "/src/main/java/test_data/user/DefaultCheckoutUser.json";
        defaultCheckoutUser = DataObjectBuilder.buildDataObjectFrom(defaultCheckoutUserDataFileLoc, UserDataObject.class);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        BillingAddressComponent billingAddressComp = checkoutPage.billingAddressComp();
        billingAddressComp.selectInputNewAddress();
        billingAddressComp.inputFirstname(defaultCheckoutUser.getFirstName());
        billingAddressComp.inputLastName(defaultCheckoutUser.getLastName());
        billingAddressComp.inputEmail(defaultCheckoutUser.getEmail());
        billingAddressComp.selectCountry(defaultCheckoutUser.getCountry());
        billingAddressComp.selectState(defaultCheckoutUser.getState());
        billingAddressComp.inputCity(defaultCheckoutUser.getCity());
        billingAddressComp.inputAdd1(defaultCheckoutUser.getAdd1());
        billingAddressComp.inputZIPCode(defaultCheckoutUser.getZipCode());
        billingAddressComp.inputPhoneNum(defaultCheckoutUser.getPhoneNum());
        billingAddressComp.clickOnContinueBtn();
    }

    @Step("Input shipping address")
    public void inputShippingAddress() {
        if (browserName.equals("safari")) {
            try {
                Thread.sleep(3000);
            } catch (Exception ignored) {}
        }
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.shippingAddressComp().clickOnContinueBtn();
    }

    @Step("Select shipping method")
    public void selectShippingMethod() {
        if (browserName.equals("safari")) {
            try {
                Thread.sleep(3000);
            } catch (Exception ignored) {}
        }
        List<String> shippingMethods = Arrays.asList("Ground", "Next Day Air", "2nd Day Air");
        int randomElemIndex = new SecureRandom().nextInt(shippingMethods.size());
        String randomMethod = shippingMethods.get(randomElemIndex);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        ShippingMethodComponent shippingMethodComp = checkoutPage.shippingMethodComp();
        shippingMethodComp.selectShippingMethod(randomMethod);
        shippingMethodComp.clickOnContinueBtn();
    }

    public void selectPaymentMethod() {
        this.paymentMethod = PaymentMethodType.COD;
    }

    @Step("Select payment method")
    public void selectPaymentMethod(PaymentMethodType paymentMethod) {
        if (browserName.equals("safari")) {
            try {
                Thread.sleep(3000);
            } catch (Exception ignored) {}
        }
        if (paymentMethod == null) {
            throw new IllegalArgumentException("[ERR] Payment method can't be null!");
        }
        this.paymentMethod = paymentMethod;
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        PaymentMethodComponent paymentMethodComponent = checkoutPage.paymentMethodComp();
        switch (paymentMethod) {
            case CHECK_MONEY_ORDER:
                paymentMethodComponent.selectCheckMoneyOrderMethod();
                break;
            case CREDIT_CARD:
                paymentMethodComponent.selectCreditCardMethod();
                break;
            case PURCHASE_ORDER:
                paymentMethodComponent.selectPurchaseOrderMethod();
                break;
            default:
                paymentMethodComponent.selectCODMethod();
        }

        paymentMethodComponent.clickOnContinueBtn();
    }

    @Step("Input payment info")
    // https://www.paypalobjects.com/en_GB/vhelp/paypalmanager_help/credit_card_numbers.htm
    public void inputPaymentInfo(CreditCardType creditCardType) {
        if (browserName.equals("safari")) {
            try {
                Thread.sleep(3000);
            } catch (Exception ignored) {}
        }
        if (creditCardType == null) {
            throw new IllegalArgumentException("[ERR] Credit card type can't be null!");
        }
        this.creditCardType = creditCardType;
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        PaymentInformationComponent paymentInformationComp = checkoutPage.paymentInformationComp();
        paymentInformationComp.selectCardType(creditCardType);
        String cardHolderFirstName = defaultCheckoutUser.getFirstName();
        String cardHolderLastName = defaultCheckoutUser.getLastName();
        paymentInformationComp.inputCardHolderName(cardHolderFirstName + " " + cardHolderLastName);
        String cardNum = creditCardType.equals(CreditCardType.VISA) ? "4012888888881881" : "6011000990139424";
        paymentInformationComp.inputCardNumber(cardNum);

        // Select current month and next year
        Calendar calendar = new GregorianCalendar();
        paymentInformationComp.inputExpiredMonth(String.valueOf(calendar.get(Calendar.MONTH) + 1));
        paymentInformationComp.inputExpiredYear(String.valueOf(calendar.get(Calendar.YEAR) + 1));

        paymentInformationComp.inputCardCode("123");
        paymentInformationComp.clickOnContinueBtn();
    }

    @Step("Confirm and verify the order")
    public void confirmOrder() {
        if (browserName.equals("safari")) {
            try {
                Thread.sleep(3000);
            } catch (Exception ignored) {}
        }
        // TODO: Add verification methods
        new CheckoutPage(driver).confirmOrderComp().clickOnContinueBtn();
        new CheckoutCompletedPage(driver).clickOnContinueBtn();
    }

}