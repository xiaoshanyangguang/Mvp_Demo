package com.demo.common.interf;

import com.demo.common.widget.BaseView;

import java.util.List;

/**
 * @创建者 piper
 * @创建时间 2016/9/1 19:24
 * @描述 TODO
 */
public interface BaseListView<T> extends BaseView{
    void showRefreshFinish(List<T> dates);
}
