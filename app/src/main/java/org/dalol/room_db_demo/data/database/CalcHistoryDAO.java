package org.dalol.room_db_demo.data.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by filippo on 25/11/2017.
 */

@Entity(tableName = "calculator")
public class CalcHistoryDAO {

    @PrimaryKey(autoGenerate = true) private int uid;
    @ColumnInfo(name = "first_value") private String firstValue;
    @ColumnInfo(name = "second_value") private String secondValue;
    @ColumnInfo(name = "process_date") private String processDate;

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setFirstValue(String firstValue) {
        this.firstValue = firstValue;
    }

    public void setProcessDate(String processDate) {
        this.processDate = processDate;
    }

    public void setSecondValue(String secondValue) {
        this.secondValue = secondValue;
    }

    public int getUid() {
        return uid;
    }

    public String getFirstValue() {
        return firstValue;
    }

    public String getProcessDate() {
        return processDate;
    }

    public String getSecondValue() {
        return secondValue;
    }
}
