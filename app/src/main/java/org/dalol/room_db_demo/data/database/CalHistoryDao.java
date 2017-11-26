package org.dalol.room_db_demo.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import org.dalol.room_db_demo.data.model.Constants;

import java.util.List;

/**
 * Created by filippo on 25/11/2017.
 */

@Dao
public interface CalHistoryDao {

    @Query("SELECT * FROM " + Constants.TABLE_NAME)
    List<CalcHistoryDAO> getAll();

    @Query("SELECT * FROM " + Constants.TABLE_NAME + " where process_date LIKE :processDate")
    CalcHistoryDAO findByProcessDate(String processDate);

    @Query("SELECT COUNT(*) from " + Constants.TABLE_NAME)
    int countUsers();

    @Insert
    void insertAll(CalcHistoryDAO... historyTables);

    @Delete
    void delete(CalcHistoryDAO historyTable);

    @Query("DELETE FROM " + Constants.TABLE_NAME)
    void emptyTable();
}
