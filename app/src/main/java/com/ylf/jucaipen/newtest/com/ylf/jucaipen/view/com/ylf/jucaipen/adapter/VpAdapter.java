package com.ylf.jucaipen.newtest.com.ylf.jucaipen.view.com.ylf.jucaipen.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2015/11/26.
 */
public class VpAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragment;

    public VpAdapter(FragmentManager fm, List<Fragment> fragment) {
        super(fm);
        this.fragment=fragment;
    }

    @Override
    public Fragment getItem(int position) {
        return fragment.get(position);
    }

    @Override
    public int getCount() {
        return fragment.size();
    }
}
