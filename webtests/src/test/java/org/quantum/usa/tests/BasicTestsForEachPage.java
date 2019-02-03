package org.quantum.usa.tests;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.quantum.usa.core.TestBase;
import org.quantum.usa.pages.HomePage;

public class BasicTestsForEachPage extends TestBase {

    private static HomePage home;
    private static AboutPage aboutPage;

    @BeforeClass
    public static void initAllTests() {

    }

    @Test
    public void test_Home_Page_Loaded() {
        home = new HomePage(browser);
        home.navigate();
        home.verifyPageLoaded();
        Assert.assertEquals("TITLE", home.title, home.getTitle());
    }
    @Test
    public void test_About_Page_Loaded() {
        aboutPage = new AboutPage(browser);
        aboutPage.navigate();
        aboutPage.verifyPageLoaded();
        Assert.assertEquals("TITLE", aboutPage.title, aboutPage.getTitle());
    }

    @Test
    public void test_all_link_click_loads_page() {
        home = new HomePage(browser);
        home.navigate();
        home.verifyPageLoaded();
        Assert.assertEquals("TITLE", home.title, home.getTitle());
        aboutPage = new AboutPage(home.click(home.headerArea.link_about).driver);
        Assert.assertEquals("TITLE", aboutPage.title, aboutPage.getTitle());
    }
}
