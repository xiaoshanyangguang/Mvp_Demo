package com.demo.func.account.model;

import com.anupcowkur.reservoir.Reservoir;
import com.demo.common.bean.GankDailyBean;
import com.demo.common.bean.GankDataBean;
import com.demo.common.dagger.scope.PerFragment;
import com.demo.common.model.BaseModel;
import com.demo.common.util.L;
import com.demo.func.account.data.ContentLocalDataSource;
import com.demo.func.home.common.EasyDate;
import com.demo.func.home.model.forum.MainApi;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * @创建者 piper
 * @创建时间 2016/8/23 13:00
 * @描述 TODO
 */
@PerFragment
public class WalletModel extends BaseModel {
    MainApi mMainApi;


    @Inject
    public WalletModel(MainApi mainApi){
        mMainApi = mainApi;
    }

    @Override
    public Observable getObservable(int recode) {
        L.e("getObservable"+recode);
        if(recode<0){
            throw new IllegalArgumentException("api接口的recode不能小于0");
        }
        switch (recode){
            case 0:
                Observable<List<GankDailyBean>> dailyDataByNetwork = (Observable<List<GankDailyBean>>) mMainApi
                        .getDailyDataByNetwork(getEasyData());


                Observable<List<GankDailyBean>> dailyDataByNetworkWithlocalUpdate = dailyDataByNetwork.observeOn(AndroidSchedulers
                        .mainThread()).doOnNext(new Action1<List<GankDailyBean>>() {
                    @Override
                    public void call(List<GankDailyBean> gankDailyBeen) {
                        L.e("doOnNext");
                        if (gankDailyBeen != null) {
                            try {
                                Reservoir.put("GankDailyBean", gankDailyBeen);
                                Reservoir.put("GankDailyBeanTime", System.currentTimeMillis());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
//                int i=0;
//                if(i==0){
//                    return dailyDataByNetworkWithlocalUpdate;
//                }
                ContentLocalDataSource data= new ContentLocalDataSource();
                Observable<List<GankDailyBean>> gankDailyBeanByLocal = data.getLocalGank("GankDailyBean");
                return Observable.concat(gankDailyBeanByLocal,dailyDataByNetworkWithlocalUpdate).first();
            case 1:
                return (Observable<GankDataBean>) mMainApi.getGankData();
        }
        throw new IllegalArgumentException("没有找到对应recode的api");
    }

    public EasyDate getEasyData() {
        EasyDate easyDate = new EasyDate();
        return easyDate;
    }

}
