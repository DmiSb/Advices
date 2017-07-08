package ru.dmisb.advices.data.network;

import ru.dmisb.advices.data.dto.AdviceDto;

public interface AdviceCallback {
    void completionHandler(Boolean success, AdviceDto advice);
}
