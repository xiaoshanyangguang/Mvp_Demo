package com.demo.func.home.presenter;

import android.util.Log;

import com.demo.common.bean.GankDailyBean;
import com.demo.common.bean.GankDataBean;
import com.demo.common.dagger.scope.PerActivity;
import com.demo.common.presenter.BasePresenter;
import com.demo.func.home.common.EasyDate;
import com.demo.func.home.model.MainModel;

import javax.inject.Inject;

/**
 * @创建者 Piper
 * @创建时间 2016/7/11 22:38
 * @描述 TODO
 */
@PerActivity
public class MainPresenter extends BasePresenter<MainContract.MainView,MainModel> implements MainContract.MainPresenter  {
    private EasyDate mEasyDate;
    @Inject
    public MainPresenter(MainModel mMainModel){
       super(mMainModel);
    }


    @Override
    public void onsuccess(Object data, int recode) {
        switch (recode){
            case 0:
                GankDailyBean gankDailyDean = (GankDailyBean) data;
                Log.e("GankDailyBean",gankDailyDean.getCategory()+"");
                mUiView.loadingComplete(data,recode);
                break;
            case 1:
                GankDataBean gankData = (GankDataBean) data;
                Log.e("GankDataBean",gankData.results.get(0).get_id()+"");
                break;
        }
    }

    @Override
    public void onFailed(Object e, int recode) {
        mUiView.showToastError(e);
    }

    @Override
    public void onFinish(int recode) {

    }

    @Override
    public int[] getObservableCode() {
        return null;
    }


}
