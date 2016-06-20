package com.tzy.simplifyweibo.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.tzy.simplifyweibo.R;
import com.tzy.simplifyweibo.SkylineApplication;
import com.tzy.simplifyweibo.model.IBaseModel;
import com.tzy.simplifyweibo.presenter.IBasePresenter;
import com.tzy.simplifyweibo.utils.TUtil;
import com.tzy.simplifyweibo.view.IBaseView;

public abstract class BaseActivity<T extends IBasePresenter,E extends
        IBaseModel> extends BaseAppCompatActivity implements IBaseView{

    public T mPresenter;
    public E mModel;
    public Context mContext;
    protected Toolbar mToolbar;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        } else {
            throw new IllegalArgumentException("You must return a right contentView layout resource Id");
        }
        mContext = this;
        mPresenter = TUtil.getT(this,0);
        mModel = TUtil.getT(this,1);
        //Presenter的start()
        initPresenter();
        initViewAndEvent();
    }


    @Override public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mToolbar = ButterKnife.findById(this, R.id.toolbar);
        TextView toolbarTitle = ButterKnife.findById(this,R.id.toolbar_title);
        if(mToolbar != null){
            setSupportActionBar(mToolbar);
            setTitle("");
            toolbarTitle.setText("SimplifyWeibo");
            //actionBar.setDisplayShowHomeEnabled()左上角图标是否显示
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setIcon(R.mipmap.like);
            //给左上角图标的左边加上一个返回的图标
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }


    @Override protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    @Override public void showLoading(String msg) {
        toggleShowLoading(true,null);
    }


    @Override public void hideLoading() {
        toggleShowLoading(false,null);
    }


    @Override public void showError(String msg) {
        toggleShowError(true,msg,null);
    }


    @Override public void showNetError() {
        toggleNetworkError(true,null);
    }


    @Override public void showException(String msg) {
        toggleShowError(true,msg,null);
    }

    protected SkylineApplication getBaseApplication(){
        return (SkylineApplication) getApplication();
    }


    protected abstract int getLayoutId();

    protected abstract void initViewAndEvent();

    protected abstract void initPresenter();
}
