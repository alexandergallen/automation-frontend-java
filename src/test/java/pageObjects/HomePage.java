package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;
    public String sortLabelStringPriceAsc = "Sort by: Lowest Price";
    public String sortLabelStringPriceDesc = "Sort by: Highest Price";

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "global-enhancements-search-query")
    private WebElement searchBar;
    @FindBy(xpath = "//button[@value='Search']")
    private WebElement searchButton;
    @FindBy(xpath = "//button[@data-wt-menu-trigger]")
    private WebElement sortDropDown;
    @FindBy(xpath = "//span[@class='wt-menu__trigger__label']")
    private WebElement sortLabel;
    @FindBy(xpath = "//nav[@aria-label='Main navigation']/ul/li//span[@class='etsy-icon']")
    private WebElement cart;
    @FindBy(xpath = "//button[contains(text(), 'Accept')]")
    private WebElement acceptCookiesButton;

    // Sorting options need to be public since they can be passed as parameters to sortItems method
    @FindBy(xpath = "//a[@data-sort-by-price_asc]")
    public WebElement sortDropDownValuePriceAsc;
    @FindBy(xpath = "//a[@data-sort-by-price_desc]")
    public WebElement sortDropDownValuePriceDesc;

    // Search results excluding ads
    @FindBy(xpath = "//ul[contains(@class,'responsive-listing-grid')]/li/div/a[contains(@class, 'organic-impression')]")
    private List<WebElement> searchResultsNoAds;

    public void acceptCookies(){
        // Disables popup if it exists (relevant for Etsy.com)
        try{
            acceptCookiesButton.click();
        } catch(Exception e){
        }
    }
    public void clearSearchBar(){
        searchBar.clear();
    }
    public void enterSearchString(String string){
        searchBar.sendKeys(string);
    }
    public void clickSearchButton(){
        searchButton.click();
    }
    public void openSortDropdown(){
        sortDropDown.click();
    }
    public void sortItems(WebElement sortOption){
        // Open dropdown to choose sorting method
        openSortDropdown();
        // Let the test wait for the dropdown to be expanded and element to be visible until it attempts to click it.
        // Omitting these lines causes the test to sometimes click wrong item in dropdown since it attempts to click while it is still moving
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.attributeToBe(sortDropDown,"aria-expanded","true"));
        wait.until(ExpectedConditions.visibilityOf(sortOption));
        // Click on correct sorting method
        sortOption.click();
    }

    // Method to check if sortingMenu has correct value
    // Added a wait to the check due to it otherwise resulting in very rare flakiness of the test due to the ajax
    // on the webpage and it loading slower than expected.
    // Added more verbose error message for easier debugging.
    public boolean doesSortingLabelContainText(String sortLabelString){
        try{
            new WebDriverWait(driver, 10).
                    until(ExpectedConditions.textToBePresentInElement(
                            sortLabel,
                            sortLabelString));
            return true;
        } catch(Exception e){
            System.out.println("FAILED:\n"+
                    "Expected: "+sortLabelString+
                    "\nGot: "+sortLabel.getText());
            return false;
        }
    }
    // Gets all listed items
    // For this only the first page is checked, it is assumed that the rest are correct if the first page is.
    public boolean checkItemsAreSortedByPriceAsc(){
        double previousPrice = 0;
        boolean checkResult = true;
        for(WebElement result : searchResultsNoAds){
            // Compares price to price of previous item and stores it for next iteration of the loop
            double currentPrice = Double.parseDouble(result.findElement(By.xpath("//span[@class='currency-value']")).getText());
            if(previousPrice>currentPrice){
                checkResult = false;
            }
            previousPrice = currentPrice;
        }
        return checkResult;
    }

    public void clickItem(int index){
        searchResultsNoAds.get(index).click();
    }

    public int getNumberOfSearchResultsWithoutAds(){
        return searchResultsNoAds.size();
    }

    public void openCart(){
        cart.click();
    }
}
