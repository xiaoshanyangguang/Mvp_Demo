package com.demo.func.account.ui;

import com.demo.R;
import com.demo.common.base.BaseListFragment;
import com.demo.common.base.BaseViewHolder;
import com.demo.common.bean.BaseGankDailyBean;
import com.demo.common.bean.GankDailyBean;
import com.demo.common.dagger.component.FragmentComponent;
import com.demo.common.presenter.BasePresenter;
import com.demo.func.account.presenter.WalletContract;
import com.demo.func.account.presenter.WalletPresenter;

import javax.inject.Inject;

/**
 * @创建者 piper
 * @创建时间 2016/8/23 12:44
 * @描述 TODO
 */
public class WalletFragment extends BaseListFragment<WalletPresenter,GankDailyBean> implements WalletContract.WalletView{


    @Inject
    WalletPresenter mWalletPresenter;
    @Override
    protected void fitDates(BaseViewHolder holder, GankDailyBean bean) {
        String  url = null;
        String  publishedAt = null;
        String  desc = null;
        if(bean.results.videoData!=null&&bean.results.videoData.size()>0){
            BaseGankDailyBean videoGankDailyBean = bean.results.videoData.get(0);
            publishedAt = videoGankDailyBean.publishedAt;
            desc = videoGankDailyBean.desc;
        }else if(bean.results.welfareData!=null&&bean.results.welfareData.size()>0){
            BaseGankDailyBean welfareGankDailyBean  = bean.results.welfareData.get(0);
            publishedAt = welfareGankDailyBean.publishedAt;
           desc = welfareGankDailyBean.desc;
       }else{
            publishedAt = "某年某月某日";
            desc= "木有福利了,好好学习吧";
        }
        if(bean.results.welfareData!=null&&bean.results.welfareData.size()>0){
            BaseGankDailyBean welfareGankDailyBean  = bean.results.welfareData.get(0);
            url = welfareGankDailyBean.url;
        }
        holder.setImageUrl(R.id.daily_iv,this,url).setText(R.id.daily_date_tv,publishedAt).setText(R.id.daily_title_tv,desc);
    }

    @Override
    public int getItemLayout() {
        return R.layout.item_daily;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initInjector(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    public BasePresenter getPresenter() {
        return mWalletPresenter;
    }


}
