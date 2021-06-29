package com.srabby.http.common.requests;

import com.srabby.http.common.RequestExecutor;

public class ScriptRequest extends Request{
    private String script;

    public ScriptRequest(String script) {
        this.script = script;
    }

    public String getScript() {
        return script;
    }

    @Override
    public void execute(RequestExecutor requestExecutor) {
        //dispatch start event
        getEventListeners().forEach(eventListener -> eventListener.onStart(this));

        requestExecutor.executeScriptRequest(this);

        //dispatch finish event
        getEventListeners().forEach(eventListener -> eventListener.onFinish(this));
    }
}
