package com.ylf.jucaipen.newtest;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends Activity {
    private TextView info;
    private NfcAdapter nfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        info= (TextView) findViewById(R.id.info);
        nfcAdapter=NfcAdapter.getDefaultAdapter(this);
        if(nfcAdapter==null){
            info.setText("手机不支持NFC");
            finish();
            return;
        }
        if(!nfcAdapter.isEnabled()){
            info.setText("请在系统设置中先开启NFC功能");
            finish();
            return;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //得到是否检测到ACTION_TECH_DISCOVERED触发
        if(NfcAdapter.ACTION_TECH_DISCOVERED.equals(getIntent().getAction())){
            //处理Intent
            processIntent(getIntent());
        }
    }

    private void processIntent(Intent intent) {
       //取出封装在intent中的TAG
        Tag tags=intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        for(String tag :tags.getTechList()){
           System.out.print("tag:"+tag);
        }
        boolean auth = false;
        //读取TAG
        MifareClassic mfc=MifareClassic.get(tags);
        try {
            mfc.connect();
            int type = mfc.getType();//获取TAG的类型
            //获取TAG中包含的扇区数
            int sector=mfc.getSectorCount();
            String typeS="";
            String metaInfo="";
            switch (type) {
                case MifareClassic.TYPE_CLASSIC:
                    typeS = "TYPE_CLASSIC";
                    break;
                case MifareClassic.TYPE_PLUS:
                    typeS = "TYPE_PLUS";
                    break;
                case MifareClassic.TYPE_PRO:
                    typeS = "TYPE_PRO";
                    break;
                case MifareClassic.TYPE_UNKNOWN:
                    typeS = "TYPE_UNKNOWN";
                    break;
            }
            metaInfo += "卡片类型：" + typeS + "\n共" + sector + "个扇区\n共"
                    + mfc.getBlockCount() + "个块\n存储空间: " + mfc.getSize() + "B\n";
            for (int j = 0; j < sector; j++) {
                //Authenticate a sector with key A.
                auth = mfc.authenticateSectorWithKeyA(j,
                        MifareClassic.KEY_DEFAULT);
                int bCount;
                int bIndex;
                if (auth) {
                    metaInfo += "Sector " + j + ":验证成功\n";
                    // 读取扇区中的块
                    bCount = mfc.getBlockCountInSector(j);
                    bIndex = mfc.sectorToBlock(j);
                    for (int i = 0; i < bCount; i++) {
                        byte[] data = mfc.readBlock(bIndex);
                        metaInfo += "Block " + bIndex + " : "
                                + bytesToHexString(data) + "\n";
                        bIndex++;
                    }
                } else {
                    metaInfo += "Sector " + j + ":验证失败\n";
                }
            }
            info.setText(metaInfo);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    //字符序列转换为16进制字符串
    private String bytesToHexString(byte[] data) {
        StringBuilder stringBuilder = new StringBuilder("0x");
        if (data == null || data.length <= 0) {
            return null;
        }
        char[] buffer = new char[2];
        for (int i = 0; i < data.length; i++) {
            buffer[0] = Character.forDigit((data[i] >>> 4) & 0x0F, 16);
            buffer[1] = Character.forDigit(data[i] & 0x0F, 16);
            System.out.println(buffer);
            stringBuilder.append(buffer);
        }
        return null;
    }


}
