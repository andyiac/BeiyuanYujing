package com.beiyuan.appyujing.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.beiyuan.appyujing.R;
import com.beiyuan.appyujing.R.id;
import com.beiyuan.appyujing.R.layout;
import com.beiyuan.appyujing.view.TitleView.OnLeftButtonClickListener;
import com.beiyuan.appyujing.view.TitleView.OnRightButtonClickListener;

public class Fragment1 extends Fragment {
	private TitleView mTitle;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
//		return super.onCreateView(inflater, container, savedInstanceState);
		View parentView = inflater.inflate(R.layout.fragment1, container, false);
		Button backBtn = (Button)parentView.findViewById(R.id.button1);
		Button toOtherActivityBtn = (Button)parentView.findViewById(R.id.button2);

		toOtherActivityBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "可以点击", Toast.LENGTH_SHORT).show();
			}
		});
		
		return parentView;
		
		
//		return inflater.inflate(R.layout.fragment1, container, false);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	

		mTitle = (TitleView) getView().findViewById(R.id.title);
		mTitle.setTitle("首页");
		mTitle.setLeftButton("123", new OnLeftButtonClickListener(){

			@Override
			public void onClick(View button) {
				Toast.makeText(getActivity(), "可以点击", Toast.LENGTH_SHORT).show();
			}
			
		});
		mTitle.setRightButton("帮忙", new OnRightButtonClickListener() {

			@Override
			public void onClick(View button) {
				Toast.makeText(getActivity(), "可以点击", Toast.LENGTH_SHORT).show();
			}
		});

	}
}
