package com.ylf.jucaipen.newtest.com.ylf.jucaipen.view.com.ylf.jucaipen.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/26.
 */
public class CityView extends LinearLayout {
    private List<String> provinces=new ArrayList<>();
    private  List<String> citys=new ArrayList<>();
    private  List<String> areas=new ArrayList<>();

    public void setProvinces(List<String> provinces) {
        this.provinces = provinces;
    }

    public void setCitys(List<String> citys) {
        this.citys = citys;
    }


    public void setAreas(List<String> areas) {
        this.areas = areas;
    }

    public CityView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void initProvince(){
    if(provinces!=null&&provinces.size()>0){
        LinearLayout proLy=new LinearLayout(getContext());
        proLy.setOrientation(LinearLayout.VERTICAL);
        TextView proTex=new TextView(getContext());
        for(String province :provinces){
            proTex.setText(province);
            proLy.addView(proTex);
        }
        addView(proLy);
        proLy.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            //获取城市
            }
        });
    }
    }

    public  void initCity(){
     if(citys!=null&&citys.size()>0){
         LinearLayout cityLp=new LinearLayout(getContext());
         cityLp.setOrientation(LinearLayout.VERTICAL);
         TextView cityStr=new TextView(getContext());
         for(String city :citys){
             cityStr.setText(city);
             cityLp.addView(cityStr);
         }
         addView(cityLp);
         cityLp.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                 //获取区、县信息

             }
         });
     }
    }

    public  void initArea(){
        if(areas!=null&&areas.size()>0){
            LinearLayout areaLp=new LinearLayout(getContext());
            areaLp.setOrientation(LinearLayout.VERTICAL);
            TextView areaStr=new TextView(getContext());
            for(String area :areas){
                areaStr.setText(area);
                areaLp.addView(areaStr);
            }
            addView(areaLp);
            areaLp.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }
    }

}
