package ru.dmisb.advices.core;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public abstract class BaseView<P extends BasePresenter, B extends ViewDataBinding>
        extends LinearLayout {

    protected P presenter;
    protected B viewDataBinding;

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        presenter = initPresenter();
    }

    protected abstract P initPresenter();

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()){
            viewDataBinding = DataBindingUtil.bind(this);
            initView();
        }
    }

    protected abstract void initView();

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()){
            presenter.takeView(this);
            onAttached();
        }
    }

    protected abstract void onAttached();

    @Override
    protected void onDetachedFromWindow() {
        if (!isInEditMode()){
            presenter.dropView();
            onDetached();
        }
        super.onDetachedFromWindow();
    }

    protected abstract void onDetached();
}
