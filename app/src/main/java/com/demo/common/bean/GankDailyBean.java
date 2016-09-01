package com.demo.common.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 Piper
 * @创建时间 2016/7/14 22:51
 * @描述 TODO
 */
public class GankDailyBean extends BaseBean{


    public ResultsBean       results;
    public ArrayList<String> category;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public ResultsBean getResults() {
        return results;
    }

    public void setResults(ResultsBean results) {
        this.results = results;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(ArrayList<String> category) {
        this.category = category;
    }

 public static   class ResultsBean {

        /**
         * _id : 5786f935421aa90df638bb2a
         * createdAt : 2016-07-14T10:30:13.329Z
         * desc : 馬場 ふみか
         * publishedAt : 2016-07-14T11:39:49.710Z
         * source : chrome
         * type : 福利
         * url : http://ww3.sinaimg.cn/large/610dc034jw1f5t889dhpoj20f00mi414.jpg
         * used : true
         * who : 代码家
         */


        @SerializedName("福利") public ArrayList<BaseGankDailyBean> welfareData;

        @SerializedName("Android") public ArrayList<BaseGankDailyBean> androidData;

        @SerializedName("iOS") public ArrayList<BaseGankDailyBean> iosData;

        @SerializedName("前端") public ArrayList<BaseGankDailyBean> jsData;

        @SerializedName("休息视频") public ArrayList<BaseGankDailyBean> videoData;

        @SerializedName("拓展资源") public ArrayList<BaseGankDailyBean> resourcesData;

        @SerializedName("App") public ArrayList<BaseGankDailyBean> appData;

        @SerializedName("瞎推荐") public ArrayList<BaseGankDailyBean> recommendData;



    }
}
