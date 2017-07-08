package ru.dmisb.advices.features.favorits;

import ru.dmisb.advices.core.BasePresenter;
import ru.dmisb.advices.ui.helpers.ListChangedListener;

class FavoritesPresenter
        extends BasePresenter<FavoritesView>
        implements IFavoritesPresenter {

    private static FavoritesPresenter instance = null;

    public static FavoritesPresenter getInstance() {
        if (instance == null)
            instance = new FavoritesPresenter();
        return instance;
    }

    //region ================= IFavoritesPresenter =================

    @Override
    public void loadFavorites() {
        if (getView() != null) {
            getView().setAdviceList(repository.getAllAdvices());
        }
    }

    @Override
    public void takeListener(ListChangedListener listener) {
        repository.takeListener(listener);
    }

    @Override
    public void dropListener() {
        repository.dropListener();
    }

    @Override
    public void removeAdvice(String id, int position) {
        repository.removeAdvice(id, position);
    }

    //endregion
}
