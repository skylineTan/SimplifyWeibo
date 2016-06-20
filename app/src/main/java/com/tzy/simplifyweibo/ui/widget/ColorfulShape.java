package com.tzy.simplifyweibo.ui.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.shapes.Shape;
import com.tzy.simplifyweibo.R;
import java.util.Random;

/**
 * Created by skylineTan on 2016/5/26 19:36.
 */
public class ColorfulShape extends Shape{

    private int[] mColors = new int[]{ R.color.blue_light,R.color
            .blue_light_2,R.color.blue_light_3,R.color.purple_light,R.color
            .grey_light};

    @Override public void draw(Canvas canvas, Paint paint) {
        int position = new Random().nextInt(4);
        paint.setColor(mColors[position]);
    }
}
