package com.srabby.http.common.requests;

import com.srabby.http.common.execution_strategies.selenium.ScriptExecutionStrategy;
import com.srabby.http.exceptions.ScrapeException;

public class ScriptRequest extends Request{
    public ScriptRequest(String script) {
        super(new ScriptExecutionStrategy());
        this.requestBody = script;
    }

    public String getSelector() {
        return requestBody;
    }

    @Override
    //Select nodes, which match to selector
    public void execute() throws ScrapeException {
        //dispatch start event
        getEventListeners().forEach(eventListener -> eventListener.onStart(this));

        this.executionStrategy.execute(this);

        //dispatch finish event
        getEventListeners().forEach(eventListener -> eventListener.onFinish(this));
    }
}
