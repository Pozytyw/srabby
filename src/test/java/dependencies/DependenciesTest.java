package dependencies;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class DependenciesTest {

    @Test
    //assert that jsoup dependency is imported
    public void jsoup(){
        Assert.assertNotNull(org.jsoup.Jsoup.class);
    }

    @Test
    //assert that jsoup dependency is imported
    public void selenium(){
        Assert.assertNotNull(WebDriver.class);
    }
}
