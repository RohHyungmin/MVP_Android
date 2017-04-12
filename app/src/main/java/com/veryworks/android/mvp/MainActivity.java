package com.veryworks.android.mvp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.veryworks.android.mvp.fragment.MainContract;
import com.veryworks.android.mvp.fragment.MainFragment;
import com.veryworks.android.mvp.fragment.MainPresenter;

public class MainActivity extends AppCompatActivity{

    MainContract.Presenter presenter;
    MainFragment view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. 프레젠터 생성
        presenter = new MainPresenter();
        // 2. 뷰 생성 - 프래그먼트를 뷰로 사용하는 형태
        view = new MainFragment();
        // 3. 생성한 뷰를 프레젠터에 넘겨준다.
        //    프레젠터에 뷰(프레그먼트)를 넘겨주면서 MVP의 상호참조형태가 된다.
        presenter.attachView(view);

        // 뷰를 메인액티비티에 세팅한다.
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.framelayout, view).commit();
    }
}
