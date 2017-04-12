package com.veryworks.android.mvp.task;

import android.content.Context;

/**
 * Created by pc on 1/22/2017.
 */

public class TaskPresenter implements TaskContract.Presenter {

    private TaskContract.View view;

    @Override
    public void attachView(TaskContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void loadItems() {
        view.setMessage("HelloWorld");
    }
}
