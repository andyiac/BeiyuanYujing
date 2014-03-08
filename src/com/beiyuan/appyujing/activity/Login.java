package com.beiyuan.appyujing.activity;

import com.beiyuan.appyujing.R;
import com.beiyuan.appyujing.R.id;
import com.beiyuan.appyujing.R.layout;
import com.beiyuan.appyujing.tools.Tools;
import com.beiyuan.appyujing.view.TitleView;
import com.beiyuan.appyujing.view.TitleView.OnLeftButtonClickListener;
import com.beiyuan.appyujing.view.TitleView.OnRightButtonClickListener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends MyActivity {

	private TitleView mTitle;
	private TextView tv_forget;
	private EditText et_username, et_password;
	private SharedPreferences preferences;
	private Button bt_login;

	// private RadioGroup rg_status;
	// private RadioButton rb_instructor,rb_teacher,rb_student;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.login);

		initTopTitle();
		init();

		preferences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);

		String username = preferences.getString("name", "").trim();
		String password = preferences.getString("pswd", "").trim();
		et_username.setText(username);
		et_password.setText(password);

		// 忘记密码
		forget();

		loginOnClick();

	}

	private void loginOnClick() {
		bt_login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String username = et_username.getText().toString().trim();
				String password = et_password.getText().toString().trim();
				if ((username.equals("")) || (password.equals(""))) {
					Tools.mToast(Login.this, "用户名或密码不能为空");
				} else {
					// 登录成功
					if (true) {

						Editor editor = preferences.edit();
						editor.putString("name", username);
						editor.putString("pswd", password);
						editor.commit();
					} else {
						// 登录失败
						Tools.mToast(Login.this, "用户名或密码有误");
					}
				}

			}
		});

	}

	private void forget() {
		tv_forget.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Tools.mToast(Login.this, "请各班负责人统计，统一到教学科修改");
			}
		});

	}

	private void init() {
		tv_forget = (TextView) findViewById(R.id.tv_forget);
		et_username = (EditText) findViewById(R.id.login_username);
		et_password = (EditText) findViewById(R.id.login_password);
		bt_login = (Button) findViewById(R.id.login_bt_login);
		// rg_status = (RadioGroup)findViewById(R.id.login_status);
		// rb_instructor = (RadioButton)findViewById(R.id.radio_instructor);
		// rb_teacher = (RadioButton)findViewById(R.id.radio_teacher);
		// rb_student = (RadioButton)findViewById(R.id.radio_student);
	}

	// 顶部菜单栏
	private void initTopTitle() {
		mTitle = (TitleView) findViewById(R.id.title);
		mTitle.setTitle("登录");
		mTitle.setLeftButton("返回", new OnLeftButtonClickListener() {

			@Override
			public void onClick(View button) {
				finish();
			}

		});
		mTitle.setRightButton("注册", new OnRightButtonClickListener() {

			@Override
			public void onClick(View button) {
				Intent intent = new Intent();
				intent.setClass(Login.this, Register.class);
				startActivity(intent);
			}
		});

	}

}