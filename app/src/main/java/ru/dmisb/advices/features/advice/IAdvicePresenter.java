package ru.dmisb.advices.features.advice;

public interface IAdvicePresenter {
    AdviceViewModel getViewModel();
    void refresh();
    void toFavorites();
}
