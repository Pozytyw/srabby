package com.srabby.http.common.requests;

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
        requestExecutor.executeScriptRequest(this);
    }
}
