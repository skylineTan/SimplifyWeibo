package com.tzy.simplifyweibo.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.orhanobut.logger.Logger;
import com.tzy.simplifyweibo.R;

public class TopicActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        Toast.makeText(this,"TopicActivity",Toast.LENGTH_SHORT).show();
        Logger.d("TopicActivity");
    }
}
