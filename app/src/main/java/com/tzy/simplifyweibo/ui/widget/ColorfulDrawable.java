package com.tzy.simplifyweibo.ui.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import com.tzy.simplifyweibo.R;
import java.util.Random;

/**
 * Created by skylineTan on 2016/5/26 19:28.
 */
public class ColorfulDrawable extends ShapeDrawable{

    private int[] mColors = new int[]{ R.color.blue_light,R.color
            .blue_light_2,R.color.blue_light_3,R.color.purple_light,R.color
            .grey_light};

    @Override public void draw(Canvas canvas) {
        int position = new Random().nextInt(4);
        canvas.drawColor(mColors[position]);
        super.draw(canvas);
    }
}
