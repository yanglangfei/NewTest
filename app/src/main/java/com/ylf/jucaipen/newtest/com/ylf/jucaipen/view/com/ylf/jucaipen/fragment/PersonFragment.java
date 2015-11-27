package com.ylf.jucaipen.newtest.com.ylf.jucaipen.view.com.ylf.jucaipen.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.ylf.jucaipen.newtest.R;
import com.ylf.jucaipen.newtest.com.ylf.jucaipen.view.com.ylf.jucaipen.activity.NewActivity;

/**
 * Created by Administrator on 2015/11/26.
 */
public class PersonFragment extends Fragment {

    private View view;
    private int index;
    private Button btn_test;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.ui_person,container,false);
         index=getArguments().getInt("index", -1);
        initView();
        return view;
    }

    private void initView() {
        btn_test= (Button) view.findViewById(R.id.btn_test);
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent into=new Intent();
                into.putExtra("index", index);
                into.setClass(getActivity(), NewActivity.class);
                startActivityForResult(into,200);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("111","Person//"+"requestCode:"+requestCode+"resultCode:"+resultCode);
        if(data!=null){
            int i=data.getIntExtra("index",-1);
            Toast.makeText(getActivity(),"i:"+i,Toast.LENGTH_SHORT).show();
        }
    }
}
