package Executors;

import com.srabby.http.common.requests.RequestEventListener;
import com.srabby.http.common.requests.Request;

public class ConsoleRequestEventListener implements RequestEventListener {
    @Override
    public void onFinish(Request request) {
        System.out.println(request.getResponse());
    }

    @Override
    public void onError(Request request) {
        System.out.println(request.getErrorMessage());
    }

    @Override
    public void onStart(Request request) {
        if(request.isError())
            System.out.println("error thread " + request.getUrl());
        else
            System.out.println("normal thread " + request.getUrl());
    }
}
