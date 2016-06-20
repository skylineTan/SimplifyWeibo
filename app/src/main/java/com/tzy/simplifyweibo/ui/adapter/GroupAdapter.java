package com.tzy.simplifyweibo.ui.adapter;

import android.content.Context;
import com.tzy.simplifyweibo.R;
import com.tzy.simplifyweibo.bean.Group;
import java.util.List;

/**
 * Created by skylineTan on 2016/5/29 18:27.
 */
public class GroupAdapter extends BaseQuickAdapter<Group>{

    public GroupAdapter(Context context, List<Group> data) {
        super(context, R.layout.item_drop_down,data);
    }


    @Override protected void convert(BaseViewHolder helper, Group item) {
        helper.setText(R.id.drop_down_text,item.group);
    }
}
