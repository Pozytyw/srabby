package com.srabby.http.common.requests;

import com.srabby.http.common.RequestExecutor;
import com.srabby.http.errors.ScrapeErrors;

public class GetRequest extends Request{
    private String requestBody;

    public GetRequest(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getRequestBody() {
        return requestBody;
    }

    @Override
    public void execute(RequestExecutor requestExecutor) throws ScrapeErrors {
        requestExecutor.executeGetRequest(this);
    }
}
