package com.srabby.http.common.execution_strategies.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public abstract class SeleniumExecutionStrategy {

    protected static WebDriver getWebDriver() {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        return new ChromeDriver(new ChromeOptions().addArguments("--headless"));
    }
}
