package com.beiyuan.appyujing.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.beiyuan.appyujing.R;
import com.beiyuan.appyujing.activity.VoiceCallActivity;
import com.beiyuan.appyujing.activity.VoiceMessageActivity;
import com.beiyuan.appyujing.view.TitleView.OnLeftButtonClickListener;
import com.beiyuan.appyujing.view.TitleView.OnRightButtonClickListener;



public class VoiceControlView extends Fragment {
	private TitleView mTitle;
	TextView text;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// return super.onCreateView(inflater, container, savedInstanceState);
		View parentView = inflater
				.inflate(R.layout.fragment1, container, false);
		Button callBtn = (Button) parentView.findViewById(R.id.button_call);
		Button messageBtn = (Button) parentView
				.findViewById(R.id.button_message);
		callBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),VoiceCallActivity.class);
				VoiceControlView.this.startActivity(intent);
			}
		});
		messageBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(),VoiceMessageActivity.class);
				VoiceControlView.this.startActivity(intent);
			}
		});

		return parentView;

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mTitle = (TitleView) getView().findViewById(R.id.title);
		mTitle.setTitle("语音");
		mTitle.setLeftButton("返回",new OnLeftButtonClickListener() {

			@Override
			public void onClick(View button) {
				// TODO Auto-generated method stub

			}
		});
		mTitle.setRightButton("设置",new OnRightButtonClickListener() {

			@Override
			public void onClick(View button) {
				// TODO Auto-generated method stub

			}
		});
	}
}
