package com.ylf.jucaipen.newtest.com.ylf.jucaipen.view.com.ylf.jucaipen.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ylf.jucaipen.newtest.R;

/**
 * Created by Administrator on 2015/11/26.
 */
public class AnimFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.ui_anim,container,false);
        return view;
    }
}
