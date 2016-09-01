package com.demo.func.login.ui;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.demo.R;
import com.demo.common.base.BaseToolbarActivity;
import com.demo.common.dagger.component.ActivityComponent;
import com.demo.common.presenter.BasePresenter;
import com.demo.common.widget.CustemToolBar;
import com.demo.func.home.ui.MainActivity;
import com.demo.func.login.presenter.LoginContract;
import com.demo.func.login.presenter.LoginPresentImpl;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * @创建者 Piper
 * @创建时间 2016/6/24 0:43
 * @描述 TODO
 */
public class LoginActivity extends BaseToolbarActivity implements LoginContract.LoginView {

    @Bind(R.id.et_name)
    EditText mEtName;
    @Bind(R.id.et_password)
    EditText mEtPassword;
    @Bind(R.id.btn_login)
    Button   mBtnLogin;
    @Inject
    LoginPresentImpl mLoginPresent;


    @Override
    protected void initInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected BasePresenter getPresent() {
        return mLoginPresent;
    }


    @Override
    protected void initViews() {
        ((CustemToolBar)toolBar).setTitleText("登录");
        ((CustemToolBar)toolBar).setBtnLeftInvisible();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void setUserNameError() {
        mEtName.setError("用户名不能为空");
    }

    @Override
    public void setPassWordError() {
        mEtPassword.setError("密码不能为空");
    }

    @Override
    public void setFailed() {
        Toast.makeText(this, "登陆失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void jumpToHome() {
        skipActivity(MainActivity.class);
    }

    @Override
    public void hidIME() {
        hideIME();
    }


    @OnClick(R.id.btn_login)
    public void onClick() {
        mLoginPresent.validateCredentials(mEtName.getText().toString().trim(),mEtPassword.getText().toString().trim());
    }


    @Override
    public void showEmpty(String msg) {

    }

    @Override
    public void showNetError() {

    }

   



}
