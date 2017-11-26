package org.dalol.room_db_demo.data.helper;

import android.arch.persistence.room.Room;
import android.content.Context;

import org.dalol.room_db_demo.data.database.CalcHistoryDatabase;

/**
 * Created by filippo on 25/11/2017.
 */

public class ApplicationHelper {

    private CalcHistoryDatabase database;

    private ApplicationHelper(){}

    private final static class InstanceHolder {
        private final static ApplicationHelper INSTANCE = new ApplicationHelper();
    }

    public static ApplicationHelper getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void init(Context context) {
        this.database = Room.databaseBuilder(context.getApplicationContext(), CalcHistoryDatabase.class, "calculator-database")
                // allow queries on the main thread.
                // Don't do this on a real app! See PersistenceBasicSample for an example.
                .allowMainThreadQueries()
                .build();
    }

    public CalcHistoryDatabase getDatabase() {
        return database;
    }

    public void destroy() {
        this.database = null;
    }
}
