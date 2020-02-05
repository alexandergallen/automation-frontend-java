$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/cucumber/etsy.feature");
formatter.feature({
  "name": "Etsy search functionality",
  "description": "",
  "keyword": "Feature"
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "I have loaded \"https://www.etsy.com/\"",
  "keyword": "Given "
});
formatter.match({
  "location": "gradle.cucumber.etsy.i_have_loaded(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Sorting search results and validating cart",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "I search for \"Sketchbook\"",
  "keyword": "When "
});
formatter.match({
  "location": "gradle.cucumber.etsy.i_search_for(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I sort the results by price ascending",
  "keyword": "And "
});
formatter.match({
  "location": "gradle.cucumber.etsy.i_sort_the_results_by_price_ascending()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "The items should be sorted accordingly",
  "keyword": "Then "
});
formatter.match({
  "location": "gradle.cucumber.etsy.the_items_should_be_sorted_accordingly()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "The most expensive item is added to the cart",
  "keyword": "And "
});
formatter.match({
  "location": "gradle.cucumber.etsy.theMostExpensiveItemIsAddedToTheCart()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I search for \"turntable mat\"",
  "keyword": "When "
});
formatter.match({
  "location": "gradle.cucumber.etsy.i_search_for(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I add an item to the cart",
  "keyword": "And "
});
formatter.match({
  "location": "gradle.cucumber.etsy.i_add_an_item_to_the_cart()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the cart should contain the correct items",
  "keyword": "Then "
});
formatter.match({
  "location": "gradle.cucumber.etsy.theCartShouldContainTheCorrectItems()"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});