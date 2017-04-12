package com.veryworks.android.mvp.task;

import android.content.Context;

/**
 * Created by pc on 1/22/2017.
 */

public interface TaskContract {
    interface View {
        void setMessage(String msg);
    }

    interface Presenter {
        void attachView(View view);
        void detachView();

        void loadItems();
    }
}
