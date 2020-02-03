# automation-frontend-java

## General

### Pre-requisites
- JDK installed (tested working with 1.8.0_241)

### Common errors
- On ./gradlew task
```
What went wrong:
Execution failed for task ':compileTestJava'.
> Could not find tools.jar. Please check that C:\Program Files\Java\jre1.8.0_231 contains a valid JDK installation.
```
Cause: Gradle is unable to find your JDK installation

**Fix: add a gradle.properties file to project root with path to your jdk**

*Example: org.gradle.java.home=C:\\Program Files\\Java\\jdk1.8.0_162*


## Frontend testing

### Pre-requisites
- Download chromedriver: https://chromedriver.chromium.org/downloads (tested working with ChromeDriver 79.0.3945.36)
  - Take note of location of chromedriver.exe and pass it as command line argument (see below)
### Execution
- (From project root) execute using default Chromedriver path (C:\ChromeDriver\chromedriver.exe):
```
./gradlew cucumber
```
- Using custom Chromedriver path (**Windows users note:** *backslash has to be escaped*):
```
./gradlew cucumber -PchromeDriverPath=C:\\ChromeDriver\\chromedriver.exe
```
### Important files
- Cucumber feature file "etsy.feature" in "src/test/resources/cucumber/"
- Step definition file "etsy.java" in "src/test/java/gradle/cucumber/"
- build.gradle contains all the required dependencies and the task "cucumber" which executes the frontend tests

### Tools
- Gradle
- Selenium webdriver
- Cucumber

## API testing

### Execution
- (From project root) execute:
```
./gradlew api_cucumber -Pusername=exampleuser -Ppassword=examplepassword
```
Please note that your github username and password should be passed as arguments to be able to test githubs api.

### Important files
- Cucumber feature file "gitApi.feature" in "src/test/resources/api/"
- Step definition file "gitApi.java" in "src/test/java/api/cucumber/"
- Json schema git_api_gist.json for validating GET /gists/{id} in "src/test/resources/"
- Post body postBody.json for populating body of POST /gists in "src/test/resources/"
- build.gradle contains all the required dependencies and the task "api_cucumber" which executes the API tests

### Tools
- Gradle
- Rest assured
- Cucumber
