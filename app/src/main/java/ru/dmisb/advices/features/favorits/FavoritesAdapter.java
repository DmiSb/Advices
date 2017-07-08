package ru.dmisb.advices.features.favorits;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.dmisb.advices.R;
import ru.dmisb.advices.data.dto.AdviceDto;
import ru.dmisb.advices.databinding.ScreenFavoritesItemBinding;
import ru.dmisb.advices.features.advice.AdviceViewModel;

class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoritesHolder>{

    private List<AdviceDto> adviceList;

    @Override
    public FavoritesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FavoritesHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.screen_favorites_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(FavoritesHolder holder, int position) {
        AdviceViewModel model = new AdviceViewModel();
        AdviceDto advice = adviceList.get(position);
        model.setId(advice.getId());
        model.setAdvice(advice.getText());
        holder.viewDataBinding.setModel(model);
    }

    @Override
    public int getItemCount() {
        return adviceList != null ? adviceList.size() : 0;
    }

    void setAdviceList(List<AdviceDto> adviceList) {
        this.adviceList = adviceList;
        notifyDataSetChanged();
    }

    AdviceDto getAdvice(int position) {
        return adviceList.get(position);
    }

    void updateAdapter() {
        notifyDataSetChanged();
    }

    class FavoritesHolder extends RecyclerView.ViewHolder {
        final ScreenFavoritesItemBinding viewDataBinding;

        FavoritesHolder(View itemView) {
            super(itemView);
            viewDataBinding = DataBindingUtil.bind(itemView);
        }
    }
}
