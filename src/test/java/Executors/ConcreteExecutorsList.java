package Executors;

import com.srabby.http.common.RequestExecutor;
import com.srabby.http.jsoup.JsoupExecutor;

import java.util.Arrays;
import java.util.List;

public class ConcreteExecutorsList {
    private static ConcreteExecutorsList INSTANCE;
    //request's executors to test
    private final List<RequestExecutor> executors;

    public ConcreteExecutorsList() {
         executors = Arrays.asList(new RequestExecutor[]{
                new JsoupExecutor(10),
                new JsoupExecutor(6),
                new JsoupExecutor(15),
        });
    }

    public static List<RequestExecutor> getList() {
        if(INSTANCE == null) {
            INSTANCE = new ConcreteExecutorsList();
        }

        return INSTANCE.executors;
    }
}
