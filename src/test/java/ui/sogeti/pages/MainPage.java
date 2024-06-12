package ui.sogeti.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends BasePage {

    @FindBy(xpath = "//*[@id='main-menu']//div[@class='wrapper']/span[contains(text(),'Services')]")
    public WebElement servicesLink;

    @FindBy(xpath = "//nav[@id='main-menu']//ul[@class='level0 clearfix']")
    public List<WebElement> headerLinks;

    public WebElement getHeaderLink(String name) {
        return headerLinks.stream().filter(p -> p.getText().contains(name)).findFirst().get();
    }

    @FindBy(css = "div.consent_wrapper")
    public WebElement cookiePop;

    @FindBy(css = "button.acceptCookie")
    public WebElement acceptCookies;

    public void acceptCookies() {
        if (cookiePop.isDisplayed()) {
            acceptCookies.click();
        }
    }

    @FindBy(xpath = "//li[@class='has-children level2 focus-style']/descendant::ul")
    public List<WebElement> servicesList;

    @FindBy(xpath = "(//a[.='Automation'])[1]")
    public WebElement automationLink;

    public String getSelectedElementColor(WebElement element) {
        return element.getCssValue("color");
    }

    @FindBy(xpath = "//div[@class='sprite-header sprite-global-arrowdown']")
    public WebElement worldwideDropdown;

    @FindBy(xpath = "//div[@id='country-list-id' and @class='country-list']/ul")
    public WebElement countries;

    @FindBy(xpath = "//div[@id='country-list-id' and @class='country-list']/ul/li")
    public List<WebElement> countriyList;
}
