package dependencies;

import org.junit.Assert;
import org.junit.Test;

public class Jsoup {

    @Test
    //assert that jsoup dependency is imported
    public void test(){
        Assert.assertNotNull(org.jsoup.Jsoup.class);
    }
}
