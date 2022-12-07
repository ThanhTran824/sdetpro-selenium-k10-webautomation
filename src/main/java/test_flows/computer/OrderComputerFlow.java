package test_flows.computer;

import models.components.cart.CartItemRowComponent;
import models.components.cart.TotalComponent;
import models.components.order.ComputerEssentialComponent;
import models.pages.ComputerItemDetailsPage;
import models.pages.ShoppingCartPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import test_data.computer.ComputerData;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderComputerFlow<T extends ComputerEssentialComponent> {

    private final WebDriver driver;
    private final Class<T> computerEssentialComponent;
    private ComputerData computerData;
    private int quantity = 1;
    private double totalItemPrice;

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComponent, ComputerData computerData) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
        this.computerData = computerData;
    }

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComponent, ComputerData computerData, int quantity) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
        this.computerData = computerData;
        this.quantity = quantity;
    }

    public void buildComputerSpecAndAddToCart(){
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
        if(computerData.getOs() != null){
            String osFullStr = computerEssentialComp.selectOS(computerData.getOs());
            osAddedPrice = extractAdditionalPrice(osFullStr);
        }

        if(this.quantity != 1){
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

    private double extractAdditionalPrice(String itemStr){
        double price = 0;
        int factor = 1;
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(itemStr);
        if(matcher.find()){
            String priceStr = matcher.group(1);
            if(priceStr.startsWith("-")) factor = -1;
            price = Double.parseDouble(matcher.group(1).replaceAll("[+-]", ""));
        }
        return price * factor;
    }

    public void verifyShoppingCartPage(){
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        List<CartItemRowComponent> cartItemRowCompList = shoppingCartPage.cartItemRowCompList();
        if(cartItemRowCompList.isEmpty()){
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
            if(priceType.startsWith("Sub-Total")){
                checkoutSubTotal = priceValue;
            } else if(priceType.startsWith("Total")){
                checkoutTotal = priceValue;
            } else {
                checkoutOtherFeesTotal = checkoutOtherFeesTotal + priceValue;
            }
        }

        SoftAssert softAssert = new SoftAssert();
        Assert.assertEquals(allSubTotal, checkoutSubTotal, "[ERR] Checking out Subtotal value is incorrect");
        Assert.assertEquals((checkoutSubTotal + checkoutOtherFeesTotal), checkoutTotal, "[ERR] Checking out Total value is incorrect");
        softAssert.assertAll();
    }

}