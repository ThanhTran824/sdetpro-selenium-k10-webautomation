package tests;

import driver.DriverFactory;
import models.components.global.footer.*;
import models.pages.HomePage;
import org.openqa.selenium.WebDriver;
import url.Urls;
import org.testng.annotations.Test;


public class DemoHomePageTest {

    @Test
    public void testFooter() {
        //public static void main(String[] args) {

        WebDriver driver = DriverFactory.getChromeDriver();

        try {

            driver.get(Urls.BASE_URL);
            HomePage homePage = new HomePage(driver);
            FooterComponent footerComp = homePage.footerComp();

            InformationColumn informationColumn = footerComp.informationColumn();
            CustomerServiceColumn customerServiceColumn = footerComp.customerServiceColumn();
            MyAccountColumn myAccountColumn = footerComp.myAccountColumn();
            FollowUsColumn followUsColumn = footerComp.followUsColumn();

            System.out.println(informationColumn.footerElem().getText());
            System.out.println(customerServiceColumn.footerElem().getText());
            System.out.println(myAccountColumn.footerElem().getText());
            System.out.println(followUsColumn.footerElem().getText());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
