package api.zippopotam.stepDef;

import api.zippopotam.apiCalls.ZippoApi;
import api.zippopotam.pojos.ZippoPojo;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.concurrent.TimeUnit;

public class API_StepDefinitions {

    private ZippoPojo zippo;

    @Given("I make a GET request to zippopotam API")
    public void i_make_a_get_request_to_zippopotam_api() {
        ZippoApi.getStuttgart();
    }

    @Then("the status code should be {int} and content type should be {string}")
    public void the_status_code_should_be_and_content_type_should_be(int statusCode, String contentType) {
        int responseStatusCode = ZippoApi.response.getStatusCode();
        String responseContentType = ZippoApi.response.getContentType();

        Assert.assertEquals(statusCode, responseStatusCode);
        System.out.println("responseStatusCode = " + responseStatusCode);
        Assert.assertEquals(contentType, responseContentType);
        System.out.println("responseContentType = " + responseContentType);
    }

    @Then("the response time should be less than {int} second")
    public void the_response_time_should_be_less_than_second(int second) {
        long responseTime = ZippoApi.response.getTimeIn(TimeUnit.MILLISECONDS);
        System.out.println("Response Time = " + responseTime + " milliseconds");
        Assert.assertTrue(responseTime < second * 1000);
    }

    @Then("the country should be {string}")
    public void the_country_should_be(String countryName) {
        String responseCountry = ZippoApi.response.body().as(ZippoPojo.class).getCountry();
        System.out.println("Country = " + responseCountry);
        Assert.assertTrue(responseCountry.equals(countryName));
    }

    @Then("the state should be {string}")
    public void the_state_should_be(String stateName) {
        String responseState = ZippoApi.response.body().as(ZippoPojo.class).getState();
        System.out.println("State = " + responseState);
        Assert.assertTrue(responseState.equals(stateName));
    }

    @Then("the place name for the post code {string} should be {string}")
    public void the_place_name_for_the_post_code_should_be(String expectedPostalCode, String expectedPlaceName) {

        zippo = ZippoApi.response.getBody().as(ZippoPojo.class);
        ZippoPojo.Places specificPlace = null;
        for (ZippoPojo.Places place : zippo.getPlaces()) {
            if (expectedPostalCode.equals(place.getPostCode())) {
                specificPlace = place;
                break;
            }
        }
        Assert.assertEquals(expectedPostalCode, specificPlace.getPostCode());
        Assert.assertEquals(expectedPlaceName, specificPlace.getPlaceName());
    }

    @Given("I make an GET request for country {string} and postal code {string}")
    public void i_make_an_get_request_for_country_and_postal_code(String country, String postalCode) {
        ZippoApi.getCountryAndPostalCode(country, postalCode);
    }

    @Then("the place name for post code {string} should be {string}")
    public void the_place_name_for_post_code_should_be(String expectedPostalCode, String expectedPlaceName) {

        zippo = ZippoApi.response.getBody().as(ZippoPojo.class);
        String placeName = zippo.getPlaces().get(0).getPlaceName();
        String postalCode = zippo.getPostCode();

        if (placeName.equals(expectedPlaceName)) {
            System.out.println(expectedPlaceName + " Postal Code = " + placeName);
        }

        Assert.assertTrue(expectedPostalCode.equals(postalCode));
        Assert.assertTrue(expectedPlaceName.equals(placeName));
    }
}
