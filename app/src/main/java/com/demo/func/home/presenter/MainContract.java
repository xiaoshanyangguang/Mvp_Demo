package com.demo.func.home.presenter;

import com.demo.common.widget.BaseView;

/**
 * @创建者 piper
 * @创建时间 2016/8/10 20:14
 * @描述 TODO
 */
public interface MainContract {
    interface MainPresenter  {

    }
    interface MainView extends BaseView{
        void jumpToNext();
    }
}
