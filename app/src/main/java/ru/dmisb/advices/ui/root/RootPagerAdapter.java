package ru.dmisb.advices.ui.root;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.dmisb.advices.R;

class RootPagerAdapter extends PagerAdapter {

    private String[] titles;

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View view = null;

        switch (position) {
            case 0:
                view = inflater.inflate(R.layout.screen_advice, container, false);

                break;
            case 1:
                view = inflater.inflate(R.layout.screen_favorites, container, false);
                break;
        }

        if (view != null)
            container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    void initTitles(String[] titles) {
        this.titles = titles;
    }
}
