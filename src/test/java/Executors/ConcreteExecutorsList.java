package Executors;

import com.srabby.application.ConsoleRequestObserver;
import com.srabby.http.common.RequestExecutor;
import com.srabby.http.common.RequestObserver;
import com.srabby.http.jsoup.JsoupExecutor;

import java.util.Arrays;
import java.util.List;

public class ConcreteExecutorsList {
    private static ConcreteExecutorsList INSTANCE;

    private final RequestObserver requestObserver;
    //request's executors to test
    private final List<RequestExecutor> executors;

    public ConcreteExecutorsList() {
        requestObserver = new ConsoleRequestObserver();
         executors = Arrays.asList(new RequestExecutor[]{
                new JsoupExecutor(10, requestObserver),
                new JsoupExecutor(6, requestObserver),
                new JsoupExecutor(15, requestObserver),
        });
    }

    public static List<RequestExecutor> getList() {
        if(INSTANCE == null) {
            INSTANCE = new ConcreteExecutorsList();
        }

        return INSTANCE.executors;
    }
}
