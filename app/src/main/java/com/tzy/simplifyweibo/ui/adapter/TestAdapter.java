package com.tzy.simplifyweibo.ui.adapter;

import android.content.Context;
import com.tzy.simplifyweibo.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by skylineTan on 2016/5/26 00:46.
 */
public class TestAdapter extends BaseQuickAdapter<String>{

    public TestAdapter(Context context, List<String> data) {
        super(context, R.layout.item_test, data);
    }


    @Override protected void convert(BaseViewHolder helper, String item) {

        helper.setImagesUrl(R.id.span_grid_layout,mData);
    }
}
