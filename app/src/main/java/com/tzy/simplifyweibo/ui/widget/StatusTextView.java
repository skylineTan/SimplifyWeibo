package com.tzy.simplifyweibo.ui.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.util.AttributeSet;
import android.widget.TextView;
import com.tzy.simplifyweibo.SkylineApplication;
import com.tzy.simplifyweibo.support.Constants;
import com.tzy.simplifyweibo.di.components.DaggerViewComponent;
import com.tzy.simplifyweibo.di.modules.ViewModule;
import com.tzy.simplifyweibo.support.MyURLSpan;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;

/**
 * Created by skylineTan on 2016/5/31 11:40.
 * From:Aisen
 * @author wangdan
 */
public class StatusTextView extends TextView{

    @Inject ThreadPoolExecutor mThreadPoolExecutor;

    public StatusTextView(Context context) {
        super(context);
    }


    public StatusTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public StatusTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setContent(String text){
        DaggerViewComponent.builder()
                .appComponent(SkylineApplication.getContext().getAppComponent())
                .viewModule(new ViewModule(this))
                .build()
                .inject(this);
        mThreadPoolExecutor.execute(() -> {
            if(text == null || TextUtils.isEmpty(text)){
                return;
            }
            setClickable(true);

            SpannableString spannableString = SpannableString.valueOf(text);

            //表情
            Matcher matcher = Pattern.compile("\\[(\\S+?)\\]").matcher
                    (spannableString);

            //用户名称
            Pattern mentionPattern = Pattern.compile
                    ("@[\\w\\p{InCJKUnifiedIdeographs}-]{1,26}");
            String scheme = Constants.Scheme.MENTIONS;
            Linkify.addLinks(spannableString,mentionPattern,scheme);

            //网页链接
            scheme = "http://";
            Linkify.addLinks(spannableString,Pattern.compile
                    ("http://[a-zA-Z0-9+&@#/%?=~_\\-|!:,\\.;" +
                            "]*[a-zA-Z0-9+&@#/%=~_|]"),scheme);

            //话题
            Pattern topicPattern = Pattern.compile
                    ("#[\\p{Print}\\p{InCJKUnifiedIdeographs}&&[^#]]+#");
            scheme = Constants.Scheme.TOPICS;
            Linkify.addLinks(spannableString,topicPattern,scheme);


            URLSpan[] urlSpans = spannableString.getSpans(0,spannableString
                    .length(),URLSpan.class);
            MyURLSpan myURLSpan = null;
            for(URLSpan urlSpan : urlSpans){
                myURLSpan = new MyURLSpan(urlSpan.getURL());
                int start = spannableString.getSpanStart(urlSpan);
                int end = spannableString.getSpanEnd(urlSpan);
                spannableString.removeSpan(urlSpan);
                spannableString.setSpan(myURLSpan,start,end, 0);
            }

            new Handler(Looper.getMainLooper()).post(() -> {
                setText(spannableString);
                setMovementMethod(LinkMovementMethod.getInstance());
            });
        });
    }

    class MyMatchFilter implements Linkify.MatchFilter{

        //MatchFilter来为RegEx样式匹配添加额外的条件
        //下面的代码是取消任何之前已“.”结尾的匹配
        @Override
        public boolean acceptMatch(CharSequence s, int start, int end) {
            return s.charAt(end - 1) != '.';
        }
    }

    class MyTransformFilter implements Linkify.TransformFilter{

        //返回需要传递的内容
        @Override public String transformUrl(Matcher match, String url) {
            return "网页链接";
        }
    }
}
