package com.ylf.jucaipen.newtest;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Administrator on 2015/11/4.
 */
public class TestActivity extends Activity {
    private Button btn_submit;
    private EditText et_name;
    private  EditText et_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_test);
        initView();
    }

    private void initView() {
        et_name= (EditText) findViewById(R.id.et_name);
        et_pwd= (EditText) findViewById(R.id.et_pwd);
        btn_submit= (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                String btnStr=btn_submit.getText().toString();
                if (btnStr.equals("提交")){
                    et_name.setBackground(null);
                    et_pwd.setBackground(null);
                    et_pwd.setEnabled(false);
                    et_name.setEnabled(false);
                    btn_submit.setText("已提交");
                }else{
                    et_name.setBackground(getResources().getDrawable(R.drawable.et_selector));
                    et_pwd.setBackground(getResources().getDrawable(R.drawable.et_selector));
                    et_pwd.setEnabled(true);
                    et_name.setEnabled(true);
                    btn_submit.setText("提交");
                }
            }
        });

    }
}
