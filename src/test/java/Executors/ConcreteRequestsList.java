package Executors;

import com.srabby.http.common.RequestBuilder;
import com.srabby.http.common.requests.Request;
import com.srabby.http.common.requests.RequestEventListener;

import java.util.Arrays;
import java.util.List;

public class ConcreteRequestsList {
    private static ConcreteRequestsList INSTANCE;
    //request's executors to test
    private final List<Request> requests;
    private final String url= "https://en.wikipedia.org/wiki/Special:Random";
    private final String badUrl= "";
    private final String cssSelector= "#firstHeading";

    public ConcreteRequestsList() {
        //create console request observer
        RequestEventListener requestEventListener = new ConsoleRequestEventListener();

        /*CHANGE TO Prototype pattern*/
        requests = Arrays.asList(new Request[]{
                //create Request
                //add correct request
                //css request
                new RequestBuilder()
                        .setEventListener(requestEventListener)
                        .setUrl(url)
                        .cssRequest(cssSelector)
                        .build(),

                //request, that execute js
                new RequestBuilder()
                        .setEventListener(requestEventListener)
                        .setUrl(url)
                        .scriptRequest("return document.getElementById(\"firstHeading\")")
                        .build(),

                //request, that execute js
                new RequestBuilder()
                        .setEventListener(requestEventListener)
                        .setUrl(url)
                        .scriptRequest("return document.getElementById(\"firstHeading\")")
                        .build(),

                //add request with bad url
                //without console event listener
                new RequestBuilder()
                        .setEventListener(requestEventListener)
                        .setUrl(badUrl)
                        .cssRequest(cssSelector)
                        .build()
        });
    }

    public static List<Request> getList() {
        if(INSTANCE == null) {
            INSTANCE = new ConcreteRequestsList();
        }

        return INSTANCE.requests;
    }
}
