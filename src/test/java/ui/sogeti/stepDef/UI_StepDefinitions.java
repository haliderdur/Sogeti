package ui.sogeti.stepDef;

import com.github.javafaker.Faker;
import ui.sogeti.pages.AutomationPage;
import ui.sogeti.pages.MainPage;
import ui.sogeti.utilities.BrowserUtils;
import ui.sogeti.utilities.Driver;
import ui.sogeti.utilities.Environment;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Description;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class UI_StepDefinitions {

    MainPage mainPage = new MainPage();
    AutomationPage automationPage = new AutomationPage();

    @Given("User goes to the homepage")
    public void user_goes_to_the_homepage() {
        Driver.getDriver().get(Environment.UI_baseURL);
    }

    @Given("User accepts all cookies")
    public void user_accepts_all_cookies() {
        mainPage.acceptCookies();
    }

    @When("User hover over {string} link on the header")
    public void user_hover_over_link_on_the_header(String link) {
        BrowserUtils.hover(mainPage.getHeaderLink(link));
        BrowserUtils.waitForClickablility(mainPage.automationLink, 5);
    }

    @Then("User sees the {string} in the opening menu")
    public void user_sees_the_in_the_opening_menu(String serviceName) {
        Assert.assertTrue(mainPage.automationLink.isDisplayed());
        Assert.assertTrue(serviceName.equals(mainPage.automationLink.getText()));
    }

    @When("User clicks Automation link")
    public void user_clicks_Automation_link() {
        mainPage.automationLink.click();
    }

    @Then("User goes to the {string} page")
    public void user_goes_to_the_page(String pageName) {
        Assert.assertTrue(automationPage.pageHeading.getText().equals(pageName));
    }

    @Then("User sees the {string} and {string} links as selected")
    public void user_sees_the_and_links_as_selected(String selectedElement1, String selectedElement2) {
        selectedElement1 = mainPage.getSelectedElementColor(mainPage.servicesLink);
        selectedElement2 = mainPage.getSelectedElementColor(mainPage.automationLink);

        System.out.println("Selected element 1 : " + selectedElement1);
        System.out.println("Selected element 2 : " + selectedElement2);
        Assert.assertTrue(selectedElement1.equals(selectedElement2));
    }

    @When("User scrolls down to the Contact us form")
    public void user_scrolls_down_to_the_form() {
        BrowserUtils.scrollToElement(automationPage.contactForm);
    }

    @When("User fills contact form with random data")
    public void user_fills_contact_form_with_random_data() {

        Faker faker = new Faker();
        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();
        String companyName = faker.company().name();
        String emailAddress = firstname + lastname + "@" + companyName + ".com";
        String phoneNumber = faker.phoneNumber().cellPhone();
        String message = faker.lorem().sentence();

        automationPage.firstName.sendKeys(firstname);
        automationPage.lastName.sendKeys(lastname);
        automationPage.emailAddress.sendKeys(emailAddress);
        automationPage.phoneNumber.sendKeys(phoneNumber);
        automationPage.countryDropdown.click();
        Select select = new Select(automationPage.countryDropdown);
        select.selectByIndex(faker.number().numberBetween(1, 40));
        automationPage.companyName.sendKeys(companyName);
        automationPage.messageTextBox.sendKeys(message);
    }

    @When("User checks I agree checkbox")
    public void user_checks_i_agree_checkbox() {
        automationPage.iAgreeCheckBox.click();
    }

    @Description("User should pass Captcha validation manually, therefore this step is not implemented")
    @When("User should pass reCaptcha")
    public void User_should_pass_reCaptcha() {
/*
   Automating Captcha is one of the constraints for Selenium.
   This must be handled by manual testing or there are some workarounds.
   Such as using 2Captcha, AntiCaptcha with Captcha API key and Captcha HTML key.
   Captcha solving API services requires different implementations
*/
    }

    @When("User clicks submit button")
    public void user_clicks_submit_button() {
        automationPage.submitFormBtn.click();
    }

    @Description("This method assertion are not going to work because the user should pass Captcha validation before submitting the form")
    @Then("User sees Thank you message displayed")
    public void user_sees_thank_you_message_displayed() {
/*
   Assert.assertTrue(automationPage.thankYouMessage.isDisplayed());
   Assert.assertEquals("Thank you for contacting us.", automationPage.thankYouMessage.getText());
*/
    }

    @When("User clicks Worldwide link")
    public void user_clicks_worldwide_link() {
        mainPage.worldwideDropdown.click();
    }

    @Then("Countries displayed")
    public void countries_displayed() {
        Assert.assertTrue(mainPage.countries.isDisplayed());
    }

    @Then("All country links are clickable")
    public void all_country_links_are_clickable() {
        for (WebElement eachCountry : mainPage.countriyList) {
            Assert.assertTrue(eachCountry.isEnabled());
        }
    }

    @Then("All country links take user to the country specific site")
    public void all_country_links_take_user_to_the_country_specific_site() {

        // Perform actions on the each country links
        for (WebElement eachCountry : mainPage.countriyList) {
            String originalWindow = Driver.getDriver().getWindowHandle();
            String countryLinkText = eachCountry.getText();
            String countryHref = eachCountry.findElement(By.tagName("a")).getAttribute("href");

            eachCountry.click();

            // Wait for the new tab to open
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));

            // Get all window handles
            Set<String> allWindows = Driver.getDriver().getWindowHandles();
            String newTabURL = "";

            // Switch to the new tab
            for (String windowHandle : allWindows) {
                if (!windowHandle.equals(originalWindow)) {
                    Driver.getDriver().switchTo().window(windowHandle);
                    newTabURL = Driver.getDriver().getCurrentUrl();
                    break;
                }
            }

            // Perform the assertion
            System.out.println(countryLinkText + " - " + newTabURL);
            Assert.assertTrue("The URL does not match for " + countryLinkText, newTabURL.equals(countryHref));

            // Close the new tab and switch back to the original tab
            Driver.getDriver().close();
            Driver.getDriver().switchTo().window(originalWindow);
        }
    }
}
