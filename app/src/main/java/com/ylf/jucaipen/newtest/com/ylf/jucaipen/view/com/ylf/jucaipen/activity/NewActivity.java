package com.ylf.jucaipen.newtest.com.ylf.jucaipen.view.com.ylf.jucaipen.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ylf.jucaipen.newtest.R;

/**
 * Created by Administrator on 2015/11/26.
 */
public class NewActivity extends Activity {

    private Button btn_new;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_news);
        initView();
    }

    private void initView() {
        index=getIntent().getIntExtra("index",-1);
        btn_new=(Button)findViewById(R.id.btn_new);
        btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returns=new Intent();
                returns.putExtra("index",index);
                setResult(100, returns);
                NewActivity.this.finish();
            }
        });
    }
}
