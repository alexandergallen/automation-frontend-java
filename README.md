# automation-frontend-java

## General

### Pre-requisites
- JDK installed (tested working with 1.8.0_241)

## Frontend testing

### Execution
- (From project root) execute:
```
./gradlew cucumber
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
