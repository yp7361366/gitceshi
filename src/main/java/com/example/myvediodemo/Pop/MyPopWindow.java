package com.example.myvediodemo.Pop;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.myvediodemo.R;
import com.example.myvediodemo.view.MyPopListView;

import java.util.List;

/**
 * Created by yipan on 2017/11/4.
 */

public class MyPopWindow extends PopupWindow implements AdapterView.OnItemClickListener {
    private List<String> mList;
    private final LayoutInflater inflater;
    public OnItemPopListener mOnItemPopListener;
    public MyPopWindow(Context context){
        inflater = LayoutInflater.from(context);
        init();
    }

    public void addList(List<String> list){
        this.mList.clear();
        this.mList = list;
    }

    private void init() {
        View view = inflater.inflate(R.layout.pop_listview, null);
        MyPopListView pop_listview = (MyPopListView) view.findViewById(R.id.pop_list);
        pop_listview.setAdapter(new MyListAdapter());
        pop_listview.setOnItemClickListener(this);
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setContentView(view);
        //设置PopWindow弹出的窗口可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.setBackgroundDrawable(new ColorDrawable(0000000000));//设置保证back，或点击窗体外区域Popwidow消息
//        this.setAnimationStyle();  //设置弹出窗体动画效果
        this.setAnimationStyle(R.style.bt_pop);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mOnItemPopListener != null) {
            mOnItemPopListener.setOnItemClick(view,position);
        }
    }



    public interface OnItemPopListener{
        void setOnItemClick(View v,int position);
    }

    public void setOnItemPopListener(OnItemPopListener popListener){
        this.mOnItemPopListener = popListener;
    }

    private class MyListAdapter extends BaseAdapter{


        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, android.view.View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.pop_list_item,parent,false);
                holder.mTextView = (TextView) convertView.findViewById(R.id.pop_item_tv);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }
            holder.mTextView.setText(mList.get(position));
            return convertView;
        }
    }

    private class ViewHolder{
        TextView mTextView;
    }
}
