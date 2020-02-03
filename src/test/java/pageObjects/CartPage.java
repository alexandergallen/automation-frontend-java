package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CartPage {
    WebDriver driver;
    WebDriverWait wait;

    public CartPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//ul[contains(@class, 'cart-listing-list')]")
    public List<WebElement> cartItems;

    public int getNumberOfCartItems(){
        return cartItems.size();
    }

    public String getCartItemTitle(WebElement element){
        return element.findElement(By.xpath(".//p[@data-listing-title-wrapper]/a")).getText();
    }

    public String getCartItemPrice(WebElement element) {
        return element.findElement(By.xpath(".//span[@class='currency-value']")).getAttribute("innerText");
    }
}
