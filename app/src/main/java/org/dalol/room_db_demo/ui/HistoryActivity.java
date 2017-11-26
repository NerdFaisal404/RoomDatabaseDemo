package org.dalol.room_db_demo.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import org.dalol.room_db_demo.R;
import org.dalol.room_db_demo.data.helper.ApplicationHelper;
import org.dalol.room_db_demo.data.model.CalcHistoryVO;
import org.dalol.room_db_demo.presenter.history.HistoryPresenter;
import org.dalol.room_db_demo.ui.adapter.HistoryListAdapter;

import java.util.List;

/**
 * Created by filippo on 25/11/2017.
 */

public class HistoryActivity extends BaseActivity implements HistoryPresenter.HistoryViewCallback {

    private HistoryListAdapter historyListAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_history;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_history, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        RecyclerView historyList = findViewById(R.id.list_cal_history);
        historyList.setHasFixedSize(true);
        historyList.setLayoutManager(new LinearLayoutManager(this));
        historyListAdapter = new HistoryListAdapter(this);
        historyList.setAdapter(historyListAdapter);

        HistoryPresenter presenter = new HistoryPresenter(this);
        presenter.loadHistory();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.actionClearHistory:
                AlertDialog alertDialog = new AlertDialog.Builder(this)
                        .setTitle("Confirm action")
                        .setMessage("Do you want to clear history?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                ApplicationHelper.getInstance().getDatabase().calcDao().emptyTable();
                                historyListAdapter.clearHistories();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).create();
                alertDialog.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLoadHistory(List<CalcHistoryVO> histories) {
        historyListAdapter.setHistories(histories);
    }
}
