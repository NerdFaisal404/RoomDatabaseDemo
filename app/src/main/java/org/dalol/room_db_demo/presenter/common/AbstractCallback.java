package org.dalol.room_db_demo.presenter.common;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class AbstractCallback<S, R> implements Callback<R> {

    private final S subject;

    public AbstractCallback(S subject) {
        this.subject = subject;
    }

    @Override
    public void onResponse(Call<R> call, Response<R> response) {
        onResponse(call, response, subject);
    }

    protected abstract void onResponse(Call<R> call, Response<R> response, S subject);
}