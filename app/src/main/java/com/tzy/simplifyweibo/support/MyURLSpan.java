package com.tzy.simplifyweibo.support;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcel;
import android.text.ParcelableSpan;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import com.orhanobut.logger.Logger;

/**
 * Created by skylineTan on 2016/5/31 21:42.
 */
public class MyURLSpan extends ClickableSpan implements ParcelableSpan{

    private final String mURL;
    private int color;


    //构造函数
    public MyURLSpan(String URL) {
        mURL = URL;
    }

    public MyURLSpan(Parcel src){
        this.mURL = src.readString();
    }

    //getter setter
    public String getURL() {
        return mURL;
    }


    public void setColor(int color) {
        this.color = color;
    }


    @Override public void onClick(View widget) {
        Uri uri = Uri.parse(this.getURL());
        Logger.d("uri---->"+uri.toString());
        Logger.d("url---->"+this.getURL());
        Logger.d("scheme-->"+uri.getScheme());
        Context context = widget.getContext();
        Intent intent;
        //如果是网页链接
        if(uri.getScheme().startsWith("http")){
            intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(uri);
            intent.setFlags(268435456);
            context.startActivity(intent);
        }else {
            intent = new Intent("android.intent.action.VIEW",uri);
            intent.putExtra("com.android.browser.application_id", context
                    .getPackageName());
            context.startActivity(intent);
        }
    }


    @Override public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        //去掉链接的下划线
        ds.setUnderlineText(false);
        //设置颜色
        if(color != 0){
            ds.setColor(color);
        }else {
            ds.setColor(ds.linkColor);
        }
    }


    //ParcelableSpan
    @Override public int getSpanTypeId() {
        return 11;
    }


    @Override public int describeContents() {
        return 0;
    }


    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mURL);
    }
}
