package ru.dmisb.advices.core;

import ru.dmisb.advices.data.Repository;

public abstract class BasePresenter<V extends BaseView> {

    protected V view;
    protected Repository repository;

    public BasePresenter() {
        repository = Repository.getInstance();
    }

    protected void takeView(V view) {
        this.view = view;
    }

    protected void dropView() {
        view = null;
    }

    protected V getView() {
        return view;
    }
}
