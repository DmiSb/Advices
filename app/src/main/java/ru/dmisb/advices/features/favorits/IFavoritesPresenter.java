package ru.dmisb.advices.features.favorits;

import ru.dmisb.advices.ui.helpers.ListChangedListener;

interface IFavoritesPresenter {
    void loadFavorites();

    void takeListener(ListChangedListener listener);
    void dropListener();

    void removeAdvice(String id, int position);
}
