package com.veryworks.android.mvp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.veryworks.android.mvp.fragment.MainContract;
import com.veryworks.android.mvp.fragment.MainFragment;
import com.veryworks.android.mvp.fragment.MainPresenter;

public class MainActivity extends AppCompatActivity{

    FrameLayout mainFrame;
    MainContract.Presenter presenter;
    MainFragment view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. 프레젠터 생성
        presenter = new MainPresenter();
        // 2. 뷰 생성
        view = new MainFragment();
        // 3. 생성한 뷰를 프레젠터에 넘겨준다.
        presenter.attachView(view);

        // 뷰를 메인액티비티에 세팅한다.
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.framelayout, view).commit();
    }
}
