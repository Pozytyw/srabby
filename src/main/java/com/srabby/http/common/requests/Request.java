package com.srabby.http.common.requests;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Request {
    protected String url;
    protected HttpMethod httpMethod;
    protected Set<String> response;
    protected Map<String, String> httpHeaders;
    protected boolean error;
    protected String errorMessage;

    public abstract void execute(RequestExecutor requestExecutor);

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public void setHttpHeaders(Map<String, String> httpHeaders) {
        this.httpHeaders = httpHeaders;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getUrl() {
        return url;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public Set<String> getResponse() {
        if(response == null){
            response = new HashSet<>();
        }
        return response;
    }

    public void setResponse(Set<String> response) {
        this.response = response;
    }

    public Map<String, String> getHttpHeaders() {
        return httpHeaders;
    }
}
