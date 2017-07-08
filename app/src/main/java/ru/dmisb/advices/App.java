package ru.dmisb.advices;

import android.app.Application;
import android.content.Context;

import ru.dmisb.advices.data.Repository;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initRepository(getApplicationContext());
    }

    private void initRepository(Context context) {
        new Repository(context);
    }
}
