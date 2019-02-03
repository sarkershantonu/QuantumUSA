package org.quantum.usa.core;

import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public final class Browser {
    private static WebDriver driver;

    public static void initBrowser() {
        cleanCookieCache();
        nullifyImplicitWait();
        setJSTimeOut(Long.valueOf(System.getProperty("js.wait")));
        setImplicitWait(Long.valueOf(System.getProperty("implicit.wait")));
        setSize();
    }

    public static void setSize() {
        if ("false".equals(System.getProperty("browser.maximize"))) {
            driver.manage().window().setSize(new Dimension(
                    Integer.valueOf(System.getProperty("browser.width")),
                    Integer.valueOf(System.getProperty("browser.height"))));
        } else {
            driver.manage().window().maximize();
        }
    }

    public static void setJSTimeOut(long sec) {
        driver.manage().timeouts().setScriptTimeout(sec, TimeUnit.SECONDS);
    }

    public static void setImplicitWait(long newWaittime_InSeconds) {
        driver.manage().timeouts().implicitlyWait(newWaittime_InSeconds, TimeUnit.SECONDS);
    }

    public static void nullifyImplicitWait() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    public static void resetImplicitWait() {
        driver.manage().timeouts().implicitlyWait(Long.valueOf(System.getProperty("implicit.wait")), TimeUnit.SECONDS);
    }

    private static void cleanCookieCache() {
        driver.manage().getCookies().clear();
        driver.manage().deleteAllCookies();

    }

    public static void close() {
        driver.quit();//this is to make process of browser kill
        // driver.close();
        driver = null;// to avoid closing time of browser by JVM
    }

    public static WebDriver getInstance() {
        if (driver == null) {
            driver = getDefaultBrowser();
        }
        return driver;
    }

    private static WebDriver getDefaultBrowser() {
        return getABrowser(System.getProperty("browser.default"));
    }

    private static WebDriver getABrowser(final String browserName) {
        if ("chrome".equals(browserName)) {
            return initChrome();
        } else if ("firefox".equals(browserName)) {
            return initFirefox();
        } else if ("ie".equals(browserName)) {
            return initIE();
        } else {
            throw new RuntimeException("No Valid Browser Found");
        }
    }

    private static WebDriver initIE() {
        WebDriverManager.iedriver().arch32().setup();

        driver = new InternetExplorerDriver();
        initBrowser();
        return driver;
    }

    private static WebDriver initFirefox() {
        WebDriverManager.firefoxdriver().setup();
        disableDetailLoggingFirefox();
        driver = new FirefoxDriver();
        initBrowser();
        return driver;
    }

    private static void disableDetailLoggingFirefox() {
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");//disable debug logging
    }

    private static WebDriver initChrome() {
        WebDriverManager.chromedriver().setup();
       /* DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
        ;*/
        driver = new ChromeDriver();
        initBrowser();
        return driver;
    }

    private static void clearChromeBrowserData() {
        driver.get("chrome://settings/clearBrowserData");
        driver.findElement(By.id("clearBrowsingDataConfirm")).click();
    }

    public static WebDriverWait setWebDriverWait(long sec) {
        WebDriverWait wait;
        if (Long.valueOf(System.getProperty("page.element.wait")) < sec) {
            wait = new WebDriverWait(driver, sec);
        } else {
            wait = new WebDriverWait(driver, Long.valueOf(System.getProperty("page.element.wait")));
        }
        return wait;
    }

    public static JavascriptExecutor getJs() {
        return (JavascriptExecutor) driver;
    }

    public static void runJs(final String js) {
        ((JavascriptExecutor) driver).executeScript(js);
    }

    public static void runJsAsync(final String js) {
        ((JavascriptExecutor) driver).executeAsyncScript(js);
    }
}
