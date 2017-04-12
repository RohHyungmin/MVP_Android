package com.veryworks.android.mvp.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.veryworks.android.mvp.R;
import com.veryworks.android.mvp.task.TaskActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements MainContract.View, View.OnClickListener{

    TextView tv;
    Button button;
    MainContract.Presenter presenter;
    View mainView;

    public MainFragment() {
        // Required empty public constructor
    }

    public void setPresenter(MainContract.Presenter presenter){
        this.presenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_task, container, false);
        tv = (TextView) mainView.findViewById(R.id.textView2);
        button = (Button) mainView.findViewById(R.id.button);
        button.setOnClickListener(this);
        return mainView;
    }

    @Override
    public void setMessage(String msg) {
        tv.setText(msg);
    }

    @Override
    public void onClick(View view) {
        Log.i("MainActivty","View ID="+view.getId());
        switch (view.getId()) {
            case R.id.button:
                presenter.loadActivity(mainView.getContext());
        }
    }
}
