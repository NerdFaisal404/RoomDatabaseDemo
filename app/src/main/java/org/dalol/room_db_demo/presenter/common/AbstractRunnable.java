package org.dalol.room_db_demo.presenter.common;

/**
 * Created by filippo on 25/11/2017.
 */

public abstract class AbstractRunnable<S> implements Runnable {

    private final S subject;

    public AbstractRunnable(S subject) {
        this.subject = subject;
    }

    @Override
    public void run() {
        run(subject);
    }

    protected abstract void run(S subject);
}
