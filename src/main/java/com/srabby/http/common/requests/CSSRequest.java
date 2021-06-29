package com.srabby.http.common.requests;

import com.srabby.http.common.RequestExecutor;
import com.srabby.http.errors.ScrapeErrors;

public class CSSRequest extends Request{
    private String selector;

    public CSSRequest(String selector) {
        this.selector = selector;
    }

    public String getSelector() {
        return selector;
    }

    @Override
    //Select nodes, which match to selector
    public void execute(RequestExecutor requestExecutor) throws ScrapeErrors{
        requestExecutor.executeCSSRequest(this);
    }
}
