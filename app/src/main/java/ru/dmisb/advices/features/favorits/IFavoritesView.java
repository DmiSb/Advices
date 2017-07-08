package ru.dmisb.advices.features.favorits;

import java.util.List;

import ru.dmisb.advices.data.dto.AdviceDto;

interface IFavoritesView {
    void setAdviceList(List<AdviceDto> adviceList);
}
