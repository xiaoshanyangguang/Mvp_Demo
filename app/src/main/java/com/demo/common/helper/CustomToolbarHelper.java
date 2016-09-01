package com.demo.common.helper;

import android.content.Context;

import com.demo.R;
import com.demo.common.base.BaseToolbarHelper;
import com.demo.common.widget.CustemToolBar;

/**
 * @创建者 Piper
 * @创建时间 2016/6/21 23:33
 * @描述 TODO
 */
public class CustomToolbarHelper extends BaseToolbarHelper<CustemToolBar> {

    public CustomToolbarHelper(Context context, int layoutId) {
        super(context, layoutId);
    }
    /**
     * 利用泛型覆写此方法提供不同的自定义toolbar
     * @return
     */
    @Override
    public int getToolBarLayoutId(){
        return R.layout.include_toolbar;
    }

}
