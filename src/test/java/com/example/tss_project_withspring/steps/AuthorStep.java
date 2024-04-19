package com.example.tss_project_withspring.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestTemplate;
import com.example.tss_project_withspring.util.HeaderSetup;
import com.example.tss_project_withspring.util.ResponseErrorHandler;
import com.example.tss_project_withspring.util.ResponseResults;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@CucumberContextConfiguration
@SpringBootTest()
public class AuthorStep {

    public static ResponseResults latestResponse = null;

    @Autowired
    protected RestTemplate restTemplate;

    @Given("^the client calls /authors")
    public void the_client_issues_GET_authors() {
        executeGet("http://localhost:8083/authors");
    }

    @Then("^the client receives for /authors status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        final HttpStatusCode currentStatusCode = latestResponse.getTheResponse().getStatusCode();
        assertThat("status code is incorrect : " + latestResponse.getBody(), currentStatusCode.value(), is(statusCode));
    }

    @And("^the client receives a response in JSON format with author \"(.+)\"$")
    public void the_client_receives_json_response_with_author(String authorName) throws JSONException {
        String latestResponseBody = latestResponse.getBody();
        JSONArray jsonArray = new JSONArray(latestResponseBody);
        boolean foundAuthor = false;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (jsonObject.getString("name").equals(authorName)) {
                foundAuthor = true;
                break;
            }
        }
        assertThat("Response JSON does not contain author: " + authorName, foundAuthor, is(true));
    }

    public void executeGet(String url) {
        final Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        final HeaderSetup requestCallback = new HeaderSetup(headers);
        final ResponseErrorHandler errorHandler = new ResponseErrorHandler();

        restTemplate.setErrorHandler(errorHandler);
        latestResponse = restTemplate.execute(url, HttpMethod.GET, requestCallback, response -> {
            if (errorHandler.getHadError()) {
                return (errorHandler.getResults());
            } else {
                return (new ResponseResults(response));
            }
        });
    }
}

