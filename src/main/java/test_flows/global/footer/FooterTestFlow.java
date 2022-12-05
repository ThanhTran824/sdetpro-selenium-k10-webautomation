package test_flows.global.footer;

import models.components.global.footer.*;
import models.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FooterTestFlow {

    private final WebDriver driver;

    public FooterTestFlow(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyFooterComponent() {
        BasePage basePage = new BasePage(driver);
        InformationColumnComponent informationColumn = basePage.footerComp().informationColumnComp();
        CustomerServiceColumnComponent customerServiceColumn = basePage.footerComp().customerServiceColumnComp();
        AccountColumnComponent myAccountColumn = basePage.footerComp().accountColumnComp();
        FollowUsColumnComponent followUsColumn = basePage.footerComp().followUsColumnComp();

        verifyInformationColumn(informationColumn);
        verifyCustomerServiceColumn(customerServiceColumn);
        verifyAccountColumn(myAccountColumn);
        verifyFollowUsColumn(followUsColumn);
    }

    private void verifyInformationColumn(FooterColumnComponent footerColumnComponent) {
        List<String> expectedLinkTexts = Arrays.asList(
                "Sitemap",
                "Shipping & Returns",
                "Privacy Notice",
                "Conditions of Use",
                "About us",
                "Contact us"
        );
        List<String> expectedHrefs = Arrays.asList(
                "https://demowebshop.tricentis.com/sitemap",
                "https://demowebshop.tricentis.com/shipping-returns",
                "https://demowebshop.tricentis.com/privacy-policy",
                "https://demowebshop.tricentis.com/conditions-of-use",
                "https://demowebshop.tricentis.com/about-us",
                "https://demowebshop.tricentis.com/contactus"
        );
        verifyFooterColumn(footerColumnComponent, expectedLinkTexts, expectedHrefs);
    }

    private void verifyCustomerServiceColumn(FooterColumnComponent footerColumnComponent) {
        List<String> expectedLinkTexts = Arrays.asList("");
        List<String> expectedHrefs = Arrays.asList("");
        verifyFooterColumn(footerColumnComponent, expectedLinkTexts, expectedHrefs);
    }

    private void verifyAccountColumn(FooterColumnComponent footerColumnComponent) {
        List<String> expectedLinkTexts = Arrays.asList("");
        List<String> expectedHrefs = Arrays.asList("");
        verifyFooterColumn(footerColumnComponent, expectedLinkTexts, expectedHrefs);
    }

    private void verifyFollowUsColumn(FooterColumnComponent footerColumnComponent) {
        List<String> expectedLinkTexts = Arrays.asList("");
        List<String> expectedHrefs = Arrays.asList("");
        verifyFooterColumn(footerColumnComponent, expectedLinkTexts, expectedHrefs);
    }

    private void verifyFooterColumn(
            FooterColumnComponent footerColumnComponent, List<String> expectedLinkTexts, List<String> expectedHrefs) {

        List<String> actualLinkTexts = new ArrayList<>();
        List<String> actualHrefs = new ArrayList<>();

        if (footerColumnComponent.linksElem().isEmpty())
            Assert.fail("[ERR] The column has no item to test");

        for (WebElement link : footerColumnComponent.linksElem()) {
            actualLinkTexts.add(link.getText().trim());
            actualHrefs.add(link.getAttribute("href"));
        }

        if (actualHrefs.isEmpty() || actualLinkTexts.isEmpty())
            Assert.fail("[ERR] Texts or hyperlink is empty in the footer column");

        Assert.assertEquals(actualLinkTexts, expectedLinkTexts, "[ERR] Link text list is mismatched!");
        Assert.assertEquals(actualHrefs, expectedHrefs, "[ERR] Hyperlink list is mismatched!");
    }

}