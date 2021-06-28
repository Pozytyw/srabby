package Executors;

import com.srabby.http.common.requests.RequestExecutor;
import com.srabby.http.jsoup.JsoupExecutor;

import java.util.Arrays;
import java.util.List;

public class ConcreteExecutorsList {
    private static ConcreteExecutorsList INSTANCE;

    //request's executors to test
    private final List<RequestExecutor> executors = Arrays.asList(new RequestExecutor[]{
            new JsoupExecutor(),
            new JsoupExecutor()
    });

    public static List<RequestExecutor> getList() {
        if(INSTANCE == null) {
            INSTANCE = new ConcreteExecutorsList();
        }

        return INSTANCE.executors;
    }
}
