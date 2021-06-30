package com.srabby.http.common;

import com.srabby.http.common.requests.*;

import java.util.*;
import java.util.stream.Collectors;

public class ConcurrentRequestExecutor implements RequestEventListener{
    private List<RequestThread> requestThreads;

    //amount of concurrently threads
    protected int concurrentlyAmount = 3;
    private int threadIndex = 0;
    private int started = 0;
    private int finished = 0;
    private Boolean running = false;

    public ConcurrentRequestExecutor() {
        this.requestThreads = new LinkedList<>();
    }

    public ConcurrentRequestExecutor(int concurrentlyAmount) {
        this();
        this.concurrentlyAmount = concurrentlyAmount;
    }

    //multithreading execution
    public void executeRequestsSimultaneously() {
        //restart counters
        started = 0;
        finished = 0;

        //run
        running = true;

        //start requestThreads as much as concurrentlyAmount
        int n = requestThreads.size();
        while (threadIndex < n && threadIndex < concurrentlyAmount){
            requestThreads.get(threadIndex).start();
            threadIndex += 1;
        }
    }

    public void addRequest(Request request){
        request.addEventListener(this);
        this.requestThreads.add(new RequestThread(this, request));
    }

    @Override
    public void onFinish(Request request) {
        if(threadIndex < requestThreads.size()) {
            requestThreads.get(threadIndex).start();
            threadIndex += 1;
        }
        finished += 1;
        if(started == finished) {
            running = false;
        }
    }

    @Override
    public void onError(Request request) {
        started -= 1;
        if(started == finished) {
            running = false;
        }
    }

    @Override
    public void onStart(Request request) {
        started += 1;
    }

    public boolean isRunning() {
        return running;
    }

    //get responses from requests
    public List<Request> getRequests() {
        return requestThreads.parallelStream().map(requestThread -> requestThread.getRequest()).collect(Collectors.toList());
    }
}
