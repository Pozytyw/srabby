package com.srabby.http.common.requests;

import java.util.HashMap;
import java.util.Map;

public final class RequestBuilder {
    private Request request;
    private String url;
    private HttpMethod httpMethod;
    private Map<String, String> httpHeaders;

    public RequestBuilder() {
        httpHeaders = new HashMap<>();

        //default concrete request
        request = new CSSRequest("body");
    }

    //request constructor. Choice of certain request class
    public RequestBuilder cssRequest(String selector){
        request = new CSSRequest(selector);
        return this;
    }

    public RequestBuilder scriptRequest(String script){
        request = new ScriptRequest(script);
        return this;
    }

    public RequestBuilder postRequest(String postBody){
        request = new PostRequest(postBody);
        return this;
    }

    public RequestBuilder getRequest(String requestBody){
        request = new GetRequest(requestBody);
        return this;
    }

    //setters
    public RequestBuilder setUrl(String url) {
        this.url = url;
        return this;
    }

    public RequestBuilder setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
        return this;
    }

    public RequestBuilder addHeader(String name, String value){
        httpHeaders.put(name, value);
        return this;
    }

    public Request build(){
        //complete request must have url
        if(url.isEmpty())
            return null;

        //set all fields
        request.setUrl(url);
        request.setHttpMethod(httpMethod);
        request.setHttpHeaders(httpHeaders);

        return request;
    }
}
