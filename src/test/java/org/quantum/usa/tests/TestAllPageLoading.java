package org.quantum.usa.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.quantum.usa.core.TestBase;
import org.quantum.usa.pages.AboutPage;
import org.quantum.usa.pages.HomePage;
import org.quantum.usa.pages.PageBase;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestAllPageLoading extends TestBase {

    @Parameterized.Parameter(value = 0)
    public Class testClass;

    @Parameterized.Parameters(name = "TEST : {0}")
    public static Collection testClasses(){
        return Arrays.asList(new Class[]{HomePage.class, AboutPage.class});
    }

    @Test
    public void testPageLoad() throws IllegalAccessException, InstantiationException {
        PageBase base = (PageBase) testClass.newInstance();
        base.navigate(baseUrl);
        base.verifyPageLoaded();
        Assert.assertEquals("Title Miss match:",base.title,base.getTitle());
    }
}
