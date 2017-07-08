package ru.dmisb.advices.data;

import android.content.Context;

import java.util.List;

import ru.dmisb.advices.data.network.AdviceCallback;
import ru.dmisb.advices.data.local.PreferencesManager;
import ru.dmisb.advices.data.network.RestService;
import ru.dmisb.advices.data.dto.AdviceDto;
import ru.dmisb.advices.ui.helpers.ListChangedListener;

public class Repository {
    private static Repository instance;
    private PreferencesManager preferencesManager;
    private RestService restService;
    private List<AdviceDto> adviceList;
    private ListChangedListener listener;

    public Repository(Context context) {
        instance = this;
        preferencesManager = new PreferencesManager(context);
        restService = new RestService();

        adviceList = preferencesManager.getAllAdvices();
    }

    public static Repository getInstance() {
        return instance;
    }

    public void takeListener(ListChangedListener listener) {
        this.listener = listener;
    }

    public void dropListener() {
        this.listener = null;
    }

    //region ================= Network =================

    public void randomCensoredAdvice(AdviceCallback callback) {
        restService.randomCensoredAdvice(callback);
    }

    //endregion

    //region ================= Local =================

    public List<AdviceDto> getAllAdvices() {
        return adviceList;
    }

    public void saveAdvice(AdviceDto advice) {
        if (preferencesManager.saveAdvice(advice)) {
            adviceList.add(advice);
            if (listener != null)
                listener.onListChanged();
        }
    }

    public void removeAdvice(String id, int position) {
        if (preferencesManager.removeAdvice(id) && adviceList.get(position).getId().equals(id)) {
            adviceList.remove(position);
            if (listener != null)
                listener.onListChanged();
        }
    }

    //endregion
}
