package com.tangpo.douban.adapter;

import com.tangpo.douban.R;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerAdapter extends Adapter<RecyclerAdapter.ViewHolder> {

	private String[] dataTextView;
	
	public RecyclerAdapter(String[] dataTextView) {
		this.dataTextView = dataTextView;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = View.inflate(parent.getContext(), R.layout.list_item_home, null);
		ViewHolder holder = new ViewHolder(view);

		holder.mImageView = (ImageView) view.findViewById(R.id.iv_head);
		holder.mTextView = (TextView) view.findViewById(R.id.tv_fm);
		return holder;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		holder.mTextView.setText(dataTextView[position]);

	}

	@Override
	public int getItemCount() {
		return dataTextView.length;
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		/**
		 * 显示fragment的图片
		 */
		private ImageView mImageView;
		/**
		 * 显示fragment的文字
		 */
		private TextView mTextView;

		public ViewHolder(View itemView) {
			super(itemView);
		}

	}
}
