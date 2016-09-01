package com.demo.func.home.presenter;

import com.demo.common.widget.BaseView;

/**
 * @创建者 piper
 * @创建时间 2016/8/21 22:38
 * @描述 TODO
 */
public interface NavigationContract {
    interface NavigationPresenter  {
    }
    interface NavigationUiView extends BaseView {
        void choose();
    }
}
