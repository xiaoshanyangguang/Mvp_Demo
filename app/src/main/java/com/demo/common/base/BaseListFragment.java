package com.demo.common.base;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.demo.R;
import com.demo.common.adapter.RecycleViewAdapter;
import com.demo.common.bean.BaseBean;
import com.demo.common.presenter.BasePresenter;
import com.demo.common.refresh.ProgressStyle;
import com.demo.common.refresh.XRecyclerView;
import com.demo.common.util.L;
import com.demo.common.util.ToastUtil;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * @创建者 piper
 * @创建时间 2016/8/24 14:58
 * @描述 TODO
 */
public abstract class BaseListFragment<T extends BasePresenter,K extends BaseBean> extends BaseNoToolBarFragment implements XRecyclerView.LoadingListener, BaseViewHolder.OnItemClickListener {

    @Bind(R.id.xrecycleview)
    protected XRecyclerView mRecyclerView;


    @Inject
    Context mContext;
    protected RecycleViewAdapter<K> mAdapter;
    @Override
    protected void initView(View contentView) {
        showLoading();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recycleview;
    }

    @Override
    public View getLoadingTargetView() {
        return mRecyclerView;
    }

    @Override
    public void loadingComplete(Object data,int recode) {
        mController.restore();
        if (!(data instanceof List)){
            throw new IllegalArgumentException("list fragment 的返回值必须为list");
        }

        List mData = (List)data;
        if (mAdapter == null) {
            mAdapter = new RecycleViewAdapter<K>(getItemLayout(), mData) {

                @Override
                protected void onBindRecycleViewHolder(BaseViewHolder holder, K item) {
                    fitDates(holder, item);
                }
            };

            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
            mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
            mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
            mRecyclerView.setLoadingListener(this);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
        mAdapter.setOnItemClickListener(this);
    }

    /**
     * onBindViewHolder()中执行,设置view
     */
    protected abstract void fitDates(BaseViewHolder holder, K bean);

    public abstract int getItemLayout() ;

    @Override
    public void onRefresh() {
//        showLoading();
        mPresenter.requestData(mPresenter.getObservableCode(), BasePresenter.RequestMode.REFRESH);
    }

    @Override
    public void onLoadMore() {
//        showLoading();
        mPresenter.requestData(mPresenter.getObservableCode(), BasePresenter.RequestMode.LOAD_MORE);
    }


    @Override
    public void onItemClick(View convertView, int position) {

    }

    @Override
    public void showLoading() {
        if (mController == null) {
            throw new IllegalStateException("no ViewHelperController");
        }
        mController.showLoading();
    }

    @Override
    public void showEmpty(String msg) {
        if (mController == null) {
            throw new IllegalStateException("no ViewHelperController");
        }
        mController.showEmpty(msg);
    }

    @Override
    public void showNetError() {
        if (mController == null) {
            throw new IllegalStateException("no ViewHelperController");
        }
        mController.showNetworkError(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoading();
                mPresenter.requestData(mPresenter.getObservableCode(),BasePresenter.RequestMode.FRIST);
            }
        });
    }

    @Override
    public void loadMoreFinish(Object morefinish) {
        L.e("loadMoreFinish");
        mRecyclerView.loadMoreComplete();
        mAdapter.addDataAndNotify((List<K>) morefinish);
    }
    @Override
    public void hasNoMoreDate(Object hasNoMore) {
        mRecyclerView.loadMoreComplete();
        ToastUtil.showToast(mActivity,"没有更多数据了");
    }

    @Override
    public void showRefreshFinish(Object refreshFinish) {
        mRecyclerView.refreshComplete();
        mAdapter.setNewData((List<K>) refreshFinish);
    }
}
