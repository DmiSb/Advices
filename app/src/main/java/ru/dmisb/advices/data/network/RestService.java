package ru.dmisb.advices.data.network;

public class RestService {

    public void randomCensoredAdvice(AdviceCallback callback) {
        HttpPostAsyncTask task = new HttpPostAsyncTask("random", callback);
        task.execute();
    }
}
