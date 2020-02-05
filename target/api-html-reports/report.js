$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/api/gitApi.feature");
formatter.feature({
  "name": "Git api testing",
  "description": "",
  "keyword": "Feature"
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "I access github api",
  "keyword": "Given "
});
formatter.match({
  "location": "api.cucumber.gitApi.i_access_github_api()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Create a gist",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "I create a gist",
  "keyword": "When "
});
formatter.match({
  "location": "api.cucumber.gitApi.i_create_a_gist()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "The gist was created",
  "keyword": "Then "
});
formatter.match({
  "location": "api.cucumber.gitApi.the_gist_was_created()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "It contains correct information",
  "keyword": "And "
});
formatter.match({
  "location": "api.cucumber.gitApi.it_contains_correct_information()"
});
formatter.result({
  "status": "passed"
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "I access github api",
  "keyword": "Given "
});
formatter.match({
  "location": "api.cucumber.gitApi.i_access_github_api()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Retrieve a gist",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "I retrieve a gist",
  "keyword": "When "
});
formatter.match({
  "location": "api.cucumber.gitApi.i_retrieve_a_gist()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "The correct gist is retrieved",
  "keyword": "Then "
});
formatter.match({
  "location": "api.cucumber.gitApi.the_correct_gist_is_retrieved()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "It contains all fields specified in the documentation",
  "keyword": "And "
});
formatter.match({
  "location": "api.cucumber.gitApi.it_contains_all_fields_specified_in_the_documentation()"
});
formatter.result({
  "status": "passed"
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "I access github api",
  "keyword": "Given "
});
formatter.match({
  "location": "api.cucumber.gitApi.i_access_github_api()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Delete a gist",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "I get gist to delete",
  "keyword": "Given "
});
formatter.match({
  "location": "api.cucumber.gitApi.i_get_gist_to_delete()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I delete the gist",
  "keyword": "When "
});
formatter.match({
  "location": "api.cucumber.gitApi.i_delete_the_gist()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "The gist doesn\u0027t exist anymore",
  "keyword": "Then "
});
formatter.match({
  "location": "api.cucumber.gitApi.the_gist_doesn_t_exist_anymore()"
});
formatter.result({
  "status": "passed"
});
});