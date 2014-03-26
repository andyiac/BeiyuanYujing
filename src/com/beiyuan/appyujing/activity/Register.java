package com.beiyuan.appyujing.activity;

import java.util.ArrayList;
import java.util.List;

import com.beiyuan.appyujing.R;
import com.beiyuan.appyujing.R.id;
import com.beiyuan.appyujing.R.layout;
import com.beiyuan.appyujing.service.UrlServiceImpl;
import com.beiyuan.appyujing.view.TitleView;
import com.beiyuan.appyujing.view.TitleView.OnLeftButtonClickListener;
import com.beiyuan.appyujing.view.TitleView.OnRightButtonClickListener;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

public class Register extends MyActivity {

	private TitleView mTitle;
	private RadioGroup user;
	private final String TAG = "Register";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rigster);
		initView();
	}

	private void initView() {

		user = (RadioGroup) findViewById(R.id.radiogroup);

		Thread threadRegister = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				UrlServiceImpl urlService = new UrlServiceImpl();
				List<String> listString = new ArrayList<String>();
				listString = urlService.getCollegeList("college",
						urlService.getJsonContent());
				Log.i(TAG, "listString===" + listString);

			}
		});
		threadRegister.start();
		// 标题响应事件
		mTitle = (TitleView) findViewById(R.id.title);
		mTitle.setTitle("注册");
		mTitle.setLeftButton("返回", new OnLeftButtonClickListener() {

			@Override
			public void onClick(View button) {
				Register.this.finish();
			}

		});

		mTitle.setRightButton("下一步", new OnRightButtonClickListener() {

			@SuppressLint("NewApi")
			@Override
			public void onClick(View button) {

				if (user.getCheckedRadioButtonId() == R.id.radio_instructor) {

					Intent intenta = new Intent(Register.this,
							RegisterFinish.class);
					Bundle bundlea = new Bundle();
					bundlea.putString("flag", "headteacher");
					intenta.putExtras(bundlea);
					Register.this.startActivity(intenta);

				} else if (user.getCheckedRadioButtonId() == R.id.radio_teacher) {
					Intent intentb = new Intent(Register.this,
							RegisterFinish.class);
					Bundle bundleb = new Bundle();
					bundleb.putString("flag", "teacher");
					intentb.putExtras(bundleb);
					Register.this.startActivity(intentb);
				} else {
					Intent intentc = new Intent(Register.this,
							RegisterFinish.class);
					Bundle bundlec = new Bundle();
					bundlec.putString("flag", "student");
					intentc.putExtras(bundlec);
					Register.this.startActivity(intentc);
				}

			}
		});

	}
}
