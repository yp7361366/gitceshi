package com.example.myvediodemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myvediodemo.Pop.MyPopWindow;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements MyPopWindow.OnItemPopListener {

    private Context mcontext;
    private List<String> mList;
    private MyPopWindow popWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mcontext = this;
        setContentView(R.layout.activity_main);
        final Button bt_pop = (Button) findViewById(R.id.bt_setect);
        initData();
        bt_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                PopupWindow popupWindow = new PopupWindow(MainActivity.this);
//                popupWindow.setContentView(getLayoutInflater().inflate(R.layout.pop_layout,null));
////                popupWindow.setWidth(100);
//                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
////                popupWindow.showAsDropDown(bt_pop);
////                popupWindow.showAtLocation(bt_pop, Gravity.BOTTOM|Gravity.RIGHT,0,0);
////                popupWindow.showAsDropDown(bt_pop,0,0, Gravity.TOP);
//                popupWindow.showAsDropDown(bt_pop,0,-(popupWindow.getContentView().getHeight() + bt_pop.getHeight()));
//                popupWindow.update();
                popWindow = new MyPopWindow(MainActivity.this, mList);
                popWindow.showAtLocation((View) bt_pop.getParent(), Gravity.BOTTOM|Gravity.LEFT, bt_pop.getWidth(),bt_pop.getHeight() - bt_pop.getPaddingTop()/2);
//                popWindow.showAsDropDown(bt_pop);
                popWindow.setOnItemPopListener(MainActivity.this);
                popWindow.update();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog = builder.create();

    }

    private void initData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 5 ; i++) {
            mList.add(((i + 1) * 3) + "个");
        }
    }


    @Override
    public void setOnItemClick(View v, int position) {
        Toast.makeText(MainActivity.this,mList.get(position),Toast.LENGTH_SHORT).show();
    }
}
