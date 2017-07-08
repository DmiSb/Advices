package ru.dmisb.advices.features.advice;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import ru.dmisb.advices.core.BaseView;
import ru.dmisb.advices.databinding.ScreenAdviceBinding;

public class AdviceView
        extends BaseView<AdvicePresenter, ScreenAdviceBinding> {

    public AdviceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    //region ================= BaseView =================

    @Override
    protected AdvicePresenter initPresenter() {
        return AdvicePresenter.getInstance();
    }

    @Override
    protected void initView() {
        viewDataBinding.setModel(presenter.getViewModel());
    }

    @Override
    protected void onAttached() {
        viewDataBinding.adviceRefresh.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.refresh();
            }
        });

        viewDataBinding.adviceToFavorites.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.toFavorites();
            }
        });
    }

    @Override
    protected void onDetached() {
        viewDataBinding.adviceRefresh.setOnClickListener(null);
        viewDataBinding.adviceToFavorites.setOnClickListener(null);
    }

    //endregion
}
