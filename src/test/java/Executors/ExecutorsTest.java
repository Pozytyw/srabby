package Executors;

import com.srabby.application.ConsoleRequestEventListener;
import com.srabby.http.common.RequestEventListener;
import com.srabby.http.common.requests.Request;
import com.srabby.http.common.RequestBuilder;
import com.srabby.http.errors.ScrapeErrors;
import org.junit.Assert;
import org.junit.Test;

public class ExecutorsTest {
    private final String url= "https://en.wikipedia.org/wiki/Special:Random";
    private final String badUrl= "";
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
            try {
                request.execute(requestExecutor);
            }catch (ScrapeErrors er){
                er.printStackTrace();
            }

            Assert.assertNotNull(request.getResponse());
        });
    }

    @Test
    public void multiThreadingTest(){
        //create console request observer
        RequestEventListener requestEventListener = new ConsoleRequestEventListener();

        ConcreteExecutorsList.getList().forEach(requestExecutor -> {
            //add 10 request to requestExecutor
            for (int i = 0; i < 5; i++) {
                //create Request
                //add correct request
                requestExecutor.addRequest(
                        new RequestBuilder()
                                .setEventListener(requestEventListener)
                                .setUrl(url)
                                .cssRequest(cssSelector)
                                .build());

                //add request with bad url
                //without console event listener
                requestExecutor.addRequest(
                        new RequestBuilder()
                        .setUrl(badUrl)
                        .cssRequest(cssSelector)
                        .build());

            }
            requestExecutor.executeRequestsSimultaneously();

            //wait for finish all requests
            while (true){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }

                if(!requestExecutor.isRunning())
                    break;
            }
        });
    }
}
