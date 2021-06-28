package com.srabby.http.common.requests;

public class CSSRequest extends Request{
    private String selector;

    public CSSRequest(String selector) {
        this.selector = selector;
    }

    public String getSelector() {
        return selector;
    }

    @Override
    public void execute(RequestExecutor requestExecutor) {
        requestExecutor.executeCSSRequest(this);
    }
}
