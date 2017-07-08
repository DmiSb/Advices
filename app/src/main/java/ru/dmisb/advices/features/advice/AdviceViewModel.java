package ru.dmisb.advices.features.advice;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

public class AdviceViewModel extends BaseObservable {
    private String text;
    private String id;

    @Bindable
    public String getText() {
        return text;
    }

    public String getId() {
        return id;
    }

    public void setAdvice(String text) {
        this.text = text;
        notifyPropertyChanged(BR.text);
    }

    public void setId(String id) {
        this.id = id;
    }
}
