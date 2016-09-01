package com.demo.func.account.data;

import com.anupcowkur.reservoir.Reservoir;
import com.demo.common.bean.GankDailyBean;
import com.demo.common.util.DataUtils;
import com.demo.common.util.L;
import com.demo.common.util.ReservoirUtils;
import com.google.common.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * @创建者 piper
 * @创建时间 2016/9/1 21:02
 * @描述 TODO
 */
public class ContentLocalDataSource implements ContentDataSource {
    @Override
    public Observable<List<GankDailyBean>> getLocalGank(String key) {
        return Observable.create(new Observable.OnSubscribe<List<GankDailyBean>>() {
            @Override
            public void call(Subscriber<? super List<GankDailyBean>> subscriber) {
                long oldTime = 0;
                Type resultType  = new TypeToken<ArrayList<GankDailyBean>>(){}.getType();


                if( ReservoirUtils.getInstance().contains("GankDailyBean")&& ReservoirUtils.getInstance().contains("GankDailyBeanTime")) {
                    L.e("GankDailyBean");

                    try {
                        oldTime = Reservoir.get
                                ("GankDailyBeanTime", long.class);
                        List<GankDailyBean> gankDailyBean = (List<GankDailyBean>) Reservoir.get
                                ("GankDailyBean", resultType);
                        L.e("DataUtils.isUpToDate(oldTime)"+oldTime+"--"+System.currentTimeMillis()+DataUtils.isUpToDate(oldTime));

                        if(DataUtils.isUpToDate(oldTime)){
                            L.e("GankDailyBeanisUpToDate");
                            L.e("gankDailyBean".toString());
                            subscriber.onNext(gankDailyBean);
                        }else{
                            subscriber.onCompleted();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        subscriber.onCompleted();
                    }
                }else{
                    subscriber.onCompleted();
                }
            }
        });


    }
}
/**
 *

 */
