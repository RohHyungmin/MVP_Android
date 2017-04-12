package com.veryworks.android.mvp.task;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.veryworks.android.mvp.R;
/*
   - 액티비티를 뷰로 사용하는 형태의 MVP
     프래그먼트를 사용하는 예제와는 다르게
     xml 에 값을 세팅하는 뷰 메서드들이 액티비티에 존재하게 된다
 */
public class TaskActivity extends AppCompatActivity implements TaskContract.View, View.OnClickListener{

    private TaskContract.Presenter presenter;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        tv = (TextView) findViewById(R.id.textView);

        // 1. 프레젠터를 생성한다.
        presenter = new TaskPresenter();
        // 2. 프레젠터에 뷰(액티비티 자신)를 넘겨준다
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
