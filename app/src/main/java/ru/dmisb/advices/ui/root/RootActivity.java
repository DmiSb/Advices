package ru.dmisb.advices.ui.root;

import android.databinding.DataBindingUtil;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.dmisb.advices.R;
import ru.dmisb.advices.databinding.ActivityRootBinding;

public class RootActivity extends AppCompatActivity {

    private ActivityRootBinding viewDataBinding;
    private final RootPagerAdapter adapter = new RootPagerAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_root);

        initToolbar();
        initAdapter();
    }

    private void initToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();
    }

    private void initAdapter() {
        viewDataBinding.rootTab.setupWithViewPager(viewDataBinding.rootPager);
        adapter.initTitles(getResources().getStringArray(R.array.screen_titles));
        viewDataBinding.rootPager.setAdapter(adapter);
        viewDataBinding.rootPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(viewDataBinding.rootTab));
    }
}
