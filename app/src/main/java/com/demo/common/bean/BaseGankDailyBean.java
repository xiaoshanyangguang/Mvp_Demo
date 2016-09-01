package com.demo.common.bean;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * @创建者 Piper
 * @创建时间 2016/7/14 23:14
 * @描述 TODO
 */
public  class BaseGankDailyBean extends BaseBean{
    public String  _id;
    public String  desc;
    public String  publishedAt;
    public String  source;
    public String  type;
    public String  url;
    public Boolean used;
    public String  who;

    // 对象id
    @SerializedName("objectId") public String objectId;

    // 创建时间
    @SerializedName("createdAt") public String createdAt;

    // 更新时间
    @SerializedName("updatedAt") public Date updatedAt;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }
}