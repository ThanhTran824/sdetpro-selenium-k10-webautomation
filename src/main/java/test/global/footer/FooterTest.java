package test.global.footer;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.BaseTest;
import test_flows.global.FooterTestFlow;
import url.Urls;

public class FooterTest extends BaseTest {

    @Test()
    public void testFooterCategoryPage() {
        WebDriver driver = getDriver();
        driver.get(Urls.BASE_URL);
        Assert.fail("Demo taking screenshot when test is failed!");
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyProductCatFooterComponent();
    }

    @Test()
    public void testFooterHomePage() {
        WebDriver driver = getDriver();
        driver.get(Urls.BASE_URL);
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyFooterComponent();
    }

}