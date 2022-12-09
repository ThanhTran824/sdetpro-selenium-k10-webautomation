package support.ui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SelectEx extends Select {

    private static final String OPTION1 = "Option 2";
    public SelectEx(WebElement element) {
        super(element);
    }

    public void selectOption2(){
        selectByVisibleText(OPTION1);
    }
}
