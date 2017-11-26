package org.dalol.room_db_demo.presenter.history;

import android.os.Handler;
import android.os.Looper;

import org.dalol.room_db_demo.data.database.CalcHistoryDAO;
import org.dalol.room_db_demo.data.helper.ApplicationHelper;
import org.dalol.room_db_demo.data.mapper.HistoryMapper;
import org.dalol.room_db_demo.data.model.CalcHistoryVO;
import org.dalol.room_db_demo.presenter.common.AbstractRunnable;

import java.util.List;

/**
 * Created by filippo on 25/11/2017.
 */

public class HistoryPresenter {

    private final HistoryViewCallback viewCallback;

    public HistoryPresenter(HistoryViewCallback viewCallback) {
        this.viewCallback = viewCallback;
    }

    public void loadHistory() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<CalcHistoryDAO> all = ApplicationHelper.getInstance().getDatabase().calcDao().getAll();
                List<CalcHistoryVO> historyList = new HistoryMapper().map(all);
                mHandler.post(new AbstractRunnable<List<CalcHistoryVO>>(historyList) {
                    @Override
                    protected void run(List<CalcHistoryVO> subject) {
                        if (viewCallback != null) {
                            viewCallback.onLoadHistory(subject);
                        }
                    }
                });
            }
        }).start();
    }

    public interface HistoryViewCallback {

        void onLoadHistory(List<CalcHistoryVO> histories);
    }

    private final Handler mHandler = new Handler(Looper.getMainLooper());
}
