package com.srabby.http.common.requests;

public class GetRequest extends Request{
    private String requestBody;

    public GetRequest(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getRequestBody() {
        return requestBody;
    }

    @Override
    public void execute(RequestExecutor requestExecutor) {
        requestExecutor.executeGetRequest(this);
    }
}
