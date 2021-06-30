package com.srabby.http.common.execution_strategies.jsoup;

import com.srabby.http.common.execution_strategies.ExecutionStrategy;
import com.srabby.http.common.requests.*;
import com.srabby.http.exceptions.ScrapeException;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class CSSExecutionStrategy extends JsoupExecutionStrategy implements ExecutionStrategy {

    @Override
    public void execute(Request request) throws ScrapeException {
        Document document = JsoupExecutionStrategy.connect(request.getUrl());
        Elements elements = document.select(request.getRequestBody());
        elements.forEach(element -> {
            request.getResponse().add(element.text());
        });
    }
}
