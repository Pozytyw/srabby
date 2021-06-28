package Executors;

import com.srabby.http.common.requests.HttpMethod;
import com.srabby.http.common.requests.Request;
import com.srabby.http.common.requests.RequestBuilder;
import org.junit.Assert;
import org.junit.Test;

public class ExecutorsTest {
    private final String url= "https://en.wikipedia.org/wiki/Special:Random";
    private final String cssSelector= "#firstHeading";

    @Test
    public void singleRequestTest(){
        ConcreteExecutorsList.getList().forEach(requestExecutor -> {
            //create Request
            Request request = new RequestBuilder()
                    .setHttpMethod(HttpMethod.GET)
                    .setUrl(url)
                    .cssRequest(cssSelector)
                    .build();

            //execute request
            request.execute(requestExecutor);

            Assert.assertNotNull(request.getResponse());
        });
    }
}
