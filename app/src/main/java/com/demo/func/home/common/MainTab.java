package com.demo.func.home.common;

import com.demo.R;
import com.demo.func.home.ui.AccountFragment;
import com.demo.func.home.ui.AddressBookFragment;
import com.demo.func.home.ui.BuyWithFragment;
import com.demo.func.home.ui.HallFragment;

/**
 * @创建者 piper
 * @创建时间 2016/8/22 22:42
 * @描述 TODO
 */
public enum  MainTab {
    ACCOUNT(0,R.string.main_tab_name_account, R.drawable.tab_icon_account,AccountFragment.class),

    BUY(1,R.string.main_tab_name_buywith,R.drawable.tab_icon_explore, BuyWithFragment.class),

    ADDRESSBook(2,R.string.main_tab_name_addressbook, R.drawable.tab_icon_tweet,AddressBookFragment.class),

    HALL(3,R.string.main_tab_name_hall,R.drawable.tab_icon_me, HallFragment.class);



    private int idxl;
    private int resName;
    private int resIcon;
    private Class<?> clz;
    private MainTab(int idx,int resName,int resIcon,Class<?> clz){
        this.idxl = idx;
        this.resName = resName;
        this.resIcon = resIcon;
        this.clz = clz;

    }

    public int getIdxl() {
        return idxl;
    }

    public void setIdxl(int idxl) {
        this.idxl = idxl;
    }

    public int getResName() {
        return resName;
    }

    public void setResName(int resName) {
        this.resName = resName;
    }

    public int getResIcon() {
        return resIcon;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }

    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }
}
