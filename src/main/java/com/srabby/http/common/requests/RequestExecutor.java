package com.srabby.http.common.requests;

import com.srabby.http.errors.ConnectionException;
import com.srabby.http.errors.ScrapeErrors;

import java.util.HashSet;
import java.util.Set;

public abstract class RequestExecutor {
    private Set<Request> requests;
    //amount of concurrently threads
    private int concurrentlyAmount;

    public RequestExecutor(int concurrentlyAmount) {
        this.concurrentlyAmount = concurrentlyAmount;
    }

    public RequestExecutor() {
    }

    //basic operations
    protected abstract String getTitle();

    //execute methods for all type of requests
    public abstract void executeScriptRequest(ScriptRequest request) throws ScrapeErrors;
    public abstract void executeCSSRequest(CSSRequest request) throws ScrapeErrors;
    public abstract void executePostRequest(PostRequest request) throws ScrapeErrors;
    public abstract void executeGetRequest(GetRequest request) throws ScrapeErrors;

    public void addRequest(Request request){
        if(this.requests == null){
            this.requests = new HashSet<>();
        }

        this.requests.add(request);
    }
}
