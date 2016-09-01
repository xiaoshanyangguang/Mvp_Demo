package com.demo.func.home.model.forum;

import com.demo.common.bean.GankDailyBean;
import com.demo.common.bean.GankDataBean;
import com.demo.common.config.DemoApi;
import com.demo.common.util.L;
import com.demo.common.util.RxUtils;
import com.demo.func.home.common.EasyDate;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * @创建者 piper
 * @创建时间 2016/8/10 21:08
 * @描述 TODO
 */
public class MainApi {

    private final MainService mMainService;


    public MainApi(OkHttpClient okHttpClient){
        mMainService = new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create())
               .baseUrl(DemoApi.BASE_URL).client(okHttpClient).build().create(MainService.class);
    }
    public Observable<GankDailyBean> getDaily(EasyDate mEasyDate){
        if(null== mEasyDate){
            throw new IllegalArgumentException("mEasyDate can not be null");
        }
       return mMainService.getDaily(mEasyDate.getYear(), mEasyDate.getMonth(),mEasyDate.getDay());
    }

    public Observable<GankDataBean> getGankData(){
        return mMainService.getGankData(DemoApi.DATA_TYPE_ANDROID,10,0);
    }

    public Observable<List<GankDailyBean>> getDailyDataByNetwork(EasyDate easyDate){
        return Observable.just(easyDate).flatMapIterable(new Func1<EasyDate, Iterable<? extends EasyDate>>() {


            @Override
            public Iterable<EasyDate> call(EasyDate easyDate) {
                return easyDate.getPastTime();
            }
        }).flatMap(new Func1<EasyDate, Observable<GankDailyBean>>() {
            @Override
            public Observable<GankDailyBean> call(EasyDate easyDate) {
                return getDaily(easyDate);
            }
        }).filter(new Func1<GankDailyBean, Boolean>() {
            @Override
            public Boolean call(GankDailyBean gankDailyBean) {
                return gankDailyBean.results.androidData!=null;
            }
        }).toSortedList(new Func2<GankDailyBean, GankDailyBean, Integer>() {
            @Override
            public Integer call(GankDailyBean gankDailyBean, GankDailyBean gankDailyBean2) {
                return gankDailyBean2.results.androidData.get(0).publishedAt.compareTo(gankDailyBean.results.androidData.get(0).publishedAt);
            }
        }).compose(RxUtils.applyIOToMainThreadSchedulers());

    }

}
