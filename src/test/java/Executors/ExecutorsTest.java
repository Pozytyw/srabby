package Executors;

import com.srabby.http.common.ConcurrentRequestExecutor;
import com.srabby.http.exceptions.ScrapeException;
import org.junit.Assert;
import org.junit.Test;

public class ExecutorsTest {

    @Test
    public void singleRequestTest(){
        ConcreteRequestsList.getList().forEach(request -> {
            try {
                request.execute();
            } catch (ScrapeException e) {
                //request set error
                request.setErrorMessage(e.getMessage());
            }
        });
    }

    @Test
    public void multiThreadingTest(){
        ConcurrentRequestExecutor requestExecutor = new ConcurrentRequestExecutor();
        ConcreteRequestsList.getList().forEach(request -> {
            requestExecutor.addRequest(request);
        });

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

        Assert.assertNotNull(requestExecutor.getRequests());
    }
}