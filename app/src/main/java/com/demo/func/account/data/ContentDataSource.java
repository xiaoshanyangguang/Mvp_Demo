package com.demo.func.account.data;

import com.demo.common.bean.GankDailyBean;

import java.util.List;

import rx.Observable;

/**
 * Created by sll on 2016/6/3.
 * 帖子详情页数据源
 */
public interface ContentDataSource {
  Observable<List<GankDailyBean>> getLocalGank(String key);
}
