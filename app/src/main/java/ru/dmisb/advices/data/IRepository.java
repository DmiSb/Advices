package ru.dmisb.advices.data;

import java.util.List;

import ru.dmisb.advices.data.dto.AdviceDto;
import ru.dmisb.advices.data.network.AdviceCallback;
import ru.dmisb.advices.ui.helpers.ListChangedListener;

public interface IRepository {
    void takeListener(ListChangedListener listener);
    void dropListener();

    void randomCensoredAdvice(AdviceCallback callback);

    List<AdviceDto> getAllAdvices();
    void saveAdvice(AdviceDto advice);
    void removeAdvice(String id, int position);
}
