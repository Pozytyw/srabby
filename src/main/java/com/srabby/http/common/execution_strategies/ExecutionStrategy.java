package com.srabby.http.common.execution_strategies;

import com.srabby.http.common.requests.Request;
import com.srabby.http.exceptions.ScrapeException;

public interface ExecutionStrategy {
    void execute(Request request) throws ScrapeException;
}
