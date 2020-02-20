package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ItemInfoPage {
    WebDriver driver;
    WebDriverWait wait;

    public ItemInfoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//select[contains(@id,'inventory-variation-select-') and not(contains(@id,'inventory-variation-select-quantity'))]")
    private List<WebElement> selections;
    @FindBy(xpath = "//h1[@data-buy-box-listing-title]")
    private WebElement itemDescription;
    @FindBy(xpath = "//div[@data-buy-box-region='price']/p/span")
    private WebElement itemPrice;
    @FindBy(xpath = "//button/div[contains(text(), 'Add to basket')]")
    private WebElement addToCartButton;

    public void populateAllSelections(){
        // Selects first proper option for every dropdown that is available.
        // This is required if item has any other options than quantity.
        try{
            for(WebElement selection : selections){
                new Select(selection).selectByIndex(1);
            }

        } catch(Exception e){
        }
    }
    public String getItemDescription(){
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(itemDescription));
        return itemDescription.getText();
    }

    public String getItemPrice(){
        // Wait for browser to update price element. This is required since selecting some options might change price.
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(itemPrice));
        if(itemPrice.getText().contains("x")){
            new WebDriverWait(driver, 10).until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(itemPrice,"+")));
        }
        return itemPrice.getText();
    }
    public void addItemToCart(){
        // Try-catch is needed to ensure it works even is page is slowly updated after choosing values in dropdown
        try{
            addToCartButton.click();
        }catch(Exception e){
            addToCartButton.click();
        }
    }
}
