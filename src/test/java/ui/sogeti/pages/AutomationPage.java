package ui.sogeti.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AutomationPage extends BasePage {

    @FindBy(css = "div.page-heading")
    public WebElement pageHeading;

    @FindBy(xpath = "//h2[.='Contact us:']")
    public WebElement contactForm;

    @FindBy(xpath = "//input[@id='4ff2ed4d-4861-4914-86eb-87dfa65876d8']")
    public WebElement firstName;

    @FindBy(xpath = "//*[@id='11ce8b49-5298-491a-aebe-d0900d6f49a7']")
    public WebElement lastName;

    @FindBy(xpath = "//*[@id='056d8435-4d06-44f3-896a-d7b0bf4d37b2']")
    public WebElement emailAddress;

    @FindBy(xpath = "//*[@id='755aa064-7be2-432b-b8a2-805b5f4f9384']")
    public WebElement phoneNumber;

    @FindBy(xpath = "//*[@id='703dedb1-a413-4e71-9785-586d609def60']")
    public WebElement companyName;

    @FindBy(xpath = "//select[@id='e74d82fb-949d-40e5-8fd2-4a876319c45a']")
    public WebElement countryDropdown;

    @FindBy(xpath = "//*[@id='88459d00-b812-459a-99e4-5dc6eff2aa19']")
    public WebElement messageTextBox;

    @FindBy(xpath = "//label[.='I agree']")
    public WebElement iAgreeCheckBox;

    @FindBy(id = "b35711ee-b569-48b4-8ec4-6476dbf61ef8")
    public WebElement submitFormBtn;

    @FindBy(xpath = "//div[@class='Form__Status__Message Form__Success__Message']")
    public WebElement thankYouMessage;


}
