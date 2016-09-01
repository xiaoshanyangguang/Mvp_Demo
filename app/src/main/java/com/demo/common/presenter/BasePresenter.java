package com.demo.common.presenter;

import android.content.Context;
import android.os.Bundle;

import com.demo.common.model.BaseModel;
import com.demo.common.util.L;
import com.demo.common.util.RxUtils;
import com.demo.common.widget.BaseView;

import java.util.HashMap;

import icepick.Icepick;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @创建者 Piper
 * @创建时间 2016/6/28 23:57
 * @描述 TODO
 */
public abstract class BasePresenter<V extends BaseView,M extends BaseModel> {


    protected      V                     mUiView;
    protected      M                     mBaseModel;
    protected      Context               mContext;
    private Subscriber mSubscriber;
    private Subscription mSubscription;
    private boolean                  isCancell =false;
    private HashMap<Integer,Boolean> recodeMap = new HashMap<Integer,Boolean>();
    protected RequestMode mode = RequestMode.FRIST;
    private CompositeSubscription mSubscriptions;


    public BasePresenter(M baseModel){
        mBaseModel = baseModel;

    }

    public void onCreate(Bundle savedInstanceState, Context context) {
        Icepick.restoreInstanceState(this,savedInstanceState);
        this.mContext = context;
    }
    public enum RequestMode {
        FRIST, LOAD_MORE, REFRESH
    }
    /**
     * 如果recodes为空或者length为0，则无需从model获取数据源,表示无需订阅
     * @param recodes
     */
    public void subscribe(int[] recodes,RequestMode mode) {
        if(recodes==null||recodes.length<=0){
            return;
        }

        mSubscriptions = new CompositeSubscription();
        requestData(recodes,mode);
    }

    public  void requestData(int[] recodes,RequestMode mode){
        this.mode = mode;
        for(int recode=0;recode<recodes.length;recode++){
            mSubscriber = getSubscriber(recode,mode);
            mSubscription = mBaseModel.getObservable(recode,mode).compose(RxUtils.applyIOToMainThreadSchedulers())
                    .subscribe(mSubscriber);
        }
    }



    protected Subscriber getSubscriber(final int recode,RequestMode mode){
        return new Subscriber() {
                @Override
                public void onCompleted() {
                    L.e("onCompleted");
                    onFinish(recode);
                }

                @Override
                public void onError(Throwable e) {
                    L.e("onError");

                    e.printStackTrace();
                    onFailed(e,recode);
                }

                @Override
                public void onNext(Object data) {
                    L.e("onNext");

                    if(!isCancell){
                        if(data!=null){
                            L.e(this.getClass().getName(),"onsuccess");
                             onsuccess(data,recode);
                        }else{
                            onFailed(data,recode);
                        }
                    }

                }
            };
    }


    protected abstract void onsuccess(Object data, int recode);

    protected abstract void onFailed(Object e, int recode);

    protected abstract void onFinish(int recode);

    public void onSave(Bundle state) {
        Icepick.restoreInstanceState(this,state);
    }

    public void cancellRequest(int index) {
        this.recodeMap.put(index,true);
    }

    public void cancellAllRequest() {
        isCancell = true;
    }

    public void startRequest(int requestCode) {

    }
    public void startAllRequest() {
        isCancell = false;
    }

    public void onResume(V uiView) {
        if(uiView==null){
            throw new NullPointerException("uiVIew 不能为空");
        }
        this.mUiView = uiView;
        subscribe(getObservableCode(),RequestMode.FRIST);
    }

    /**
     * 获取对应api的recode，如果没有则返回null
     * @return
     */
    public abstract int[] getObservableCode();

    public void onPause() {
        mUiView = null;
        if(mSubscription!=null&&(!mSubscription.isUnsubscribed())){
            mSubscription.unsubscribe();
        }
    }

    public void onDestroy() {
        mUiView=null;
    }


    public void closeRealm() {

    }
}
