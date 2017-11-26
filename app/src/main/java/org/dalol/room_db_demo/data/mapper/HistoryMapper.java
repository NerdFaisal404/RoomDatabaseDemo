package org.dalol.room_db_demo.data.mapper;

import org.dalol.room_db_demo.data.database.CalcHistoryDAO;
import org.dalol.room_db_demo.data.model.CalcHistoryVO;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by filippo on 25/11/2017.
 */

public class HistoryMapper {

    public List<CalcHistoryVO> map(List<CalcHistoryDAO> all) {
        List<CalcHistoryVO> historyList = new LinkedList<>();
        if (all != null) {
            for (CalcHistoryDAO historyTable : all) {
                CalcHistoryVO history = new CalcHistoryVO();
                history.firstValue = historyTable.getFirstValue();
                history.secondValue = historyTable.getSecondValue();
                history.processDate = historyTable.getProcessDate();
                historyList.add(history);
            }
        }
        return historyList;
    }
}
