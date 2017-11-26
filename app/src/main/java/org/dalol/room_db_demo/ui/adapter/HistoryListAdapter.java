package org.dalol.room_db_demo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.dalol.room_db_demo.R;
import org.dalol.room_db_demo.data.model.CalcHistoryVO;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by filippo on 25/11/2017.
 */

public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.HistoryViewHolder> {

    private final List<CalcHistoryVO> calcHistories = new LinkedList<>();
    private final LayoutInflater layoutInflater;

    public HistoryListAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HistoryViewHolder(layoutInflater.inflate(R.layout.item_history_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) {
        CalcHistoryVO history = calcHistories.get(position);
        holder.historyLabel.setText(String.format("Val 1 = %s, Val 2 = %s, date -> %s", history.firstValue, history.secondValue, history.processDate));
    }

    @Override
    public int getItemCount() {
        return calcHistories.size();
    }

    public void setHistories(List<CalcHistoryVO> histories) {
        calcHistories.addAll(histories);
        notifyDataSetChanged();
    }

    public void clearHistories() {
        calcHistories.clear();
        notifyDataSetChanged();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {

        final TextView historyLabel;

        HistoryViewHolder(View itemView) {
            super(itemView);
            historyLabel = itemView.findViewById(R.id.text_history);
        }
    }
}
