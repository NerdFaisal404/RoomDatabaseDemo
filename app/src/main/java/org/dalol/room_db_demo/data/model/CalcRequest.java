package org.dalol.room_db_demo.data.model;

/**
 * Created by filippo on 25/11/2017.
 */

public class CalcRequest {

    private final int firstValue;
    private final int secondValue;

    public CalcRequest(int firstValue, int secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public int getFirstValue() {
        return firstValue;
    }

    public int getSecondValue() {
        return secondValue;
    }
}
