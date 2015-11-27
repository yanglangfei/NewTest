package com.ylf.jucaipen.newtest.com.ylf.jucaipen.view.com.ylf.jucaipen.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.Html;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Administrator on 2015/11/4.
 */
public class MyTextView extends TextView {
    //加载image路径
    //0 本地    1 本项目    2 网络
    private  int type;
    //设置的Text文本
    private  String text;
    //网络的Getting
    private  NetGetting netGetting;
    //图片文件路径
    private  String pic="pics";

    public void setTextStr(String text) {
        this.text = text;
        initView();
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

   public  MyTextView(Context context) {
    super(context);
   }


    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView() {
        netGetting=new NetGetting();
        setText(Html.fromHtml(text,netGetting,null));
    }

    

    class  NetGetting implements Html.ImageGetter {

        @Override
        public Drawable getDrawable(String source) {
            //source  src标签属性
            Drawable drawable = null;
            // 封装路径
            // 判断是否以http开头
            if(source.startsWith("http")){
                File file = new File(Environment.getExternalStorageDirectory(), pic);
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
    }
    class  GetImageTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {
            // 加载网络图片
           loadNetPic(params);
            return null;
        }

        private void loadNetPic(String[] params) {
            {
                {
                    //下载图片
                    String path = params[0];

                    File file = new File(Environment.getExternalStorageDirectory(), pic);

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

            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            // 当执行完成后再次为其设置一次
            setText(Html.fromHtml(text,netGetting,null));
        }
    }


}
