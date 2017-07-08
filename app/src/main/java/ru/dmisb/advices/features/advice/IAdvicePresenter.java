package ru.dmisb.advices.features.advice;

interface IAdvicePresenter {
    AdviceViewModel getViewModel();
    void refresh();
    void toFavorites();
}
