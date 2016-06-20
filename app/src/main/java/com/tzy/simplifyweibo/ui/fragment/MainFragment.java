package com.tzy.simplifyweibo.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.orhanobut.logger.Logger;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.openapi.models.User;
import com.tzy.simplifyweibo.R;
import com.tzy.simplifyweibo.bean.Group;
import com.tzy.simplifyweibo.bean.Status;
import com.tzy.simplifyweibo.model.impl.MainModel;
import com.tzy.simplifyweibo.presenter.impl.MainPresenter;
import com.tzy.simplifyweibo.ui.EndlessRecyclerOnScrollListener;
import com.tzy.simplifyweibo.ui.adapter.BaseQuickAdapter;
import com.tzy.simplifyweibo.ui.adapter.GroupAdapter;
import com.tzy.simplifyweibo.ui.adapter.ListDropDownAdapter;
import com.tzy.simplifyweibo.ui.adapter.StatusAdapter;
import com.tzy.simplifyweibo.ui.widget.DropDownMenu;
import com.tzy.simplifyweibo.utils.AccessTokenKeeper;
import com.tzy.simplifyweibo.utils.FontHelper;
import com.tzy.simplifyweibo.view.IMainView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by skylineTan on 2016/5/29 12:11.
 */
public class MainFragment extends BaseLazyFragment<MainPresenter, MainModel>
        implements IMainView,
        BaseQuickAdapter.RequestLoadMoreListener,
        SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.main_drop_down_menu) DropDownMenu mDropDownMenu;

    private StatusAdapter mStatusAdapter;
    private List<Status> mStatusList;
    private ListDropDownAdapter mListDropDownAdapter;
    private GroupAdapter mGroupAdapter;
    private List<View> viewList;
    private RecyclerView mainRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private int mCurrentCounter;
    private boolean isFirst = true;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.authorizeUser();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.authorizeCallBack(requestCode, resultCode, data);
    }


    @Override protected void onFirstUserVisible() {
        Logger.d("onFirstUserVisible");
    }


    @Override protected void onUserVisible() {
        Logger.d("onUserVisible");
    }


    @Override protected void onFirstUserInVisible() {
        Logger.d("onFirstUserInVisible");
    }


    @Override protected void onUserInVisible() {
        Logger.d("onUserInVisible");
    }


    @Override protected View getLoadingTargetView() {
        return getLoadingView();
    }


    @Override protected void initViewsAndEvents() {
        Logger.d("initViewsAndEvents()");
        View view = LayoutInflater.from(getActivity())
                                  .inflate(R.layout.include_recycler_view,
                                          null);
        mainRecyclerView = ButterKnife.findById(view, R.id.main_recycler_view);
        mSwipeRefreshLayout = ButterKnife.findById(view,
                R.id.main_swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        //FontHelper.applyFont(getActivity(), ButterKnife.findViewById(R.id
        //        .activity_main_root),
        //        "assets/fonts/noto.ttf");
        mainRecyclerView.setLayoutManager(
                new LinearLayoutManager(getActivity()));
        ListView listView = new ListView(getActivity());
        RecyclerView recyclerView = new RecyclerView(getActivity());
        String[] headers = { "分组", "类别" };
        List<String> groupList = new ArrayList<>();
        groupList.add("背景");
        groupList.add("背景");
        groupList.add("背景");
        mListDropDownAdapter = new ListDropDownAdapter(getActivity(),
                groupList);
        listView.setAdapter(mListDropDownAdapter);
        List<Group> typeList = new ArrayList<>();
        typeList.add(new Group("12"));
        typeList.add(new Group("34"));
        typeList.add(new Group("56"));
        mGroupAdapter = new GroupAdapter(getActivity(), typeList);
        recyclerView.setAdapter(mGroupAdapter);
        viewList = new ArrayList<>();
        viewList.add(recyclerView);
        viewList.add(listView);
        //View contentView = LayoutInflater.from(this).inflate(R.layout
        //        .content_main,null);
        TextView contentView = new TextView(getActivity());
        contentView.setLayoutParams(
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
        contentView.setText("内容显示区域");
        contentView.setGravity(Gravity.CENTER);
        contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), viewList, view);
    }


    @Override protected int getContentViewLayoutID() {
        return R.layout.fragment_main;
    }


    @Override protected void initPresenter() {
        mPresenter.setVM(this, mModel);
    }


    @Override public void showUser(User user) {
        Toast.makeText(getActivity(), user.toString(), Toast.LENGTH_SHORT)
             .show();
        Logger.d(user.toString());
        Oauth2AccessToken accessToken = AccessTokenKeeper.readAccessToken(
                getActivity());
        String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(
                new Date(accessToken.getExpiresTime()));
        String format = getString(
                R.string.weibosdk_demo_token_to_string_format_1);
        String content = String.format(format, accessToken.getToken(), date);
        Logger.d(content);
        mPresenter.loadStatusList(accessToken.getToken(), 1);
    }


    @Override public void showStatusList(List<Status> statusList) {
        mStatusList = statusList;
        mStatusAdapter = new StatusAdapter(getActivity(), mStatusList);
        mStatusAdapter.setOnLoadMoreListener(this);
        mStatusAdapter.openLoadMore(20, true);
        mStatusAdapter.setOnRecyclerViewItemClickListener(
                (view, position) -> Toast.makeText(getActivity(),
                        "点击了首页的RecyclerView", Toast.LENGTH_SHORT).show());
        mStatusAdapter.setOnRecyclerViewItemChildClickListener(
                (adapter, view, position) -> {
                    switch (view.getId()) {
                        case R.id.status_content:
                            break;
                    }
                    Toast.makeText(getActivity(),
                            "您点击了MainFragment里面的一个Item " + "child",
                            Toast.LENGTH_LONG).show();
                });
        mainRecyclerView.setAdapter(mStatusAdapter);
        isFirst = false;
    }


    @Override public void showMoreStatusList(List<Status> statusList) {
        mCurrentCounter = statusList.size();
        //mStatusAdapter.addData(statusList);
        mStatusAdapter.notifyDataChangedAfterLoadMore(statusList, true);
        Logger.d(mStatusList.toString());
    }


    public View getFooterView() {
        return getActivity().getLayoutInflater()
                            .inflate(R.layout.view_foot_no_more,
                                    (ViewGroup) mainRecyclerView.getParent(),
                                    false);
    }


    private View getLoadingView() {
        return getActivity().getLayoutInflater()
                            .inflate(R.layout.view_foot_loading, null);
    }


    @Override public void showProgress() {
        mSwipeRefreshLayout.setRefreshing(true);
    }


    @Override public void dismissProgress() {
        mSwipeRefreshLayout.setRefreshing(false);
    }


    @Override public void onLoadMoreRequested() {
        mainRecyclerView.post(() -> {
            //mStatusAdapter.addFooterView(getView());
            //在不是第一次加载数据的时候，mCurrentCounter有数据才进行加载下一页
            if (!isFirst && mCurrentCounter != 0) {
                Oauth2AccessToken accessToken
                        = AccessTokenKeeper.readAccessToken(getActivity());
                mPresenter.loadMoreStatusList(accessToken.getToken(), 1);
            }
            else {
                mStatusAdapter.notifyDataChangedAfterLoadMore(false);
                mStatusAdapter.addFooterView(getFooterView());
            }
        });
    }


    @Override public void onRefresh() {
        Oauth2AccessToken accessToken = AccessTokenKeeper.readAccessToken(
                getActivity());
        mPresenter.loadStatusList(accessToken.getToken(), 1);
    }
}
