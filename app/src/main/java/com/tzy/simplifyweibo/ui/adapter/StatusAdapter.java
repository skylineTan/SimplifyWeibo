package com.tzy.simplifyweibo.ui.adapter;

import android.content.Context;
import com.orhanobut.logger.Logger;
import com.tzy.simplifyweibo.R;
import com.tzy.simplifyweibo.bean.Image;
import com.tzy.simplifyweibo.bean.Status;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;

/**
 * Created by skylineTan on 2016/5/25 20:00.
 */
public class StatusAdapter extends BaseQuickAdapter<Status> {

    public StatusAdapter(Context context, List<Status> data) {
        super(context, R.layout.item_weibo, data);
    }


    @Override protected void convert(BaseViewHolder helper, Status item) {
        List<Image> imageList;
        //如果有图片
        if ((item.retweeted_status != null &&
                item.retweeted_status.pic_urls != null &&
                !item.retweeted_status.pic_urls.isEmpty()) ||
                (item != null && item.pic_urls != null)) {
            if (item.retweeted_status != null) {
                imageList = item.retweeted_status.pic_urls;
            }
            else {
                imageList = item.pic_urls;
            }
            final List<String> imageUrlList = new ArrayList<>();
            for (int i = 0; i < imageList.size(); i++) {

                imageUrlList.add(imageList.get(i).thumbnail_pic);
            }
            helper.setImagesUrl(R.id.status_span_grid_layout, imageUrlList);
        }
        //如果被转发
        if (item.retweeted_status != null) {
            helper.setStatusText(R.id.status_forward_name,
                    item.retweeted_status.user.screen_name)
                  .setStatusText(R.id.status_forward_content,
                          item.retweeted_status.text);
        }
        helper.setStatusText(R.id.status_content, item.text)
              .setText(R.id.status_name_date, item.user.screen_name)
              .setText(R.id.status_comment_count,
                      String.valueOf(item.comments_count))
              .setText(R.id.status_like_count,
                      String.valueOf(item.attitudes_count))
              .setImageUrl(R.id.status_face, item.user.profile_image_url)
        .setOnClickListener(R.id.status_content,new OnItemChildClickListener());
    }
}
