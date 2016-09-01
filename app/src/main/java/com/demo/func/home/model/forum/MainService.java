package com.demo.func.home.model.forum;

import com.demo.common.bean.GankDailyBean;
import com.demo.common.bean.GankDataBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @创建者 Piper
 * @创建时间 2016/7/12 23:28
 * @描述 TODO
 */
public interface MainService {
    @GET("day/{year}/{month}/{day}")
    Observable<GankDailyBean> getDaily(@Path("year") int year , @Path("month") int month , @Path("day") int day);

    @GET("data/{type}/{size}/{page}")
    Observable<GankDataBean> getGankData(@Path("type") String type, @Path("size") int size , @Path("page") int page);
}
