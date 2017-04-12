package com.veryworks.android.mvp.fragment;

import android.content.Context;

/**
 * Created by pc on 1/22/2017.
 */

public interface MainContract {
    interface View {
        void setMessage(String msg);
        void setPresenter(Presenter presenter);
    }

    interface Presenter {
        void attachView(View view);
        void detachView();
        void loadItems();
        void loadActivity(Context context);
    }
}
