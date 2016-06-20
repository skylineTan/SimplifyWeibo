package com.tzy.simplifyweibo.utils;

import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.orhanobut.logger.Logger;
import com.tzy.simplifyweibo.R;
import com.tzy.simplifyweibo.ui.widget.ColorfulDrawable;
import com.tzy.simplifyweibo.ui.widget.ColorfulShape;

/**
 * Created by skylineTan on 2016/5/25 20:15.
 */
public class GlideHelper {

    private static GlideHelper instance;


    private GlideHelper() {

    }


    public static GlideHelper getInstance() {
        if (instance == null) {
            synchronized (GlideHelper.class) {
                if (instance == null) {
                    instance = new GlideHelper();
                }
            }
        }
        return instance;
    }


    public void loadImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
             .load(url)
             .asBitmap()
             .centerCrop()
             .placeholder(R.drawable.place_iv_shape)
             .error(R.drawable.place_iv_shape)
             .diskCacheStrategy(DiskCacheStrategy.ALL)
             .into(imageView);
    }
}
