# Srabby
### Java multithreading web scraping

#### General usage

```Java
    //define request's list
    List<Request> requests;
    
    requests = Arrays.asList(new Request[]{
        //create simple request
        new RequestBuilder()
            .setEventListener(requestEventListener) // class, that implement RequestEventListener interface
            .setUrl(url) //e.g. "https://en.wikipedia.org/wiki/Special:Random"
            //select the query type as cssRequest 
            .cssRequest(cssSelector) //e.g. ".class_name"
            .build(),//...create others requests
    });
    
    
    //create multithreading executor
    ConcurrentRequestExecutor requestExecutor = new ConcurrentRequestExecutor();
    
    //add all request to executor
    requests.forEach(request -> {
        requestExecutor.addRequest(request);
    });

    //start
    requestExecutor.executeRequestsSimultaneously();
    
```
Notice that ConcurrentRequestExecutor don't use ``` join() ``` threahd's method.
Use ``` requestExecutor.isRunning() ``` to check if all of request's thread ended their jobs. 
