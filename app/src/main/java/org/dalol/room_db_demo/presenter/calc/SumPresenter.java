package org.dalol.room_db_demo.presenter.calc;

import android.support.annotation.NonNull;

import org.dalol.room_db_demo.data.api.ApiSum;
import org.dalol.room_db_demo.data.database.CalcHistoryDAO;
import org.dalol.room_db_demo.data.helper.ApplicationHelper;
import org.dalol.room_db_demo.data.model.CalcRequest;
import org.dalol.room_db_demo.data.model.ResultResponse;
import org.dalol.room_db_demo.presenter.common.AbstractCallback;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by filippo on 25/11/2017.
 */

public class SumPresenter {

    private final ViewCallback viewCallback;
    private final ApiSum apiSum;

    public SumPresenter(@NonNull ViewCallback viewCallback, @NonNull ApiSum apiSum) {
        this.viewCallback = viewCallback;
        this.apiSum = apiSum;
    }

    public void calculateSum(int num1, int num2) {
        Call<ResultResponse> calculateSum = this.apiSum.calculateSum(num1, num2);
        calculateSum.enqueue(new AbstractCallback<CalcRequest, ResultResponse>(new CalcRequest(num1, num2)) {

            @Override
            protected void onResponse(Call<ResultResponse> call, Response<ResultResponse> response, CalcRequest subject) {
                if (response.isSuccessful()) {
                    ResultResponse body = response.body();
                    if (body != null) {
                        Integer result = body.getResponse();
                        CalcHistoryDAO historyTable = new CalcHistoryDAO();
                        historyTable.setFirstValue(String.valueOf(subject.getFirstValue()));
                        historyTable.setSecondValue(String.valueOf(subject.getSecondValue()));
                        historyTable.setProcessDate(new Date().toString());
                        ApplicationHelper.getInstance().getDatabase().calcDao().insertAll(historyTable);
                        viewCallback.onResult(result);
                    } else {
                        handleError("Body returned as null");
                    }
                } else {
                    handleError(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {
                handleError(t.getMessage());
            }
        });
    }

    private void handleError(String message) {
        viewCallback.onFailure(message);
    }


    public interface ViewCallback {

        void onResult(int result);

        void onFailure(String message);
    }
}
