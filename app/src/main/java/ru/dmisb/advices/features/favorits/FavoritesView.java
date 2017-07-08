package ru.dmisb.advices.features.favorits;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;

import java.util.List;

import ru.dmisb.advices.R;
import ru.dmisb.advices.core.BaseView;
import ru.dmisb.advices.data.dto.AdviceDto;
import ru.dmisb.advices.databinding.ScreenFavoritesBinding;
import ru.dmisb.advices.ui.helpers.ItemTouchHelperCallback;
import ru.dmisb.advices.ui.helpers.ListChangedListener;

public class FavoritesView
        extends BaseView<FavoritesPresenter, ScreenFavoritesBinding>
        implements IFavoritesView, ListChangedListener {

    private FavoritesAdapter adapter = new FavoritesAdapter();
    private ItemTouchHelper itemTouchHelper;

    public FavoritesView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    //region ================= IFavoritesView =================

    @Override
    public void setAdviceList(List<AdviceDto> adviceList) {
        adapter.setAdviceList(adviceList);
    }


    //endregion

    //region ================= BaseView =================

    @Override
    protected FavoritesPresenter initPresenter() {
        return FavoritesPresenter.getInstance();
    }

    @Override
    protected void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        viewDataBinding.favoritesList.setLayoutManager(layoutManager);
        viewDataBinding.favoritesList.setAdapter(adapter);
    }

    @Override
    protected void onAttached() {
        presenter.loadFavorites();
        presenter.takeListener(this);
        itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(viewDataBinding.favoritesList);
    }

    ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(getContext()) {
        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            if (direction == 16) {
                showRemoveAdviceDialog(position);
            }
        }
    };

    private void showRemoveAdviceDialog(final int position) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle(R.string.favorites_remove_title);
        alertDialog.setMessage(R.string.favorites_remove_message);
        alertDialog.setPositiveButton(R.string.yes_caption, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.removeAdvice(adapter.getAdvice(position).getId(), position);
            }
        });
        alertDialog.setNegativeButton(R.string.cancel_caption, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.setCancelable(true);
        alertDialog.show();
    }

    @Override
    protected void onDetached() {
        presenter.dropListener();
        itemTouchHelper.attachToRecyclerView(null);
    }

    @Override
    public void onListChanged() {
        adapter.updateAdapter();
    }

    //endregion
}
