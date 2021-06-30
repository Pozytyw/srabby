package com.srabby.http.common.requests;

import com.srabby.http.common.requests.Request;

public interface RequestEventListener {
    void onFinish(Request request);
    void onError(Request request);
    void onStart(Request request);
}
