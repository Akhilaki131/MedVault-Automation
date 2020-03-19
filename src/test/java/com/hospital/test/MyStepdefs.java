package com.hospital.test;

import com.hospital.test.model.PatientDetailResponse;
import com.hospital.test.model.PatientDetails;
import com.hospital.test.model.UserDetails;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.junit.Assert;
import org.springframework.http.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Properties;
import java.util.logging.Logger;

public class MyStepdefs {

    Properties environment = new Properties();
    String host = "http://localhost:8800/LoginWebApp";
    String hospitalHost = host + "/doctors";
    ResponseEntity entity;
    private final static Logger log =
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @When("^I click on HealthCheck$")
    public void i_click_on_Hello() throws Throwable {
        String endpoint = host + "/hello";
        String env = environment.getProperty("ENDPOINT_HELLO");
        entity = invokeService(endpoint, HttpMethod.GET, null);
        log.info("HealthCheck response returned " + entity.getStatusCode().toString());

    }

    @Then("^Response should be successful$")
    public void response_should_be_successful() throws Throwable {
        Assert.assertEquals(200, entity.getStatusCodeValue());
    }

    @When("^I click on HelloWorld$")
    public void i_click_on_HealthCheck() throws Throwable {
        String endpoint = host + "/hello";
        String env = environment.getProperty("ENDPOINT_HELLO");
        entity = invokeService(endpoint, HttpMethod.GET, null);
        log.info("HelloWorld response returned " + entity.getStatusCode().toString());

    }

    @Given("^a running application$")
    public void a_running_application() throws Throwable {
        i_click_on_Hello();
        Assert.assertEquals(200, entity.getStatusCodeValue());
    }

    @When("^a user enters \"([^\"]*)\" and \"([^\"]*)\"$")
    public void entersDetails(String username, String password) {
        String endpoint = hospitalHost + "/login";
        String queryParam = "?" + "username=" + username + "&password=" + password;
        String env = environment.getProperty("ENDPOINT_HELLO");
        String uri = endpoint + queryParam;
        try {
            entity = invokeService(uri, HttpMethod.POST, null);
        } catch (HttpServerErrorException e) {
            log.info("Login response failed " + entity.getStatusCode().toString());
        }

        log.info("HealthCheck response returned " + entity.getStatusCode().toString());

    }

    @Then("^login should return \"([^\"]*)\"$")
    public void login_should_return_success(String result) throws Throwable {
        if (result.contains("success")) {
            Assert.assertEquals(200, entity.getStatusCodeValue());
        }
    }

    @When("^a user gives \"([^\"]*)\"$")
    public void a_user_gives(String patientName) throws Throwable {
        String endpoint = hospitalHost + "/patientDetails";
        String queryParam = "?" + "patientName=" + patientName;
        String env = environment.getProperty("ENDPOINT_HELLO");
        String uri = endpoint + queryParam;
        entity = invokeService(uri, HttpMethod.GET, null);
    }

    @Then("^response should give PatientDetails$")
    public void response_should_give_PatientDetails() throws Throwable {
        String responseStr = Objects.requireNonNull(entity.getBody()).toString();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        PatientDetailResponse patientDetailResponse = mapper.readValue(responseStr, PatientDetailResponse.class);
        Assert.assertNotNull(patientDetailResponse);
        Assert.assertEquals(200, entity.getStatusCodeValue());
    }

    @Given("^a user signup with details \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void a_user_signup_with_details(String username, String password, String email) throws Throwable {
        String endpoint = hospitalHost + "/signup";
        UserDetails userDetails = new UserDetails(username, password, email);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        String jsonPayload = mapper.writeValueAsString(userDetails);
        entity = invokeService(endpoint, HttpMethod.POST, jsonPayload);
    }

    @Given("^verify login for \"([^\"]*)\" and \"([^\"]*)\"$")
    public void verify_login(String username, String password) throws Throwable {
        String endpoint = hospitalHost + "/login";
        String queryParam = "?" + "username=" + username + "&password=" + password;
        String env = environment.getProperty("ENDPOINT_HELLO");
        String uri = endpoint + queryParam;
        try {
            entity = invokeService(uri, HttpMethod.POST, null);
        } catch (HttpServerErrorException e) {
            log.info("Login response failed " + entity.getStatusCode().toString());
        }
        log.info("HealthCheck response returned " + entity.getStatusCode().toString());
    }

    @Given("^enter patient details \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and (\\d+) and (\\d+) and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void enter_patient_details(String username, String firstName, String lastName, int age, int phone, String disease, String medications) throws Throwable {
        String endpoint = hospitalHost + "/set/PatientDetails";
        PatientDetails patientDetails = new PatientDetails(username,firstName,lastName,age,phone,disease,medications);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        String jsonPayload = mapper.writeValueAsString(patientDetails);
        entity = invokeService(endpoint, HttpMethod.POST, jsonPayload);
    }


    public ResponseEntity invokeService(String endpointURI, HttpMethod httpMethod, String jsonPayload) {
        ResponseEntity response = null;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(jsonPayload, headers);

        try {
            response = restTemplate.exchange(endpointURI, httpMethod, request, String.class);
        } catch (RuntimeException e) {
            throw e;
        }

        return response;
    }
}
