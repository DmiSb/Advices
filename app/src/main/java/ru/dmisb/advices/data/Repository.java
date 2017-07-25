package ru.dmisb.advices.data;

import android.content.Context;

import java.util.List;

import ru.dmisb.advices.data.dto.AdviceDto;
import ru.dmisb.advices.data.local.PreferencesManager;
import ru.dmisb.advices.data.network.AdviceCallback;
import ru.dmisb.advices.data.network.RestService;
import ru.dmisb.advices.ui.helpers.ListChangedListener;

public class Repository implements IRepository{
    private static Repository instance;
    private final PreferencesManager preferencesManager;
    private final RestService restService;
    private final List<AdviceDto> adviceList;
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

    @Override
    public void takeListener(ListChangedListener listener) {
        this.listener = listener;
    }

    @Override
    public void dropListener() {
        this.listener = null;
    }

    //region ================= Network =================

    @Override
    public void randomCensoredAdvice(AdviceCallback callback) {
        restService.randomCensoredAdvice(callback);
    }

    //endregion

    //region ================= Local =================

    @Override
    public List<AdviceDto> getAllAdvices() {
        return adviceList;
    }

    @Override
    public void saveAdvice(AdviceDto advice) {
        if (preferencesManager.saveAdvice(advice)) {
            adviceList.add(advice);
            if (listener != null)
                listener.onListChanged();
        }
    }

    @Override
    public void removeAdvice(String id, int position) {
        if (preferencesManager.removeAdvice(id) && adviceList.get(position).getId().equals(id)) {
            adviceList.remove(position);
            if (listener != null)
                listener.onListChanged();
        }
    }

    //endregion
}
