package com.srabby.http.common.requests;

import com.srabby.http.common.RequestExecutor;
import com.srabby.http.errors.ScrapeErrors;

public class PostRequest extends Request{
    private String postBody;

    public PostRequest(String postBody) {
        this.postBody = postBody;
    }

    public String getPostBody() {
        return postBody;
    }

    @Override
    public void execute(RequestExecutor requestExecutor) throws ScrapeErrors {
        //dispatch start event
        getEventListeners().forEach(eventListener -> eventListener.onStart(this));

        requestExecutor.executePostRequest(this);

        //dispatch finish event
        getEventListeners().forEach(eventListener -> eventListener.onFinish(this));
    }
}
