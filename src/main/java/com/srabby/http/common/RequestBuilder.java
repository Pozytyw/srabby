package com.srabby.http.common;

import com.srabby.http.common.requests.*;

import java.util.HashMap;
import java.util.Map;

public final class RequestBuilder {
    private Request request;
    private String url;
    private HttpMethod httpMethod;
    private RequestEventListener requestEventListener;
    private Map<String, String> httpHeaders;

    public RequestBuilder() {
        httpHeaders = new HashMap<>();

        //default concrete request
        request = new CSSRequest("body");
    }

    //request constructor. Choose of certain request class
    public RequestBuilder cssRequest(String selector){
        request = new CSSRequest(selector);
        return this;
    }

    public RequestBuilder scriptRequest(String script){
        request = new ScriptRequest(script);
        return this;
    }

    //setters
    public RequestBuilder setUrl(String url) {
        this.url = url;
        return this;
    }

    public RequestBuilder addHeader(String name, String value){
        httpHeaders.put(name, value);
        return this;
    }

    public RequestBuilder setEventListener(RequestEventListener requestEventListener){
        this.requestEventListener = requestEventListener;
        return this;
    }

    public Request build(){
        //set all fields
        request.setUrl(url);
        request.setHttpMethod(httpMethod);
        request.setHttpHeaders(httpHeaders);
        if(requestEventListener != null)
            request.addEventListener(requestEventListener);

        return request;
    }
}
