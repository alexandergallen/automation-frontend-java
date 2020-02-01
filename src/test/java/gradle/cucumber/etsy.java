package gradle.cucumber;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.*;
import java.util.*;

import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class etsy {
    WebDriver driver = null;
    WebDriverWait wait;
    List<String> itemInfo = new ArrayList<>();
    List<String> itemPrice = new ArrayList<>();

    // Close chrome after each scenario
    @After
    public void closeChrome(){
        driver.quit();
    }

    // Method to open a webpage
    private void openWebpage(String url) {
        // Path to local chromedriver should be modified accoridngly. Please refer to README.
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to(url);
        // Disables popup if it exists (relevant for Etsy.com)
        try{
            driver.findElement(By.xpath("//button[contains(text(), 'Accept')]")).click();
        } catch(Exception e){
        }
    }

    // Method to sort items on Etsy.com
    // attribute data-sort-by-* should be passed. Example "data-sort-by-price_asc"
    private void sortItems(String menuItemAttribute){
        // Open dropdown to choose sorting method
        driver.findElement(By.xpath("//button[@data-wt-menu-trigger]")).click();

        // Let the test wait for the element to be loaded until it attempts to click it.
        // Omitting these lines causes the test to click wrong item in dropdown since it attempts to click while it is still moving
        wait = new WebDriverWait(driver, 10);
        WebElement menuItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@"+menuItemAttribute+"]")));
        // Click on correct sorting method
        menuItem.click();
    }

    private void addItemToCart(List<WebElement> list, int index){
        // Create handle for multiple tabs
        String winHandleBefore = driver.getWindowHandle();
        // Click on item with index specified in function call
        list.get(index).click();
        // Switch to the new tab since clicking on item automatically opens new tab
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
        // Selects first proper option for every dropdown that is available.
        // This is required if item has any other options than quantity.
        try{
            List<WebElement> selections = driver.findElements(By.xpath("//select[contains(@id,'inventory-variation-select-') and not(contains(@id,'inventory-variation-select-quantity'))]"));
            for(WebElement selection : selections){
                new Select(selection).selectByIndex(1);
            }

        } catch(Exception e){
        }
        // Get item info and store it into lists that are checked in cart validation. Lists are populated in
        // same order as items are listed in cart for easier readability.
        itemInfo.add(0, driver.findElement(By.xpath("//h1[@data-buy-box-listing-title]")).getText());
        // Wait for browser to update price element. This is required since selecting some options might change price.
        if((new WebDriverWait(driver, 10)).until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("//div[@data-buy-box-region='price']/p/span")),"+")))){
            itemPrice.add(0,driver.findElement(By.xpath("//div[@data-buy-box-region='price']/p/span")).getText().split(" ")[1]);
        }
        // Click Add to cart button. This try/catch is required since selecting items in dropdown causes a reload of
        // the "Add button".
        try{
            driver.findElement(By.xpath("//button/div[contains(text(), 'Add to cart')]")).click();
        }catch(Exception e){
            driver.findElement(By.xpath("//button/div[contains(text(), 'Add to cart')]")).click();
        }
        // Close newly opened tab and go back to previous one. Easier to follow test and no idle tabs are left open.
        driver.close();
        driver.switchTo().window(winHandleBefore);
    }

    @Given("I have loaded {string}")
    public void i_have_loaded(String string) {
        openWebpage(string);
    }

    @When("I search for {string}")
    public void i_search_for(String string) {
        // Clear search bar from previous queries and enter the new search query.
        WebElement searchBar = driver.findElement(By.id("global-enhancements-search-query"));
        searchBar.clear();
        searchBar.sendKeys(string);
        // Click on search button
        driver.findElement(By.xpath("//button[@value='Search']")).click();
        // Sometimes the cookies aren't attached properly to the webdriver and the popup re-appears.
        // Re-checking it at this point greatly reduces the flakiness of the test.
        try{
            driver.findElement(By.xpath("//button[contains(text(), 'Accept')]")).click();
        } catch(Exception e){
        }
    }

    @When("I sort the results by price ascending")
    public void i_sort_the_results_by_price_ascending() {
        sortItems("data-sort-by-price_asc");
    }

    @Then("The items should be sorted accordingly")
    public void the_items_should_be_sorted_accordingly() {
        double previousPrice = 0;
        // Checks that correct sorting was achieved and is displayed
        assertEquals("Wrong sorting option was set",
                "Sort by: Lowest Price",
                driver.findElement(By.xpath("//span[@class='wt-menu__trigger__label']")).getText());
        // Checks that the items shown are actually sorted correctly
        // Gets all listed items
        // For this only the first page is checked, it is assumed that the rest are correct if the first page is.
        List<WebElement> items = driver.findElements(By.xpath("//ul[contains(@class,'responsive-listing-grid')]/li/div/a[contains(@class, 'organic-impression')]"));
        for(WebElement item : items){
                // Compares price to price of previous item and stores it for next iteration of the loop
                double currentPrice = Double.parseDouble(item.findElement(By.xpath("//span[@class='currency-value']")).getText());
                assertTrue("The items were not sorted correctly",currentPrice>=previousPrice);
                previousPrice = currentPrice;
        }
    }

    @And("The most expensive item is added to the cart")
    public void theMostExpensiveItemIsAddedToTheCart() {
        // Since the search returned more than 250 pages worth of items the price has to be sorted by price desc to get
        // most expensive item
        sortItems("data-sort-by-price_desc");
        assertEquals("Wrong sorting option was set",
                "Sort by: Highest Price",
                driver.findElement(By.xpath("//span[@class='wt-menu__trigger__label']")).getText());
        // Get list of items and filter out adds using class: organic impression
        List<WebElement> items = driver.findElements(By.xpath("//ul[contains(@class,'responsive-listing-grid')]/li/div/a[contains(@class, 'organic-impression')]"));
        // Add most expensive item to cart
        addItemToCart(items, 0);
    }

    @When("I add an item to the cart")
    public void i_add_an_item_to_the_cart() {
        // Get list of items and filter out adds using class: organic impression
        List<WebElement> items = driver.findElements(By.xpath("//ul[contains(@class,'responsive-listing-grid')]/li/div/a[contains(@class, 'organic-impression')]"));
        // Add an item to cart. Here index: items.size()/2 combined with default sorting (relevancy) is used to
        // achieve variety in items added to cart over multiple test runs.
        addItemToCart(items, items.size()/2);
    }

    @Then("the cart should contain the correct items")
    public void theCartShouldContainTheCorrectItems() {
        int i = 0;
        // Open cart
        driver.findElement(By.xpath("//nav[@aria-label='Main navigation']/ul/li//span[@class='etsy-icon']")).click();
        // Get items in cart
        List<WebElement> items = driver.findElements(By.xpath("//ul[contains(@class, 'cart-listing-list')]"));
        // Check that both items exist in cart.
        assertTrue("There are not two items in the cart",
                items.size()==2);
        // Loop through items in cart and validate title/description and price.
        for(WebElement item : items){
            assertEquals("Item title is incorrect",
                    item.findElement(By.xpath(".//p[@data-listing-title-wrapper]/a")).getText(),
                    itemInfo.get(i));
            assertEquals("Item price is incorrect",
                    item.findElement(By.xpath(".//span[@class='currency-value']")).getAttribute("innerText"),
                    itemPrice.get(i));
            i++;
        }

    }
}