package com.demo.func.account.presenter;

import android.util.Log;

import com.demo.common.bean.GankDailyBean;
import com.demo.common.bean.GankDataBean;
import com.demo.common.dagger.scope.PerFragment;
import com.demo.common.presenter.BasePresenter;
import com.demo.func.account.model.WalletModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;

/**
 * @创建者 piper
 * @创建时间 2016/8/23 12:56
 * @描述 TODO
 */
@PerFragment
public class WalletPresenter extends BasePresenter<WalletContract.WalletView,WalletModel> implements WalletContract.WalletPresenter {
    private     Subscriber   mSubscriber;
    private Subscription mSubscription;
    @Inject
    public WalletPresenter(WalletModel baseModel) {
        super(baseModel);
    }



    @Override
    public void onsuccess(Object o, int recode) {
        switch (recode){
            case 0:
                List<GankDailyBean> gankDailyDeans = (ArrayList<GankDailyBean>) o;
                if(gankDailyDeans.size()>0){
                    if(mode==RequestMode.FRIST){
                        mUiView.loadingComplete(gankDailyDeans,recode);
                    }else if(mode==RequestMode.LOAD_MORE){
                        mUiView.loadMoreFinish(gankDailyDeans);
                    }else if(mode ==RequestMode.REFRESH){
                        mUiView.showRefreshFinish(gankDailyDeans);
                    }
                }else{
                    if(mode==RequestMode.LOAD_MORE){
                        mUiView.hasNoMoreDate(gankDailyDeans);
                    }else{
                        mUiView.showEmpty("木有更多数据了");
                    }
                }


                break;
            case 1:
                GankDataBean gankData = (GankDataBean) o;
                Log.e("GankDataBean",gankData.results.get(0).get_id()+"");
                break;
        }
//        mUiView.loadingComplete(o,recode);
    }



    @Override
    public void onFailed(Object e, int recode) {
        mUiView.showNetError();
    }

    @Override
    public void onFinish(int recode) {

    }

    @Override
    public int[] getObservableCode() {
        return new int[]{0};
    }
}
