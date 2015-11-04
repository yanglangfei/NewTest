package com.ylf.jucaipen.newtest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.ylf.jucaipen.newtest.com.ylf.jucaipen.view.MyTextView;

/**
 * Created by Administrator on 2015/11/4.
 */
public class StrTestActivity extends Activity {
    private MyTextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_str);
        tv= (MyTextView) findViewById(R.id.tv);
        tv.setTextStr("网络图片测试：\" + \"<img src='http://img.my.csdn.net/uploads/201307/14/1373780364_7576.jpg'>");
    }
}
