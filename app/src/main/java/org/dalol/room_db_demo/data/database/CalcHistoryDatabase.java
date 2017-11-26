package org.dalol.room_db_demo.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by filippo on 25/11/2017.
 */

@Database(entities = CalcHistoryDAO.class, version = 1)
public abstract class CalcHistoryDatabase extends RoomDatabase {

    public abstract CalHistoryDao calcDao();
}
