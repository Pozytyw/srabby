package com.srabby.http.common;

import com.srabby.http.common.requests.Request;
import com.srabby.http.errors.ScrapeErrors;

public class RequestThread extends Thread{
    private RequestExecutor requestExecutor;
    private Request request;
    private boolean terminated = false;
    private boolean complete = false;
    private int maxRerunCount = 3;
    private int rerunCount = 0;

    RequestThread(RequestExecutor requestExecutor, Request request){
        super();
        this.requestExecutor = requestExecutor;
        this.request = request;
    }

    public void setMaxRerunCount(int maxRerunCount) {
        this.maxRerunCount = maxRerunCount;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //stop if terminated
        if(terminated)
            return;

        rerunCount += 1;
        try{
            requestExecutor.getRequestObserver().onStart(request);
            //execute request
            request.execute(requestExecutor);

            //successfully execution
            requestExecutor.getRequestObserver().onComplete(request);

            //remove thread from list
            requestExecutor.requestThreadComplete(this);
            complete = true;

        }catch (ScrapeErrors e){
            //set and show error
            request.setError(true);
            request.setErrorMessage(e.getMessage());
            requestExecutor.getRequestObserver().onError(request);

            //run again if rerun count is smaller than maximum
            //and not terminated
            if(rerunCount <= maxRerunCount && !terminated)
                this.run();
            else
                complete = true;
        }
    }

    //Clear rerun counter and run thread
    public void forceRun(){
        rerunCount = 0;
        complete = false;
        run();
    }

    //terminate
    public void terminate(){
        this.terminated = true;
    }

    //getters

    public boolean isComplete() {
        return complete;
    }

    public Request getRequest() {
        return request;
    }
}
