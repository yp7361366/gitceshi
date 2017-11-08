package com.example.myvediodemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.example.myvediodemo.Pop.MyPopWindow;
import com.example.myvediodemo.adapter.MyRecyclerViewAdapter;
import com.example.myvediodemo.myinterface.OnRecyclerViewItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements MyPopWindow.OnItemPopListener, OnRecyclerViewItemLongClickListener {

    private Context mcontext;
    private List<String> mList ;
    private List<String> mImageList;
    private MyPopWindow popWindow;
    private RecyclerView recyclerView;
    private List<Integer> imageUrlList;
    private int[] image_id = {R.mipmap.one,R.mipmap.two,R.mipmap.three,R.mipmap.four,R.mipmap.fire,R.mipmap.six};
    private MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mcontext = this;
        setContentView(R.layout.activity_main);
        final Button bt_pop = (Button) findViewById(R.id.bt_setect);
        initImageUrlData();
        recyclerView = (RecyclerView) findViewById(R.id.recyview_image);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyRecyclerViewAdapter(this);
//        adapter.addList(imageUrlList);
        recyclerView.setAdapter(adapter);
        initData();
        bt_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popWindow == null) {
                    popWindow = new MyPopWindow(MainActivity.this);
                }
                popWindow.addList(mList);

                popWindow.showAtLocation((View) bt_pop.getParent(), Gravity.BOTTOM|Gravity.LEFT, bt_pop.getWidth(),bt_pop.getHeight() - bt_pop.getPaddingTop()/2);
//                popWindow.showAsDropDown(bt_pop);
                popWindow.setOnItemPopListener(MainActivity.this);
                popWindow.update();
            }
        });

        adapter.setOnRecyclerViewItemLongClickListener(this);

    }

    private void initImageUrlData() {
        imageUrlList = new ArrayList<>();
        for (int i = 0; i < image_id.length; i++) {
            imageUrlList.add(image_id[i]);
        }
    }

    private void initData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 5 ; i++) {
            mList.add(((i + 1) * 3) + "个");
        }
    }

    private void initImage(){
        mImageList = new ArrayList<>();
        for (int i = 0; i < image_id.length ; i++) {
            mImageList.add(image_id[i] + "");
        }
    }


    @Override
    public void setOnItemClick(View v, int position) {
//        Toast.makeText(MainActivity.this,mList.get(position),Toast.LENGTH_SHORT).show();
        adapter.addList(imageUrlList);
        adapter.notifyDataSetChanged();
        popWindow.dismiss();
        popWindow = null;
    }

    @Override
    public boolean onItemLongClick(View view, final int position) {
        initImage();
        if (popWindow == null) {
            popWindow = new MyPopWindow(MainActivity.this);
        }
        popWindow.addList(mImageList);
        popWindow.showAsDropDown(view,view.getWidth()/2,-view.getHeight()/2);
        popWindow.update();
        popWindow.setOnItemPopListener(new MyPopWindow.OnItemPopListener() {
            @Override
            public void setOnItemClick(View v, int positions) {
                adapter.selectList(Integer.valueOf(mImageList.get(positions)),position);

            }
        });
        return true;
    }
}
