package com.tangpo.douban.fragment;

import com.tangpo.douban.R;
import com.tangpo.douban.activity.SearchActivity;
import com.tangpo.douban.adapter.RecyclerAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeFragment extends BaseFragment {

	private RecyclerView rlv_home;
	private TextView tv_search;

	@Override
	protected View onInflaterContent(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// return inflater.inflate(R.layout.main_tab_home_content, null);
		View view = inflater.inflate(R.layout.main_tab_home_content, null);
		tv_search = (TextView) view.findViewById(R.id.tv_search);
		tv_search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "成功了", 1).show();

				Intent intent = new Intent();
				intent.setClass(getActivity(), SearchActivity.class);
				startActivity(intent);
			}
		});

		// 添加RecyclerView
		rlv_home = (RecyclerView) view.findViewById(R.id.rlv_home);
		// 设置布局管理器
		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
		rlv_home.setLayoutManager(layoutManager);
		String[] dataset = new String[20];
		for (int i = 0; i < dataset.length; i++) {
			dataset[i] = "" + i;
		}

		RecyclerAdapter adapter = new RecyclerAdapter(dataset);
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
