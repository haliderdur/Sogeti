@UI @Sogeti
Feature: Homepage links functionality

  Background: The user has already on the homepage
    Given User goes to the homepage
    And User accepts all cookies

  @TC-01
  Scenario: Hover over the link after selecting to make sure link is selected
    When User hover over "Services" link on the header
    Then User sees the "Automation" in the opening menu
    When User clicks Automation link
    Then User goes to the "Automation" page
    When User hover over "Services" link on the header
    Then User sees the "Services" and "Automation" links as selected

  @TC-02
  Scenario: Automation page contact form test
    When User hover over "Services" link on the header
    When User clicks Automation link
    And User scrolls down to the Contact us form
    And User fills contact form with random data
    And User checks I agree checkbox
    And User should pass reCaptcha
      # Above step was not implemented due to the Captcha. Detailed description is in step definition method
    And User clicks submit button
    Then User sees Thank you message displayed

  @TC-03
  Scenario: Worldwide country specific links are clickable and takes user to the country site
    When User clicks Worldwide link
    Then Countries displayed
    And All country links are clickable
    And All country links take user to the country specific site
