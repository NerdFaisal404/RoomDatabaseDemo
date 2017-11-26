package org.dalol.room_db_demo.application;

import android.app.Application;

import org.dalol.room_db_demo.data.helper.ApplicationHelper;

/**
 * Created by filippo on 25/11/2017.
 */

public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationHelper.getInstance().init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ApplicationHelper.getInstance().destroy();
    }
}
