package com.ylf.jucaipen.newtest.com.ylf.jucaipen.view.com.ylf.jucaipen.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.ylf.jucaipen.newtest.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2015/11/4.
 */
public class TextActivity extends Activity {
    private TextView tv_text;
    private  String picName="pics";  //文件名称
    final String sText = "网络图片测试：" + "<img src='http://img.my.csdn.net/uploads/201307/14/1373780364_7576.jpg'>";
    private String src;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_text);
        initView();
    }

    private void initView() {
        tv_text = (TextView) findViewById(R.id.tv_text);
        Document result= Jsoup.parse(sText);
       Element image= result.select("img").first();
        src=image.attr("src");
        if(src!=null&&!src.equals("")){
            ImageView imageView=new ImageView(this);
            BitmapUtils utils=new BitmapUtils(this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            utils.display(imageView, src);
            ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(100,100);
            this.addContentView(imageView,lp);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(src!=null&&!src.equals("")) {
                        Intent in = new Intent();
                        in.setClass(TextActivity.this, ImageActivity.class);
                        in.putExtra("src",src);
                        TextActivity.this.startActivity(in);
                    }
                }

            });
        }
        tv_text.setText(Html.fromHtml(sText, getter, null));
    }

    class  GetImageTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... params) {
            // 加载网络图片
            loadNetPic(params);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            // 当执行完成后再次为其设置一次
            tv_text.setText(Html.fromHtml(sText,getter,null));
        }
    }

    private void loadNetPic(String[] params) {
        //下载图片
        String path = params[0];

        File file = new File(Environment.getExternalStorageDirectory(), picName);

        InputStream in = null;

        FileOutputStream out = null;

        try {
            URL url = new URL(path);

            HttpURLConnection connUrl = (HttpURLConnection) url.openConnection();

            connUrl.setConnectTimeout(5000);

            connUrl.setRequestMethod("GET");

            if(connUrl.getResponseCode() == 200) {

                in = connUrl.getInputStream();

                out = new FileOutputStream(file);

                byte[] buffer = new byte[1024];

                int len;

                while((len = in.read(buffer))!= -1){
                    out.write(buffer, 0, len);
                }
            } else {
                Log.i("111", connUrl.getResponseCode() + "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private Html.ImageGetter getter=new Html.ImageGetter() {
        @Override
        public Drawable getDrawable(String source) {
            //source  src标签属性
            Drawable drawable = null;
            // 封装路径
            File file = new File(Environment.getExternalStorageDirectory(), picName);
            // 判断是否以http开头
            if(source.startsWith("http")){
                if(file.exists()){
                    //图片存在
                   drawable= Drawable.createFromPath(file.getAbsolutePath());
                    drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
                }else {
                    //不存在即开启异步任务加载网络图片
                    new GetImageTask().execute(source);
                }
            }
            return drawable;
        }
    };
}
