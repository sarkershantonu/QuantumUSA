package org.quantum.usa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.quantum.usa.core.Browser;
import org.quantum.usa.pages.common.FooterArea;
import org.quantum.usa.pages.common.HeaderArea;
import ru.yandex.qatools.allure.annotations.Step;

public abstract class PageBase {
    public String name;
    public FooterArea footerArea;
    public HeaderArea headerArea;
    public int timeout = 15;

    public PageBase(){
        driver = Browser.getInstance();
        initElement(this);
    }
    public String title;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return pageUrl;
    }

    public void setUrl(String url) {
        this.pageUrl = url;
    }

    public String pageUrl;
    public String pageLoadedText;
    public WebDriver driver;

    public PageBase(WebDriver aDriver) {
        this.driver = aDriver;
         initElement(this);
        //init footer
    }
    @Step
    public abstract void initStaticItems();
    @Step
    public <T extends PageBase> void initElement(T t) {
        PageFactory.initElements(driver, t);
    }
    @Step
    public void initElement() {
        PageFactory.initElements(driver, this);
        initElement(headerArea);
        initElement(footerArea);
    }

    @Step
    public PageBase verifyPageLoaded() {
        (new WebDriverWait(driver, timeout)).until(
                new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return d.getPageSource().contains(pageLoadedText);
                    }
                });
        return this;
    }

    @Step
    public PageBase verifyPageUrl() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getCurrentUrl().contains(pageUrl);
            }
        });
        return this;
    }

    @Step
    public String getTitle() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return driver.getTitle();
    }

    @Step
    public PageBase click(WebElement element) {
        element.click();
        return this;
    }

    @Step
    public PageBase type(WebElement element, String text) {
        element.click();
        element.clear();
        element.sendKeys(text);

        return this;
    }

    @Step
    public void clickByJS(WebElement aWebElement) {
        String script = "";//todo
        Browser.getJs().executeAsyncScript(script);

    }
    @Step
    public  void navigate(){
        driver.get(System.getProperty("app.url")+pageUrl);
    }
}
