package test.global.footer;

import driver.DriverFactory;

import models.components.product.ProductItemComponent;
import models.pages.HomePage;
import org.openqa.selenium.WebDriver;
import url.Urls;

import java.util.List;

public class FeatureProductTest {

    public static void main(String[] args) {
        /*WebDriver driver = DriverFactory.getChromeDriver();

        try {
            testFeaturedProductHomePage(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();*/
    }

    private static void testFeaturedProductHomePage(WebDriver driver) {
        driver.get(Urls.BASE_URL);
        HomePage homePage = new HomePage(driver);
        List<ProductItemComponent> productItemComps =
                homePage.productGridComp().productItemComps();

        productItemComps.forEach(productItemComp -> {
            System.out.println(productItemComp.productTitleElem().getText());
        });
    }


}