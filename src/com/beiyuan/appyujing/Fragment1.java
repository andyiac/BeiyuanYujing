package com.beiyuan.appyujing;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Fragment1 extends Fragment {

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
//		backBtn.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				if(mListener!=null)mListener.backEvent();
//			}
//		});
		
		toOtherActivityBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				Intent intent = new Intent(getActivity(), OtherActivity1.class);
//				startActivity(intent);
				Toast.makeText(getActivity(), "可以点击", Toast.LENGTH_SHORT).show();
			}
		});
		
		return parentView;
		
		
//		return inflater.inflate(R.layout.fragment1, container, false);
	}

}
