package hearder;

import hearder.interfaces.IHeader;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;

public class DigestAuthentication implements IHeader {

    // Apply to Selenium4
    public void setCredentials(WebDriver driver, String username, String password) {

        ((HasAuthentication) driver)
                .register(() -> new UsernameAndPassword(username, password));
    }
}
