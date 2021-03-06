package com.srabby.http.common.requests;

import com.srabby.http.common.execution_strategies.ExecutionStrategy;
import com.srabby.http.common.HttpMethod;
import com.srabby.http.exceptions.ScrapeException;

import java.util.*;

public abstract class Request {
    protected String url;
    protected HttpMethod httpMethod;
    protected Map<String, String> httpHeaders;
    protected ExecutionStrategy executionStrategy;
    protected String requestBody;

    protected Set<String> response;
    //listeners
    private List<RequestEventListener> eventListeners;

    //error fields
    protected boolean error = false;
    protected String errorMessage;

    public Request(ExecutionStrategy executionStrategy) {
        this.executionStrategy = executionStrategy;
    }

    public abstract void execute() throws ScrapeException;

    //getters and setters
    public void setUrl(String url) {
        this.url = url;
    }

    public void setHttpHeaders(Map<String, String> httpHeaders) {
        this.httpHeaders = httpHeaders;
    }

    public boolean isError() {
        return error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        error = true;
        this.errorMessage = errorMessage;
        //dispatch error event
        eventListeners.forEach(eventListener -> eventListener.onError(this));
    }

    public String getUrl() {
        return url;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
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

    public String getRequestBody() {
        return requestBody;
    }

    //event listeners
    public void addEventListener(RequestEventListener eventListener){
        if(eventListeners == null)
            eventListeners = new LinkedList<>();

        eventListeners.add(eventListener);
    }

    public void removeEventListener(RequestEventListener eventListener){
        if(eventListeners == null)
            eventListeners = new LinkedList<>();

        if(eventListeners.contains(eventListener))
            eventListeners.remove(eventListener);
    }

    protected List<RequestEventListener> getEventListeners() {
        if(eventListeners == null)
            eventListeners = new LinkedList<>();

        return eventListeners;
    }
}
