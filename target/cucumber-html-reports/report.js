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
  "error_message": "org.openqa.selenium.TimeoutException: Expected condition failed: waiting for visibility of Proxy element for: DefaultElementLocator \u0027By.xpath: //h3[contains(@class,\u0027wt-text-title-03\u0027)]\u0027 (tried for 10 second(s) with 500 milliseconds interval)\r\n\tat org.openqa.selenium.support.ui.WebDriverWait.timeoutException(WebDriverWait.java:95)\r\n\tat org.openqa.selenium.support.ui.FluentWait.until(FluentWait.java:272)\r\n\tat pageObjects.ItemInfoPage.getItemPrice(ItemInfoPage.java:52)\r\n\tat gradle.cucumber.etsy.theMostExpensiveItemIsAddedToTheCart(etsy.java:122)\r\n\tat ✽.The most expensive item is added to the cart(file:///C:/Users/Alexa/IdeaProjects/automation-frontend-java/src/test/resources/cucumber/etsy.feature:9)\r\nCaused by: org.openqa.selenium.NoSuchElementException: Unable to locate element: //h3[contains(@class,\u0027wt-text-title-03\u0027)]\nFor documentation on this error, please visit: https://www.seleniumhq.org/exceptions/no_such_element.html\nBuild info: version: \u00273.141.59\u0027, revision: \u0027e82be7d358\u0027, time: \u00272018-11-14T08:17:03\u0027\nSystem info: host: \u0027LAPTOP-FI93AATQ\u0027, ip: \u0027192.168.0.185\u0027, os.name: \u0027Windows 10\u0027, os.arch: \u0027amd64\u0027, os.version: \u002710.0\u0027, java.version: \u00271.8.0_241\u0027\nDriver info: org.openqa.selenium.firefox.FirefoxDriver\nCapabilities {acceptInsecureCerts: true, browserName: firefox, browserVersion: 72.0.2, javascriptEnabled: true, moz:accessibilityChecks: false, moz:buildID: 20200117190643, moz:geckodriverVersion: 0.26.0, moz:headless: false, moz:processID: 17352, moz:profile: C:\\Users\\Alexa\\AppData\\Loca..., moz:shutdownTimeout: 60000, moz:useNonSpecCompliantPointerOrigin: false, moz:webdriverClick: true, pageLoadStrategy: normal, platform: WINDOWS, platformName: WINDOWS, platformVersion: 10.0, rotatable: false, setWindowRect: true, strictFileInteractability: false, timeouts: {implicit: 0, pageLoad: 300000, script: 30000}, unhandledPromptBehavior: dismiss and notify}\nSession ID: 4c9cdab1-8f14-4691-b25d-f4e72d7a0471\n*** Element info: {Using\u003dxpath, value\u003d//h3[contains(@class,\u0027wt-text-title-03\u0027)]}\r\n\tat sun.reflect.GeneratedConstructorAccessor11.newInstance(Unknown Source)\r\n\tat sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)\r\n\tat java.lang.reflect.Constructor.newInstance(Constructor.java:423)\r\n\tat org.openqa.selenium.remote.http.W3CHttpResponseCodec.createException(W3CHttpResponseCodec.java:187)\r\n\tat org.openqa.selenium.remote.http.W3CHttpResponseCodec.decode(W3CHttpResponseCodec.java:122)\r\n\tat org.openqa.selenium.remote.http.W3CHttpResponseCodec.decode(W3CHttpResponseCodec.java:49)\r\n\tat org.openqa.selenium.remote.HttpCommandExecutor.execute(HttpCommandExecutor.java:158)\r\n\tat org.openqa.selenium.remote.service.DriverCommandExecutor.execute(DriverCommandExecutor.java:83)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:552)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:323)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElementByXPath(RemoteWebDriver.java:428)\r\n\tat org.openqa.selenium.By$ByXPath.findElement(By.java:353)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:315)\r\n\tat org.openqa.selenium.support.pagefactory.DefaultElementLocator.findElement(DefaultElementLocator.java:69)\r\n\tat org.openqa.selenium.support.pagefactory.internal.LocatingElementHandler.invoke(LocatingElementHandler.java:38)\r\n\tat com.sun.proxy.$Proxy13.isDisplayed(Unknown Source)\r\n\tat org.openqa.selenium.support.ui.ExpectedConditions.elementIfVisible(ExpectedConditions.java:314)\r\n\tat org.openqa.selenium.support.ui.ExpectedConditions.access$000(ExpectedConditions.java:43)\r\n\tat org.openqa.selenium.support.ui.ExpectedConditions$10.apply(ExpectedConditions.java:300)\r\n\tat org.openqa.selenium.support.ui.ExpectedConditions$10.apply(ExpectedConditions.java:297)\r\n\tat org.openqa.selenium.support.ui.FluentWait.until(FluentWait.java:249)\r\n\tat pageObjects.ItemInfoPage.getItemPrice(ItemInfoPage.java:52)\r\n\tat gradle.cucumber.etsy.theMostExpensiveItemIsAddedToTheCart(etsy.java:122)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n\tat java.lang.reflect.Method.invoke(Method.java:498)\r\n\tat io.cucumber.java.Invoker.invoke(Invoker.java:27)\r\n\tat io.cucumber.java.JavaStepDefinition.execute(JavaStepDefinition.java:27)\r\n\tat io.cucumber.core.runner.PickleStepDefinitionMatch.runStep(PickleStepDefinitionMatch.java:63)\r\n\tat io.cucumber.core.runner.TestStep.executeStep(TestStep.java:64)\r\n\tat io.cucumber.core.runner.TestStep.run(TestStep.java:49)\r\n\tat io.cucumber.core.runner.PickleStepTestStep.run(PickleStepTestStep.java:46)\r\n\tat io.cucumber.core.runner.TestCase.run(TestCase.java:51)\r\n\tat io.cucumber.core.runner.Runner.runPickle(Runner.java:66)\r\n\tat io.cucumber.core.runtime.Runtime.lambda$run$2(Runtime.java:100)\r\n\tat java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)\r\n\tat java.util.concurrent.FutureTask.run(FutureTask.java:266)\r\n\tat io.cucumber.core.runtime.Runtime$SameThreadExecutorService.execute(Runtime.java:243)\r\n\tat java.util.concurrent.AbstractExecutorService.submit(AbstractExecutorService.java:112)\r\n\tat io.cucumber.core.runtime.Runtime.lambda$run$3(Runtime.java:100)\r\n\tat java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:193)\r\n\tat java.util.stream.SliceOps$1$1.accept(SliceOps.java:204)\r\n\tat java.util.ArrayList$ArrayListSpliterator.tryAdvance(ArrayList.java:1359)\r\n\tat java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:126)\r\n\tat java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:499)\r\n\tat java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:486)\r\n\tat java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)\r\n\tat java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:708)\r\n\tat java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)\r\n\tat java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:499)\r\n\tat io.cucumber.core.runtime.Runtime.run(Runtime.java:101)\r\n\tat io.cucumber.core.cli.Main.run(Main.java:73)\r\n\tat io.cucumber.core.cli.Main.main(Main.java:31)\r\n",
  "status": "failed"
});
formatter.step({
  "name": "I search for \"turntable mat\"",
  "keyword": "When "
});
formatter.match({
  "location": "gradle.cucumber.etsy.i_search_for(java.lang.String)"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "I add an item to the cart",
  "keyword": "And "
});
formatter.match({
  "location": "gradle.cucumber.etsy.i_add_an_item_to_the_cart()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "the cart should contain the correct items",
  "keyword": "Then "
});
formatter.match({
  "location": "gradle.cucumber.etsy.theCartShouldContainTheCorrectItems()"
});
formatter.result({
  "status": "skipped"
});
formatter.embedding("image/png", "embedded0.png", null);
formatter.after({
  "status": "passed"
});
});