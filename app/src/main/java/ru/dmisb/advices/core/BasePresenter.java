package ru.dmisb.advices.core;

import ru.dmisb.advices.data.Repository;

public abstract class BasePresenter<V extends BaseView> {

    private V view;
    protected final Repository repository;

    protected BasePresenter() {
        repository = Repository.getInstance();
    }

    void takeView(V view) {
        this.view = view;
    }

    void dropView() {
        view = null;
    }

    protected V getView() {
        return view;
    }
}
