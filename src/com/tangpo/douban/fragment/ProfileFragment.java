package com.tangpo.douban.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tangpo.douban.R;

public class ProfileFragment extends BaseFragment {

    @Override
    protected View onInflaterContent(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_tab_profile_content, null);
    }

    @Override
    protected void onFragmentSeletedPre(boolean gainSelect) {

    }

    @Override
    public void onFragmentShowChanged(boolean gainShow) {

    }


}
