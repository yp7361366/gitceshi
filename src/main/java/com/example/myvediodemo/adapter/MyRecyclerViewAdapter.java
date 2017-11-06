package com.example.myvediodemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myvediodemo.R;
import com.example.myvediodemo.myinterface.OnRecyclerViewItemClickListener;
import com.example.myvediodemo.myinterface.OnRecyclerViewItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yipan on 2017/11/6.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements  View.OnClickListener {

    private final LayoutInflater inflater;
    private List<Integer> mList = new ArrayList<>();
    private Context mContext;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    private OnRecyclerViewItemLongClickListener onRecyclerViewItemLongClickListener;

    public MyRecyclerViewAdapter(Context context){
        this.mContext = context;
        inflater = LayoutInflater.from(context);
    }

    public void addList(List<Integer> list){
        this.mList.addAll(list);
    }

    public void selectList(Integer str,int position){
        mList.remove(position);
        mList.add(position,str);
        notifyItemChanged(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(inflater.inflate(R.layout.recycler_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Glide.with(mContext).load(mList.get(position)).into(((MyViewHolder)holder).mImageView);
        holder.itemView.setOnClickListener(this);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onRecyclerViewItemLongClickListener != null) {
                    onRecyclerViewItemLongClickListener.onItemLongClick(v, position);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener listener){
        this.onRecyclerViewItemClickListener = listener;
    }
    public void setOnRecyclerViewItemLongClickListener(OnRecyclerViewItemLongClickListener listener){
        this.onRecyclerViewItemLongClickListener = listener;
    }

//    @Override
//    public boolean onLongClick(View v) {
//        if (onRecyclerViewItemLongClickListener != null) {
//            onRecyclerViewItemLongClickListener.onItemLongClick(v, v);
//        }
//        return true;
//    }

    @Override
    public void onClick(View v) {
        if (onRecyclerViewItemClickListener != null) {
            onRecyclerViewItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }


    private class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.recyitem_iv);

        }
    }

}
