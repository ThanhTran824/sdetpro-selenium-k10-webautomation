package hearder.interfaces;

import org.openqa.selenium.WebDriver;

public interface IHeader {
    void setCredentials(WebDriver driver, String username, String password);
}
