package com.srabby.http.common;

import com.srabby.http.common.requests.Request;

public interface RequestObserver {
    void onComplete(Request request);
    void onError(Request request);
    void onStart(Request request);
}
