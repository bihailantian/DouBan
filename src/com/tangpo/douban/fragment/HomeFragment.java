package com.tangpo.douban.fragment;

import com.tangpo.douban.R;
import com.tangpo.douban.adapter.RecyclerAdapter;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends BaseFragment {

	private RecyclerView rlv_home;
	
	
    @Override
    protected View onInflaterContent(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.main_tab_home_content, null);
    	View view  = inflater.inflate(R.layout.main_tab_home_content, null);
    	//添加RecyclerView
    	rlv_home = (RecyclerView) view.findViewById(R.id.rlv_home);
    	// 设置布局管理器
    	LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
    	rlv_home.setLayoutManager(layoutManager);
    	String[] dataset = new String[20];
    	for (int i = 0; i < dataset.length; i++) {
    		dataset[i] = "item"+i;
		}
    	
    	RecyclerAdapter adapter  = new RecyclerAdapter(dataset);
    	rlv_home.setAdapter(adapter);
    	return view;
    }

    @Override
    protected void onFragmentSeletedPre(boolean gainSelect) {

    }

    @Override
    public void onFragmentShowChanged(boolean gainShow) {

    }


}
