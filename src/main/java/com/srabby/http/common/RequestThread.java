package com.srabby.http.common;

import com.srabby.http.common.requests.Request;
import com.srabby.http.exceptions.ScrapeException;

public class RequestThread extends Thread{
    private ConcurrentRequestExecutor concurrentRequestExecutor;
    private Request request;
    private boolean terminated = false;
    private int maxRerunCount = 3;
    private int rerunCount = 0;

    RequestThread(ConcurrentRequestExecutor concurrentRequestExecutor, Request request){
        super();
        this.concurrentRequestExecutor = concurrentRequestExecutor;
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
            request.execute();

        }catch (ScrapeException e){
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
