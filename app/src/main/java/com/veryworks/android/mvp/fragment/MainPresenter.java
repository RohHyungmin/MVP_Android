package com.veryworks.android.mvp.fragment;

import android.content.Context;
import android.content.Intent;

import com.veryworks.android.mvp.task.TaskActivity;

/**
 * Created by pc on 1/22/2017.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;

    @Override
    public void attachView(MainContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void loadItems() {
        view.setMessage("Main Activity!!!");
    }

    @Override
    public void loadActivity(Context context) {
        Intent intent = new Intent(context, TaskActivity.class);
        context.startActivity(intent);
    }
}
