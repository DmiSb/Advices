package ru.dmisb.advices.features.advice;

import java.util.Timer;
import java.util.TimerTask;

import ru.dmisb.advices.data.network.AdviceCallback;
import ru.dmisb.advices.core.BasePresenter;
import ru.dmisb.advices.data.dto.AdviceDto;
import ru.dmisb.advices.utils.AppConfig;

class AdvicePresenter
        extends BasePresenter<AdviceView>
        implements IAdvicePresenter, AdviceCallback {

    private static AdvicePresenter instance = null;
    private final AdviceViewModel viewModel = new AdviceViewModel();
    private Timer timer = null;
    private RefreshTimerTask refreshTask = null;

    private AdvicePresenter() {
        initView();
    }

    private void initView() {
        repository.randomCensoredAdvice(this);
        startTimer();
    }

    static AdvicePresenter getInstance() {
        if (instance == null)
            instance = new AdvicePresenter();
        return instance;
    }

    private void startTimer() {
        if (timer == null) {
            timer = new Timer();
            refreshTask = new RefreshTimerTask();
        }
        timer.schedule(refreshTask, AppConfig.MAX_CONNECTION_TIMEOUT, AppConfig.REPEAT_TIMEOUT);
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            refreshTask = null;
            timer = null;
        }
    }

    //region ================= AdviceCallback =================

    @Override
    public void completionHandler(Boolean success, AdviceDto advice) {
        if(success) {
            viewModel.setId(advice.getId());
            viewModel.setAdvice(advice.getText());
        }
        if (timer == null)
            startTimer();
    }

    //endregion

    //region ================= IAdvicePresenter =================

    @Override
    public AdviceViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void refresh() {
        stopTimer();
        repository.randomCensoredAdvice(this);
    }

    @Override
    public void toFavorites() {
        repository.saveAdvice(
                new AdviceDto(viewModel.getId(), viewModel.getText())
        );
    }

    //endregion

    private class RefreshTimerTask extends TimerTask {

        @Override
        public void run() {
            repository.randomCensoredAdvice(instance);
        }
    }
}
