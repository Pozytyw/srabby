package com.srabby.application;

import com.srabby.http.common.RequestEventListener;
import com.srabby.http.common.requests.Request;

public class VoidRequestEventListener implements RequestEventListener {
    @Override
    public void onFinish(Request request) {

    }

    @Override
    public void onError(Request request) {

    }

    @Override
    public void onStart(Request request) {

    }
}
