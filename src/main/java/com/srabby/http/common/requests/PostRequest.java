package com.srabby.http.common.requests;

public class PostRequest extends Request{
    private String postBody;

    public PostRequest(String postBody) {
        this.postBody = postBody;
    }

    public String getPostBody() {
        return postBody;
    }

    @Override
    public void execute(RequestExecutor requestExecutor) {
        requestExecutor.executePostRequest(this);
    }
}
