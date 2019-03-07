package com.commonlib.adapter;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HERB
 * @date Created on 2017/8/12 21:10
 * @info:
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter {

    private Context mContext;
    private List<T> mDatas;
    private int mLayoutId;
    private OnItemClickListener mItemClickListener;
    private OnLongClickListener mLongClickListener;
    private int positions;

    private List<CountDownTimer> countDownMap;

    public BaseRecyclerAdapter(Context context, int layoutId) {
        mContext = context;
        mLayoutId = layoutId;
        mDatas = new ArrayList<>();
        countDownMap = new ArrayList();
    }

    //刷新数据
    public void refreshData(List<T> datas){
        mDatas.clear();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    public T getItem(int position) {
        if (position >= mDatas.size()) return null;
        return mDatas.get(position);
    }

    public List<T> getList(){
        return mDatas;
    }

    public boolean removeItem(int index){
        try {
            if (index < mDatas.size()){
                mDatas.remove(index);
                notifyDataSetChanged();
                return true;
            }
        }catch (Exception e){
            Logger.e(e.getMessage());
        }
        return false;

    }


    public boolean removeItem(T index){
        try {
            if (index != null){
                boolean flag = mDatas.remove(index);
                notifyDataSetChanged();
                return flag;
            }
        }catch (Exception e){
            Logger.e(e.getMessage());
        }
        return false;

    }

    public void add(CountDownTimer ct){
        countDownMap.add(ct);
    }

    public void clear(){

        if (mDatas != null && mDatas.size()>0){
            mDatas.clear();
        }
        notifyDataSetChanged();
    }

    public void addAll(List<T> datas){
        mDatas.addAll(0,datas);
        notifyDataSetChanged();
    }
    public void addFront(T data){
        mDatas.add(0,data);
        notifyDataSetChanged();
    }
    public void addEnd(T data){
        mDatas.add(data);
        notifyDataSetChanged();
    }

    public int getSize(){
        return mDatas.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false);
        BaseViewHolder baseViewHolder = new BaseViewHolder(itemView,mContext);
        return baseViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof BaseViewHolder){
            convert(mContext, holder, mDatas.get(position));

            positions = position;
            ((BaseViewHolder) holder).mItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(v,position);
                }
            });
            ((BaseViewHolder) holder).mItemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mLongClickListener.onLongClick(view,position);
                    return false;
                }
            });

        }
    }

    public T getItem(){

        return mDatas.get(positions);
    }


    @Override
    public int getItemCount() {
        return mDatas == null?0:mDatas.size();
    }
    public abstract void convert(Context mContext, RecyclerView.ViewHolder holder, T t);

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
    public void setLongClickListener(OnLongClickListener listener) {
        this.mLongClickListener = listener;
    }



    //点击事件
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    //点击事件
    public interface OnLongClickListener {
        void onLongClick(View view, int position);
    }

    /**
     * 清空资源
     */
    public void cancelAllTimers() {
        if (countDownMap == null) {
            return;
        }

        for (int i = 0,length = countDownMap.size(); i < length; i++) {
            CountDownTimer cdt = countDownMap.get(i);
            if (cdt != null) {
                cdt.cancel();
            }
        }
    }


}
