package api_learning;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;

public class PageHelper {

    private JavascriptExecutor jsExecutor;
    private final static String SCROLL_TO_BOTTOM = "window.scrollTo(0, document.body.scrollHeight)";
    private final static String SCROLL_TO_TOP = "window.scrollTo(document.body.scrollHeight, 0)";

    public PageHelper(JavascriptExecutor jsExecutor) {
        this.jsExecutor = jsExecutor;
    }

    protected PageHelper scrollToBottom() {
        jsExecutor.executeScript(SCROLL_TO_BOTTOM);
        return this;
    }

    protected PageHelper scrollToTop() {
        jsExecutor.executeScript(SCROLL_TO_BOTTOM);
        return this;
    }

    public PageHelper removeElement(WebElement toBeRemoveElement) {
        jsExecutor.executeScript("arguments[0].remove()", toBeRemoveElement);
        return this;
    }

    public PageHelper changeElementStyle(WebElement toBeChangeElement){
        jsExecutor.executeScript("arguments[0].setAttribute('style', 'background: blue; border: 4px solid red')", toBeChangeElement);
        return this;
    }
}
