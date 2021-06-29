package com.srabby.http.jsoup;

import com.srabby.http.common.RequestExecutor;
import com.srabby.http.common.requests.*;
import com.srabby.http.errors.ConnectionException;
import com.srabby.http.errors.ScrapeErrors;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class JsoupExecutor extends RequestExecutor {

    private Document document;

    public JsoupExecutor(int concurrentlyAmount) {
        super(concurrentlyAmount);
    }

    private Document connect(String url) throws ConnectionException {
        try {
            return Jsoup.connect(url).get();
            //on error throw connection exception
        } catch (HttpStatusException e){
            throw new ConnectionException("Status code: [" + e.getStatusCode() + "]. " + e.getMessage());
        }catch (Exception e) {
            throw new ConnectionException(e.getMessage());
        }
    }

    @Override
    protected String getTitle() {
        if(document == null)
            return null;

        return document.title();
    }

    @Override
    public void executeScriptRequest(ScriptRequest request) throws ScrapeErrors {
    }

    @Override
    public void executeCSSRequest(CSSRequest request) throws ScrapeErrors {
        Document document = connect(request.getUrl());
        Elements elements = document.select(request.getSelector());
        elements.forEach(element -> {
            request.getResponse().add(element.text());
        });
    }

    @Override
    public void executePostRequest(PostRequest request) throws ScrapeErrors {

    }

    @Override
    public void executeGetRequest(GetRequest request) throws ScrapeErrors {

    }
}
