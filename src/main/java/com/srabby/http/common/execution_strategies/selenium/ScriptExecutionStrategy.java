package com.srabby.http.common.execution_strategies.selenium;

import com.srabby.http.common.execution_strategies.ExecutionStrategy;
import com.srabby.http.common.requests.Request;
import com.srabby.http.exceptions.ScrapeException;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebElement;

public class ScriptExecutionStrategy extends SeleniumExecutionStrategy implements ExecutionStrategy {
    private static int i = 0;

    @Override
    public void execute(Request request) throws ScrapeException {
        //create webDriver
        WebDriver webDriver = getWebDriver();

        //go to url
        webDriver.get(request.getUrl());

        //execute javascript
        JavascriptExecutor js = (JavascriptExecutor)webDriver;
        Object result = js.executeScript(request.getRequestBody());

        //if javascript return nothing
        if(result == null)
            return;

        //it returned data is WebElement
        if(result.getClass().equals(RemoteWebElement.class)){
            request.getResponse().add(((RemoteWebElement)result).getText());
        }

        //close window
        webDriver.close();
    }
}


