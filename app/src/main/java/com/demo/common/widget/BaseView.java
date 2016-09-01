package com.demo.common.widget;

/**
 * @创建者 Piper
 * @创建时间 2016/6/29 0:07
 * @描述 TODO
 */
public interface BaseView {

    /**
     * showLoading 方法主要用于页面请求数据时显示加载状态
     */
    public void showLoading();

    /**
     * showEmpty 方法用于请求的数据为空的状态
     */
    public void showEmpty(String msg);

    /**
     * showNetError 方法用于网络异常
     */
    public void showNetError();

    /**
     * showNetError 方法用于请求数据完成
     */
    void loadingComplete(Object data,int recode);

    /**
     * 没有更多数据了
     */
    void hasNoMoreDate(Object hasNoMore);

    /**
     * 加载更多完成
     * @param morefinish
     */
    void loadMoreFinish(Object morefinish);

    /**
     * 刷新完成
     * @param refreshFinish
     */
    void showRefreshFinish(Object refreshFinish);

    /**
     * 展示服务器错误吐司
     */
    void showToastError(Object error);
}
