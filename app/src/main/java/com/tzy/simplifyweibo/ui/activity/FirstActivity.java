package com.tzy.simplifyweibo.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.tzy.simplifyweibo.R;
import com.tzy.simplifyweibo.bean.Status;
import com.tzy.simplifyweibo.ui.adapter.StatusAdapter;
import com.tzy.simplifyweibo.ui.adapter.TestAdapter;
import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends AppCompatActivity {

    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    private TestAdapter mStatusAdapter;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ButterKnife.bind(this);

        List<String> stringList = new ArrayList<>();
        stringList.add("123");
        stringList.add("123");
        stringList.add("123");
        stringList.add("123");
        stringList.add("123");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mStatusAdapter = new TestAdapter(this,stringList);
        recyclerView.setAdapter(mStatusAdapter);
    }
}
