package com.demo.func.home.common;

import com.demo.common.config.DemoApi;
import com.demo.common.util.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

/**
 * @创建者 piper
 * @创建时间 2016/8/10 22:14
 * @描述 TODO
 */
public class EasyDate {

    private int page = 1;
    private Calendar mCalendar;
    @Inject
    public EasyDate(){
        mCalendar = Calendar.getInstance();
    }

    public void setCalendar(Calendar calendar){
        mCalendar = calendar;
    }
    public int getYear(){
       return mCalendar.get(Calendar.YEAR);
    }
    public int getMonth(){
       return mCalendar.get(Calendar.MONTH)+1;
    }
    public int getDay(){
        return mCalendar.get(Calendar.DAY_OF_MONTH);
    }

    public List<EasyDate> getPastTime() {
        List<EasyDate> easyDates = new ArrayList<>();
        for (int i = 0; i < DemoApi.DEFAULT_DAILY_SIZE; i++) {
                /*
                 * - (page * DateUtils.ONE_DAY) 翻到哪页再找 一页有DEFAULT_DAILY_SIZE这么长
                 * - i * DateUtils.ONE_DAY 往前一天一天 找呀找
                 */
            long time = this.mCalendar.getTimeInMillis() - ((page - 1) * DemoApi.DEFAULT_DAILY_SIZE * DateUtils.ONE_DAY) - i * DateUtils.ONE_DAY;
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(time);
            EasyDate date = new EasyDate();
            date.setCalendar(c);
            easyDates.add(date);
        }
        return easyDates;
    }

}
