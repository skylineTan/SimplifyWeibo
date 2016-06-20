package com.tzy.simplifyweibo.ui;

import android.content.Context;
import android.view.View;

/**
 * Created by skylineTan on 2016/5/29 12:26.
 */
public interface IVaryViewHelper {

    View getCurrentLayout();

    void restoreView();

    void showLayout(View view);

    View inflate(int layoutId);

    Context getContext();

    View getView();
}
