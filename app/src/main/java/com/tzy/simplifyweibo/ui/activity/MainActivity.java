package com.tzy.simplifyweibo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.Bind;
import com.tzy.simplifyweibo.R;
import com.tzy.simplifyweibo.ui.BaseActivity;
import com.tzy.simplifyweibo.ui.fragment.MainFragment;
import com.tzy.simplifyweibo.utils.NetUtils;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.container) FrameLayout container;

    private MainFragment mMainFragment;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTabSection(0);
    }


    @Override protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override protected void initViewAndEvent() {

    }


    @Override protected void initPresenter() {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mMainFragment.onActivityResult(requestCode, resultCode, data);
    }


    @Override public void onClick(View v) {

    }


    public void setTabSection(int index) {
        FragmentTransaction transaction
                = getSupportFragmentManager().beginTransaction();
        switch (index) {
            case 0:
                if (mMainFragment == null) {
                    mMainFragment = new MainFragment();
                    transaction.add(R.id.container, mMainFragment);
                }
                else {
                    transaction.show(mMainFragment);
                }
                break;
            default:
                break;
        }
        transaction.commit();
    }


    @Override protected void getBundleExtras(Bundle extras) {

    }


    @Override protected View getLoadingTargetView() {
        return null;
    }


    @Override protected void onNetworkConnected(NetUtils.NetType type) {

    }


    @Override protected void onNetworkDisConnected() {

    }
}
