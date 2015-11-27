package com.ylf.jucaipen.newtest.com.ylf.jucaipen.view.com.ylf.jucaipen.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ylf.jucaipen.newtest.R;
import com.ylf.jucaipen.newtest.com.ylf.jucaipen.view.com.ylf.jucaipen.adapter.VpAdapter;
import com.ylf.jucaipen.newtest.com.ylf.jucaipen.view.com.ylf.jucaipen.fragment.AnimFragment;
import com.ylf.jucaipen.newtest.com.ylf.jucaipen.view.com.ylf.jucaipen.fragment.PersonFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/4.
 */
public class MyList extends FragmentActivity {
    private List<Fragment> fragment=new ArrayList<Fragment>();
    private FragmentManager fm;
    private RadioGroup rg;
    private FragmentTransaction trans;
    private ViewPager vp;
    private VpAdapter vpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_turn);
        initView();
    }

    private void initView() {
        PersonFragment personFragment=new PersonFragment();
        AnimFragment animFragment=new AnimFragment();
        Bundle bundle=new Bundle();
        fragment.add(personFragment);
        fragment.add(animFragment);
        bundle.putInt("index", 3);
        personFragment.setArguments(bundle);
        rg= (RadioGroup) findViewById(R.id.rg);
        vp= (ViewPager) findViewById(R.id.vp);
        vpAdapter=new VpAdapter(getSupportFragmentManager(),fragment);
        vp.setAdapter(vpAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("111","MyList//"+"requestCode:"+requestCode+"resultCode:"+resultCode);
        if(data!=null){
            int i=data.getIntExtra("index",-1);
           Log.i("111","i:" + i);
        }
    }


}
