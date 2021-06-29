package Executors;

import com.srabby.http.common.requests.Request;
import com.srabby.http.common.RequestBuilder;
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
                    .setUrl(url)
                    .cssRequest(cssSelector)
                    .build();

            //execute request
            request.execute(requestExecutor);

            Assert.assertNotNull(request.getResponse());
        });
    }

    @Test
    public void multiThreadingTest(){
        ConcreteExecutorsList.getList().forEach(requestExecutor -> {
            //add 10 request to requestExecutor
            for (int i = 0; i < 10; i++) {
                //create Request
                requestExecutor.addRequest(
                        new RequestBuilder()
                                .setUrl(url)
                                .cssRequest(cssSelector)
                                .build());
            }


            requestExecutor.executeRequestsSimultaneously();
            int i = 0;
            while (!requestExecutor.executionComplete()){
                i += 1;
                i -= 1;
            }
        });

    }
}
