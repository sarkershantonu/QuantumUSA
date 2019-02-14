package org.quantum.usa.pages;

import org.openqa.selenium.WebDriver;
import org.quantum.usa.pages.PageBase;
import org.quantum.usa.pages.common.FooterArea;
import org.quantum.usa.pages.common.HeaderArea;

public class AboutPage extends PageBase {
    public AboutPage(WebDriver aDriver) {
        super(aDriver);
        //initElement();
        initStaticItems();
    }
    @Override
    public void initStaticItems() {
        this.pageUrl = "/about/";
        this.pageLoadedText = "Our mission is to help every human being fulfill his true potential by awakening his inner power";
        headerArea = new HeaderArea(this.driver);
        footerArea = new FooterArea(this.driver);
        this.title="About - Quantum Meditation Society USA (QMS-USA)";
    }

    public AboutPage() {
        super();
        //initElement();
        initStaticItems();
    }
}
