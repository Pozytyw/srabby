package com.srabby.application;

import com.srabby.http.common.RequestObserver;
import com.srabby.http.common.requests.Request;

public class ConsoleRequestObserver implements RequestObserver {
    @Override
    public void onComplete(Request request) {
        System.out.println(request.getResponse());
    }

    @Override
    public void onError(Request request) {
        System.out.println(request.getErrorMessage());
    }

    @Override
    public void onStart(Request request) {
        if(request.isError())
            System.out.println("error thread " + request.getUrl());
        else
            System.out.println("normal thread " + request.getUrl());
    }
}
