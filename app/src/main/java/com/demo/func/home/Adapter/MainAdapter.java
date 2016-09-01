package com.demo.func.home.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.R;

import java.util.List;

/**
 * @创建者 Piper
 * @创建时间 2016/7/6 22:04
 * @描述 TODO
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder>{

    private LayoutInflater mLayoutInflater;
    private List<String> mData;
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(mLayoutInflater.inflate(R.layout.item_main_card,parent,false));
        return holder;
    }

    public MainAdapter(Context context , List<String> data){
        mLayoutInflater  = LayoutInflater.from(context);
        mData = data;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTextView.setText(mData.get(position));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addData(int position){
        mData.add(position,"我是新添加的");
        notifyDataSetChanged();
    }

    public void removeData(int position){
        mData.remove(position);
        notifyDataSetChanged();
    }
    public  class MyViewHolder extends RecyclerView.ViewHolder{
//        CardView mCardView;
        TextView mTextView;
        public  MyViewHolder(View view){
            super(view);
            mTextView = (TextView) view.findViewById(R.id.tv_chapter);
        }

    }
}
