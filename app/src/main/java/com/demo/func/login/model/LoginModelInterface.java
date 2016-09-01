package com.demo.func.login.model;

import com.demo.common.model.BaseModelInterface;

/**
 * @创建者 Piper
 * @创建时间 2016/6/24 0:45
 * @描述 TODO
 */
public interface LoginModelInterface extends BaseModelInterface {
    interface OnloginFinishListener{
        void onUserNameError();
        void onPassWordError();
        void onSuccess();
        void onFailed(String str);
    }
    void login(String userName,String passWord,OnloginFinishListener listener);
}
