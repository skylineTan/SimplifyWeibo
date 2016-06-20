package com.tzy.simplifyweibo.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by skylineTan on 2016/5/29 12:28.
 */
public class VaryViewHelper implements IVaryViewHelper{

    private View view;
    private ViewGroup parentView;
    private int viewIndex;
    private ViewGroup.LayoutParams params;
    private View currentView;


    public VaryViewHelper(View view) {
        super();
        this.view = view;
    }

    private void init(){
        //传进来的view的布局参数
        params = view.getLayoutParams();
        if(view.getParent() != null){
            parentView = (ViewGroup) view.getParent();
        }else {
            //setContentView的父布局 也就是View的layout里面的根布局的parent
            parentView = (ViewGroup) view.getRootView().findViewById(android.R.id.content);
        }
        //循环遍历parentView的子View，得到当前view的index
        int count = parentView.getChildCount();
        for(int index = 0;index < count;index++){
            if(view == parentView.getChildAt(index)){
                viewIndex = index;
                break;
            }
        }
        currentView = view;
    }


    @Override public View getCurrentLayout() {
        return currentView;
    }


    @Override public void restoreView() {
        showLayout(view);
    }


    @Override public void showLayout(View view) {
        //也就是parenetView还没有被赋值
        if(parentView == null){
            init();
        }

        //如果parentView已经被初始化 也就是可以传进来另外的View进行显示

        this.currentView = view;
        if(parentView.getChildAt(viewIndex) != view){
            ViewGroup parent = (ViewGroup) view.getParent();
            if(parent != null){
                parent.removeView(view);
            }
            //把原来index位置的View移除，在index位置add当前View
            parent.removeViewAt(viewIndex);
            parent.addView(view,viewIndex,params);
        }
    }


    @Override public View inflate(int layoutId) {
        return LayoutInflater.from(view.getContext()).inflate(layoutId,null);
    }


    @Override public Context getContext() {
        return view.getContext();
    }


    @Override public View getView() {
        return view;
    }
}
