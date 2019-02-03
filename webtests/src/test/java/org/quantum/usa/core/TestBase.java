package org.quantum.usa.core;



import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.allure.events.StepFinishedEvent;
import ru.yandex.qatools.allure.events.StepStartedEvent;
import ru.yandex.qatools.allure.experimental.LifecycleListener;


import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;

public class TestBase extends LifecycleListener {
    private Deque<String> names = new LinkedList<>();

    @Override
    public void fire(StepStartedEvent event) {
        names.push(event.getName());
        logger.info(getOffset() + "@Step:" + names.getFirst());
    }

    @Override
    public void fire(StepFinishedEvent event) {
        logger.info(getOffset() + "@Step done " + names.poll());
    }

    private String getOffset() {
        return new String(new char[names.size() == 0 ? 0 : names.size() - 1]).replaceAll("\0", "   ");
    }

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    @Rule
    public TestWatcher testLogs = new LoggingRule(logger);

    protected static WebDriver browser;
    protected static String baseUrl;
    @BeforeClass
    public static void initForAllClasses() throws IOException {
        PropertyLoader.loadProperties();
        browser = Browser.getInstance();
        baseUrl=System.getProperty("app.url");
    }
    @AfterClass
    public static void cleanupClass() {
        Browser.close();
    }
    public static void print(WebElement element){
        System.out.println("TAG " + element.getTagName());
        System.out.println("TXT " + element.getText());
        System.out.println("VAL " + element.getAttribute("value"));
        System.out.println("SIZE " + element.getSize().toString());
        System.out.println(element.toString());
    }
}
