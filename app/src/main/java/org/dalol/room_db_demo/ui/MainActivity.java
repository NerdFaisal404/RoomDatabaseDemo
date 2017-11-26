package org.dalol.room_db_demo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.dalol.room_db_demo.data.api.ApiSum;
import org.dalol.room_db_demo.R;
import org.dalol.room_db_demo.presenter.calc.SumPresenter;

import java.util.Locale;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseActivity implements SumPresenter.ViewCallback {

    private EditText num1, num2;
    private TextView resultTxt;

    private SumPresenter sumPresenter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        num1 = findViewById(R.id.text_num1);
        num2 = findViewById(R.id.text_num2);
        resultTxt = findViewById(R.id.text_result);

        Button calculateButton = findViewById(R.id.button_calculate);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sumPresenter.calculateSum(getValue(num1), getValue(num2));
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.dalol.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiSum apiSum = retrofit.create(ApiSum.class);
        sumPresenter = new SumPresenter(this, apiSum);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionViewHistory:
                startActivity(new Intent(this, HistoryActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private int getValue(EditText text) {
        String valString = text.getText().toString();
        return valString.isEmpty() ? 0 : Integer.valueOf(valString);
    }

    @Override
    public void onResult(int result) {
        resultTxt.setText(String.format(Locale.UK, "Result = %d", result));
    }

    @Override
    public void onFailure(String message) {
        showToast(message);
    }
}
