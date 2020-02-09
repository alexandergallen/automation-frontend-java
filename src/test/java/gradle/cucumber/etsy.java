package gradle.cucumber;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.*;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.CartPage;
import pageObjects.HomePage;
import pageObjects.ItemInfoPage;

public class etsy {
    WebDriver driver = null;
    WebDriverWait wait;
    String chromeDriverPath;
    ChromeOptions options = new ChromeOptions();
    List<String> itemInfoList = new ArrayList<>();
    List<String> itemPriceList = new ArrayList<>();

    HomePage homePage;
    CartPage cartPage;
    ItemInfoPage itemInfoPage;

    // Close web driver after each scenario.
    // Take screenshot if scenario failed.
    @After
    public void doSomethingAfter(Scenario scenario){
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }
        driver.quit();
    }

    // Method to open a webpage
    private void openWebpage(String url) {
        // Path to local chromedriver should be modified accoridngly. Please refer to README.
        if(!System.getProperty("chromeDriverPath").isEmpty()){
            chromeDriverPath=System.getProperty("chromeDriverPath");
        }else{
            chromeDriverPath="C:\\ChromeDriver\\chromedriver.exe";
        }
        if(System.getProperty("os.name").equals("Linux")){
            chromeDriverPath="/chromedriver";
            options.addArguments("--headless", "--disable-dev-shm-usage", "--window-size=1920,1080", "--start-maximized", "--ignore-certificate-errors", "--no-sandbox");
        }
        options.addArguments("--headless", "--window-size=1920,1080", "--start-maximized");
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver(options);
        driver.navigate().to(url);
    }

    @Given("I have loaded {string}")
    public void i_have_loaded(String string) {
        openWebpage(string);
        homePage = new HomePage(driver);
        homePage.acceptCookies();
    }

    @When("I search for {string}")
    public void i_search_for(String string) {
        // Clear search bar from previous queries and enter the new search query.
        homePage.clearSearchBar();
        homePage.enterSearchString(string);
        homePage.clickSearchButton();
        // Sometimes the cookies aren't attached properly to the webdriver and the popup re-appears.
        // Re-checking it at this point greatly reduces the flakiness of the test.
        homePage.acceptCookies();
    }

    @When("I sort the results by price ascending")
    public void i_sort_the_results_by_price_ascending() {
        homePage.sortItems(homePage.sortDropDownValuePriceAsc);
    }

    @Then("The items should be sorted accordingly")
    public void the_items_should_be_sorted_accordingly() {
        // Checks that correct sorting was achieved and is displayed
        assertTrue("Wrong sorting option was set",
                homePage.doesSortingLabelContainText(homePage.sortLabelStringPriceAsc));
        // Checks that the items shown are actually sorted correctly
        assertTrue("The items were not sorted correctly",
                homePage.checkItemsAreSortedByPriceAsc());
    }

    @And("The most expensive item is added to the cart")
    public void theMostExpensiveItemIsAddedToTheCart() {
        // Since the search returned more than 250 pages worth of items the price has to be sorted by price desc to get
        // most expensive item
        homePage.sortItems(homePage.sortDropDownValuePriceDesc);
        assertTrue("Wrong sorting option was set",
                homePage.doesSortingLabelContainText(homePage.sortLabelStringPriceDesc));
        // Create handle for multiple tabs
        String winHandleBefore = driver.getWindowHandle();
        homePage.clickItem(0);

        // Switch to the new tab since clicking on item automatically opens new tab
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }

        itemInfoPage = new ItemInfoPage(driver);
        // Populate all selections on item info page
        itemInfoPage.populateAllSelections();
        // Get item info and price and store it into lists that are checked in cart validation.
        // Lists are populated in same order as items are listed in cart for easier readability.
        itemInfoList.add(0, itemInfoPage.getItemDescription());
        itemPriceList.add(0,itemInfoPage.getItemPrice());
        itemInfoPage.addItemToCart();
        // Close newly opened tab and go back to previous one. Easier to follow test and no idle tabs are left open.
        driver.close();
        driver.switchTo().window(winHandleBefore);

    }

    @When("I add an item to the cart")
    public void i_add_an_item_to_the_cart() {
        // Create handle for multiple tabs
        String winHandleBefore = driver.getWindowHandle();
        // Taking "random" item by using default sort (Relevancy) and taking the middle item from
        // the list of items that weren't ads.
        homePage.clickItem(homePage.getNumberOfSearchResultsWithoutAds()/2);

        // Switch to the new tab since clicking on item automatically opens new tab
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
        // Populate all selections on item info page
        itemInfoPage.populateAllSelections();
        // Get item info and price and store it into lists that are checked in cart validation.
        // Lists are populated in same order as items are listed in cart for easier readability.
        itemInfoList.add(0, itemInfoPage.getItemDescription());
        itemPriceList.add(0,itemInfoPage.getItemPrice());
        itemInfoPage.addItemToCart();
        // Close newly opened tab and go back to previous one. Easier to follow test and no idle tabs are left open.
        driver.close();
        driver.switchTo().window(winHandleBefore);
    }

    @Then("the cart should contain the correct items")
    public void theCartShouldContainTheCorrectItems() {
        int i = 0;
        homePage.openCart();
        cartPage = new CartPage(driver);
        // Check that both items exist in cart.
        assertTrue("There are not two items in the cart",
                cartPage.getNumberOfCartItems()==2);
        // Loop through items in cart and validate title/description and price.
        for(WebElement item : cartPage.cartItems){
            assertEquals("Item title is incorrect",
                    cartPage.getCartItemTitle(item),
                    itemInfoList.get(i));
            assertTrue("Item price is incorrect",
                    itemPriceList.get(i).contains(cartPage.getCartItemPrice(item)));
            i++;
        }

    }
}