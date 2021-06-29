package com.srabby.http.common;

import com.srabby.http.common.requests.*;
import com.srabby.http.errors.ScrapeErrors;

import java.util.*;
import java.util.stream.Collectors;

public abstract class RequestExecutor {
    private List<RequestThread> requestThreads;
    private RequestObserver requestObserver;

    //amount of concurrently threads
    protected int concurrentlyAmount = 3;
    private int threadIndex = 0;
    private boolean executionStarted = false;

    public RequestExecutor(RequestObserver requestObserver) {
        this.requestObserver = requestObserver;
        this.requestThreads = new LinkedList<>();
    }

    public RequestExecutor(int concurrentlyAmount, RequestObserver requestObserver) {
        this(requestObserver);
        this.concurrentlyAmount = concurrentlyAmount;
    }

    //basic operations
    protected abstract String getTitle();

    //multithreading execution
    public void executeRequestsSimultaneously() {
        executionStarted = true;
        int n = requestThreads.size();

        while (threadIndex < n && threadIndex < concurrentlyAmount){
            requestThreads.get(threadIndex).start();
            threadIndex += 1;
        }
    }

    //request's thread call this method on end of job
    public void requestThreadComplete(RequestThread requestThread){
        if(threadIndex < requestThreads.size()) {
            requestThreads.get(threadIndex).start();
            threadIndex += 1;
        }
    }

    //run to check if execution is complete
    public boolean executionComplete(){
        if(requestThreads.stream().filter(requestThread -> requestThread.isComplete()).count() < requestThreads.size())
            return false;

        //on execution complete
        executionStarted = false;
        return true;
    }


    //execute methods for all type of requests
    public abstract void executeScriptRequest(ScriptRequest request) throws ScrapeErrors;
    public abstract void executeCSSRequest(CSSRequest request) throws ScrapeErrors;
    public abstract void executePostRequest(PostRequest request) throws ScrapeErrors;
    public abstract void executeGetRequest(GetRequest request) throws ScrapeErrors;

    public void addRequest(Request request){
        if(!executionStarted)
            this.requestThreads.add(new RequestThread(this, request));
    }

    //get responses from requests
    public List<Request> getRequests() {
        return requestThreads.parallelStream().map(requestThread -> requestThread.getRequest()).collect(Collectors.toList());
    }

    //getters and setters
    public RequestObserver getRequestObserver() {
        return requestObserver;
    }
}
