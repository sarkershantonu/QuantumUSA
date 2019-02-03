package org.quantum.usa.pages.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.quantum.usa.pages.PageBase;

public class HeaderArea extends PageBase {
    public HeaderArea(WebDriver aDriver) {
        super(aDriver);
    }

    @Override
    public void initStaticItems() {

    }

    @FindBy(css = "a[href='https://quantummeditationsociety.org/about/']")
    @CacheLookup
    public WebElement link_about;
    @FindBy(css = "a[href='https://quantummeditationsociety.org/dream/']")
    @CacheLookup
    public WebElement link_achieveYourDreamSeries;
    @FindBy(css = "a[href='https://quantummeditationsociety.org/download-quantum-meditation-books-and-wallpaper/']")
    @CacheLookup
    public WebElement link_download;
    @FindBy(css = "a[href='https://quantummeditationsociety.org/qms-events/']")
    @CacheLookup
    public WebElement link_events;
    @FindBy(css = "a[href='https://quantummeditationsociety.org/healing/']")
    @CacheLookup
    public WebElement link_healingRequest;
    @FindBy(css = "a[href='https://quantummeditationsociety.org/the-easiest-way-to-learn-meditation/']")
    @CacheLookup
    public WebElement link_learnMeditation;

    @FindBy(css = "a[href='https://quantummeditationsociety.org/login/']")
    @CacheLookup
    public WebElement link_login;
    @FindBy(css = "a[href='https://quantummeditationsociety.org/past-events/']")
    @CacheLookup
    public WebElement link_pastEvents;
    @FindBy(css = "a[href='https://quantummeditationsociety.org/events-category/bengali-programs/']")
    @CacheLookup
    public WebElement link_programsInBengali;
    @FindBy(css = "a[href='https://quantummeditationsociety.org/quantum-living/']")
    @CacheLookup
    public WebElement link_quantumLiving1;
    @FindBy(css = "a[href='https://quantummeditationsociety.org/category/quantum-terms/']")
    @CacheLookup
    public WebElement link_quantumTerms;
    @FindBy(css = "a[href='https://quantummeditationsociety.org/register-qg-qpm/']")
    @CacheLookup
    public WebElement link_register;
    @FindBy(css = "a[href='https://quantummeditationsociety.org/stay-healthy-article/']")
    @CacheLookup
    public WebElement link_stayHealthy;

    @FindBy(css = "a[href='https://quantummeditationsociety.org/sadaka-healing/']")
    @CacheLookup
    public WebElement link_sadakaHealing;

}
