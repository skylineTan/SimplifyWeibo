package com.tzy.simplifyweibo.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by skylineTan on 2016/5/26 19:19.
 */
public class ColorfulImageView extends ImageView{

    public ColorfulImageView(Context context) {
        this(context,null);
    }


    public ColorfulImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }


    public ColorfulImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ColorfulImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
