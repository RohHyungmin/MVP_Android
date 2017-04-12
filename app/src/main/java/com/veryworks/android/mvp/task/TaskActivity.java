package com.veryworks.android.mvp.task;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.veryworks.android.mvp.R;

public class TaskActivity extends AppCompatActivity implements TaskContract.View, View.OnClickListener{

    private TaskContract.Presenter presenter;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        tv = (TextView) findViewById(R.id.textView);

        presenter = new TaskPresenter();
        presenter.attachView(this);
        presenter.loadItems();
    }

    @Override
    public void setMessage(String msg) {
        tv.setText(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void onClick(View view) {

    }
}
