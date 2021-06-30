package com.srabby.http.common.execution_strategies.jsoup;

import com.srabby.http.exceptions.ConnectionException;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public abstract class JsoupExecutionStrategy {

    protected static Document connect(String url) throws ConnectionException {
        try {
            return Jsoup.connect(url).get();
            //on error throw connection exception
        } catch (HttpStatusException e){
            throw new ConnectionException("Status code: [" + e.getStatusCode() + "]. " + e.getMessage());
        }catch (Exception e) {
            throw new ConnectionException(e.getMessage()+ " | on url " + url);
        }
    }
}
