package com.srabby.http.common;

import com.srabby.http.common.requests.Request;
import com.srabby.http.errors.ScrapeErrors;

public class RequestThread extends Thread{
    private RequestExecutor requestExecutor;
    private Request request;
    private boolean terminated = false;
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
        //stop if terminated
        if(terminated)
            return;

        rerunCount += 1;
        try{
            //execute request
            request.execute(requestExecutor);

        }catch (ScrapeErrors e){
            //request set error
            request.setErrorMessage(e.getMessage());

            //run again if rerun count is smaller than maximum
            //and not terminated
            if(rerunCount <= maxRerunCount && !terminated)
                this.run();
        }
    }

    //Clear rerun counter and run thread
    public void forceRun(){
        rerunCount = 0;
        run();
    }

    //terminate
    public void terminate(){
        this.terminated = true;
    }

    //getters
    public Request getRequest() {
        return request;
    }
}
