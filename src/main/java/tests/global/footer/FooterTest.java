package tests.global.footer;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import test.BaseTest;
import test_flows.global.footer.FooterTestFlow;
import url.Urls;

public class FooterTest extends BaseTest {

    @Test
    public void testHomePageFooter() {
        WebDriver driver = getDriver();
        driver.get(Urls.BASE_URL);
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyFooterComponent();
    }

    @Test
    public void testCategoryPageFooter() {
        WebDriver driver = getDriver();
        driver.get(Urls.BASE_URL);
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyFooterComponent();
    }

    @Test
    public void testLoginPageFooter() {
        WebDriver driver = getDriver();
        driver.get(Urls.BASE_URL);
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyFooterComponent();
    }

}