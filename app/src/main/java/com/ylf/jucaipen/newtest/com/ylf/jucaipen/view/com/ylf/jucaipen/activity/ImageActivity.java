package com.ylf.jucaipen.newtest.com.ylf.jucaipen.view.com.ylf.jucaipen.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.ylf.jucaipen.newtest.R;

/**
 * Created by Administrator on 2015/12/1.
 */
public class ImageActivity extends Activity {
    @ViewInject(R.id.bg)
    private LinearLayout bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_image);
        ViewUtils.inject(this);
        String src=getIntent().getStringExtra("src");
        if(src!=null&&!src.equals("")){
            BitmapUtils utils=new BitmapUtils(this);
            utils.display(bg,src);
        }
    }
}
