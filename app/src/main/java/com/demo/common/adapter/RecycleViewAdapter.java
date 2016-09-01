package com.demo.common.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.demo.common.base.BaseViewHolder;
import com.demo.common.refresh.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class RecycleViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private View mContentView;

    private List<T> mData;

    private int mLayoutResId;

    private Context mContext;

    private LayoutInflater mLayoutInflater;

    private BaseViewHolder.OnItemClickListener mOnItemClickListener;
    private BaseViewHolder.OnItemLongClickListener mOnItemLongClickListener;

    private BaseViewHolder.OnItemClickListener onItemClickListener;
    private BaseViewHolder.OnItemLongClickListener onItemLongClickListener;

    public RecycleViewAdapter(int layoutResId, List<T> data) {
        this.mData = data == null ? new ArrayList<T>() : data;
        if (layoutResId != 0) {
            this.mLayoutResId = layoutResId;
        }
    }

    public RecycleViewAdapter(View contentView, List<T> data) {
        this(0, data);
        mContentView = contentView;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BaseViewHolder viewHolder = (BaseViewHolder) holder;
        ViewParent parent = viewHolder.getConvertView().getParent();

        int headerCount =0;
        if(parent instanceof XRecyclerView){
            headerCount = ((XRecyclerView) parent).getHeaderViewCount();
        }
        initOnItemClick(viewHolder,headerCount,position);
        initOnItemLongClick(viewHolder,headerCount,position);
        onBindRecycleViewHolder(viewHolder, mData.get(position));
    }

    protected  void initOnItemLongClick(BaseViewHolder viewHolder,int headerCount,int position){
        if(onItemClickListener!=null){
            viewHolder.setOnItemClickListener(onItemClickListener,position);
        }

    }

    protected  void initOnItemClick(BaseViewHolder viewHolder,int headerCount,int position){
        if(onItemLongClickListener!=null){
            viewHolder.setOnItemLongClickListener(onItemLongClickListener,position);
        }

    }

    /**
     * 对多个viewtype支持
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder baseViewHolder = null;
        this.mContext = parent.getContext();
        this.mLayoutInflater = LayoutInflater.from(mContext);
        if(viewType<0){
            throw new IllegalArgumentException("viewType 类型不能小于0");
        }
        if(getItemLayouts()==null){
            baseViewHolder = createBaseViewHolder(parent, mLayoutResId);
            return baseViewHolder;
        }

//----------------------------
        int[] layoutIds = getItemLayouts();
        if(layoutIds.length<1){
            throw new IllegalArgumentException("getItemLayouts() 返回的数组不能小于1");
        }

        int itemLayoutId;
        if(layoutIds.length==1){
            itemLayoutId = layoutIds[0];
        }else{
            itemLayoutId = layoutIds[viewType];
        }
           baseViewHolder = createBaseViewHolder(parent, itemLayoutId);

        return baseViewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        return getRecycleItemViewType(position);
    }

    protected  int getRecycleItemViewType(int position){
        return 0;
    }



    protected BaseViewHolder createBaseViewHolder(ViewGroup parent, int layoutResId) {
        if (mContentView == null) {
            return new BaseViewHolder(getItemView(layoutResId, parent));
        }
        return new BaseViewHolder(mContentView);
    }

    protected View getItemView(int layoutResId, ViewGroup parent) {
        return mLayoutInflater.inflate(layoutResId, parent, false);
    }



    protected abstract void onBindRecycleViewHolder(BaseViewHolder holder, T t);

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setNewData(List<T> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }
    public void remove(T data) {
        mData.remove(data);
        notifyDataSetChanged();
    }
    public void addDataAndNotify(List<T> data) {
        this.mData.addAll(data);
        notifyDataSetChanged();
    }

    public List getData() {
        return mData;
    }

    public T getItem(int position) {
        return mData.get(position);
    }
    /**
     * 返回recycleview条目布局的id的数组
     * 多类型item时覆写
     * @return 布局id数组
     */
    public  int[] getItemLayouts() {
        return null;
    }

    /**
     * set the on item click listener
     * 设置点击事件
     *
     * @param onItemClickListener onItemClickListener
     */
    public void setOnItemClickListener(BaseViewHolder.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * set the on item long click listener
     * 设置长点击事件
     *
     * @param onItemLongClickListener onItemLongClickListener
     */
    public void setOnItemLongClickListener(BaseViewHolder.OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

}
