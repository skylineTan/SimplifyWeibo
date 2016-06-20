package com.tzy.simplifyweibo.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.orhanobut.logger.Logger;
import com.tzy.simplifyweibo.R;
import com.tzy.simplifyweibo.utils.DensityUtils;
import com.tzy.simplifyweibo.utils.GlideHelper;
import com.tzy.simplifyweibo.utils.ScreenUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by skylineTan on 2016/5/22 15:51.
 */
public class SpanGridLayout extends ViewGroup {

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    private List<String> mDataList;
    private int columns;
    private int rows;
    private int mDefWidth, mDefHeight, width, height;
    private int gap;
    private int defMargin;


    public SpanGridLayout(Context context) {
        this(context, null);
    }


    public SpanGridLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public SpanGridLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SpanGridLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    //在width和height都是wrap_content的时候，给它一个默认的宽度和高度
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthSpecMode == MeasureSpec.AT_MOST &&
                heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mDefWidth, mDefHeight);
        }
        else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mDefWidth, heightSpecSize);
        }
        else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, mDefHeight);
        }
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        width = getWidth();
        height = getHeight();
    }


    private void init() {
        mDataList = new ArrayList<>();
        //mDefWidth = DensityUtils.getScreenWidth(getContext()) -
        //        DensityUtils.dp2px(getContext(), 40);
        mDefHeight = DensityUtils.dp2px(getContext(), 350);
        mDefWidth = mDefHeight;
        gap = DensityUtils.dp2px(getContext(), 4);
    }


    public void setData(List<String> dataList) {
        if (dataList == null || dataList.isEmpty()) {
            this.setVisibility(GONE);
            return;
        }
        this.setVisibility(VISIBLE);
        removeAllViews();
        for (int i = 0; i < dataList.size(); i++) {
            ImageView iv = generateImageView();
            addView(iv, generateDefaultLayoutParams());
        }
        mDataList = dataList;
        layoutChildrenView();
    }


    private void layoutChildrenView() {
        int childrenCount = mDataList.size();

        int single = (mDefWidth - 2 * gap) / 3;
        int two = (mDefWidth - gap) / 2;

        switch (childrenCount) {
            case 1:
                ImageView iv = (ImageView) getChildAt(0);
                iv.layout(0, 0, mDefWidth, mDefHeight);
                GlideHelper.getInstance().loadImage(getContext(),
                        mDataList.get(0),iv);
                break;
            case 2:
                for (int i = 0; i < 2; i++) {
                    ImageView iv2 = (ImageView) getChildAt(i);
                    iv2.layout(0, i * (gap + (mDefHeight - gap) / 2), mDefWidth,
                            (int) (mDefHeight / (2 * Math.pow(2, -i))));
                    GlideHelper.getInstance().loadImage(getContext(),
                            mDataList.get(i),iv2);
        }
                break;
            case 3:
                getChildAt(0).layout(0, 0, mDefWidth, gap + single * 2);
                GlideHelper.getInstance().loadImage(getContext(),
                        mDataList.get(0),(ImageView) getChildAt(0));
                for (int i = 0; i < 2; i++) {
                    ImageView iv3 = (ImageView) getChildAt(i + 1);
                    iv3.layout((two + gap) * i, 2 * gap + single * 2,
                            gap * i + two * (i + 1), mDefHeight);
                    GlideHelper.getInstance().loadImage(getContext(),
                            mDataList.get(i + 1),iv3);
                }
                break;
            case 4:
                getChildAt(0).layout(0, 0, mDefWidth, gap + 2 * single);
                GlideHelper.getInstance().loadImage(getContext(),
                        mDataList.get(0),(ImageView) getChildAt(0));
                for (int i = 0; i < 3; i++) {
                    ImageView iv4 = (ImageView) getChildAt(i + 1);
                    iv4.layout(gap * i + single * i, 2 * gap + 2 * single,
                            gap * i + single * (i + 1), mDefHeight);
                    GlideHelper.getInstance().loadImage(getContext(),
                            mDataList.get(i + 1),iv4);
                }
                break;
            case 5:
                getChildAt(0).layout(0, 0, 2 * single + gap, 2 * single + gap);
                GlideHelper.getInstance().loadImage(getContext(),
                        mDataList.get(0),(ImageView) getChildAt(0));
                for (int i = 0; i < 2; i++) {
                    getChildAt(i + 1).layout(2 * gap + 2 * single,
                            gap * i + single * i, mDefWidth,
                            gap * i + single * (i + 1));
                    GlideHelper.getInstance().loadImage(getContext(),
                            mDataList.get(i + 1),(ImageView) getChildAt(i + 1));
                }
                for (int i = 0; i < 2; i++) {
                    getChildAt(i + 3).layout((gap + two) * i,
                            2 * single + 2 * gap, gap * i + two * (i + 1),
                            mDefHeight);
                    GlideHelper.getInstance().loadImage(getContext(),
                            mDataList.get(i + 1),(ImageView) getChildAt(i + 3));
                }
                break;
            case 6:
                getChildAt(0).layout(0, 0, 2 * single + gap, 2 * single + gap);
                GlideHelper.getInstance().loadImage(getContext(),
                        mDataList.get(0),(ImageView) getChildAt(0));
                for (int i = 0; i < 2; i++) {
                    getChildAt(i + 1).layout(2 * gap + 2 * single,
                            gap * i + single * i, mDefWidth,
                            gap * i + single * (i + 1));
                    GlideHelper.getInstance().loadImage(getContext(),
                            mDataList.get(i + 1),(ImageView) getChildAt(i + 1));
                }
                for (int i = 0; i < 3; i++) {
                    getChildAt(i + 3).layout((gap + single) * i,
                            2 * single + 2 * gap, gap * i + single * (i + 1),
                            mDefHeight);
                    GlideHelper.getInstance().loadImage(getContext(),
                            mDataList.get(i + 3),(ImageView) getChildAt(i + 3));
                }
                break;
            case 7:
                for (int i = 0; i < 2; i++) {
                    getChildAt(i).layout(0, (gap + single) * i,
                            2 * single + gap, gap * i + single * (i + 1));
                    GlideHelper.getInstance().loadImage(getContext(),
                            mDataList.get(i),(ImageView) getChildAt(i));
                }
                for (int i = 0; i < 2; i++) {
                    getChildAt(i + 2).layout(2 * gap + 2 * single,
                            gap * i + single * i, mDefWidth,
                            gap * i + single * (i + 1));
                    GlideHelper.getInstance().loadImage(getContext(),
                            mDataList.get(i + 2),(ImageView) getChildAt(i + 2));
                }
                for (int i = 0; i < 3; i++) {
                    getChildAt(i + 4).layout((gap + single) * i,
                            2 * single + 2 * gap, gap * i + single * (i + 1),
                            mDefHeight);
                    GlideHelper.getInstance().loadImage(getContext(),
                            mDataList.get(i + 4),(ImageView) getChildAt(i + 4));
                }
                break;
            case 8:
                for (int i = 0; i < 2; i++) {
                    getChildAt(i).layout((gap + two) * i, 0,
                            gap * i + two * (i + 1), single);
                    GlideHelper.getInstance().loadImage(getContext(),
                            mDataList.get(i),(ImageView) getChildAt(i));
                }
                for (int i = 0; i < 2; i++) {
                    for (int j = 2; j < 5; j++) {
                        getChildAt(j + 3 * i).layout((gap + single) * (j - 2),
                                (i +1)* (gap + single),
                                gap * (j - 2) + single * (j - 2 + 1),
                                single * (i + 2) + gap * (i +1));
                        GlideHelper.getInstance().loadImage(getContext(),
                                mDataList.get(j + 3 *i),(ImageView) getChildAt
                                        (j + 3 * i));
                    }
                }
                break;
            case 9:
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        getChildAt(j + 3 * i).layout((gap + single) * j,
                                i * (gap + single), gap * j + single * (j + 1),
                                single * (i + 1) + gap * i);
                        GlideHelper.getInstance().loadImage(getContext(),
                                mDataList.get(j + 3 * i),(ImageView) getChildAt(j + 3 * i));
                    }
                }
                break;
            default:
                break;
        }
        requestLayout();
    }


    private ImageView generateImageView() {
        ImageView iv = new ImageView(getContext());
        //iv.setBackgroundColor(Color.parseColor("#ffffff"));
        return iv;
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }


    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }


    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View v, int position);
    }
}
