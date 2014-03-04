package com.beiyuan.appyujing;

import com.beiyuan.appyujing.view.TitleView;
import com.beiyuan.appyujing.view.TitleView.OnLeftButtonClickListener;
import com.beiyuan.appyujing.view.TitleView.OnRightButtonClickListener;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Register extends Activity {

	private EditText userName;
	private EditText userIdCard;
	private EditText userNum;
	private EditText passward;
	private EditText passwordSure;
	private TitleView mTitle;
	private RadioGroup user;
	private String name, idCard, num;
	private String passwordText, passwordSureText;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rigster);
		initView();
	}

	private void initView() {
		userName = (EditText) findViewById(R.id.user_info_username);
		userIdCard = (EditText) findViewById(R.id.user_info_usernum);
		userNum = (EditText) findViewById(R.id.user_info_userphone);
		passward = (EditText) findViewById(R.id.user_info_password);
		passwordSure = (EditText) findViewById(R.id.user_info_password_sure);
		user = (RadioGroup) this.findViewById(R.id.radiogroup);
		
        //标题响应事件
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

				name = userName.getText().toString().trim();
				idCard = userIdCard.getText().toString().trim();
				num = userNum.getText().toString().trim();
				passwordText = passward.getText().toString().trim();
				passwordSureText = passwordSure.getText().toString().trim();
				
				if("".equals(name)) {
					Toast.makeText(getApplication(), "姓名不能为空", Toast.LENGTH_SHORT).show();
				}
				if("".equals(idCard)) {
					Toast.makeText(getApplication(), "身份证号不能为空", Toast.LENGTH_SHORT).show();
				}
				if("".equals(num)) {
					Toast.makeText(getApplication(), "手机号不能为空", Toast.LENGTH_SHORT).show();
				}
				if(passwordText.length()<6||passwordText.length()>12) {
					Toast.makeText(getApplication(), "密码不符合格式，请重新输入", Toast.LENGTH_SHORT).show();
					passward.setText("");
					passwordSure.setText("");
				}
				if(!passwordSureText.equals(passwordText)) {
					Toast.makeText(getApplication(), "密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
					passwordSure.setText("");
				}
				
				if ((!"".equals(name)) && (!"".equals(idCard)) && (!"".equals(num))) {
					if ((!("".equals(passwordText)))
							&& (passwordText.length() > 5)) {
						if ((passwordSureText.equals(passwordText))) {

							if (user.getCheckedRadioButtonId() == R.id.radio_instructor) {
								
								Intent intenta = new Intent(Register.this,
										RegisterFinish.class);
								Bundle bundlea = new Bundle();
								bundlea.putString("flag", "instructor");
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
					}
				}
			}
		});

	}

	protected void Switch(int checkedRadioButtonId) {
		// TODO Auto-generated method stub

	}

}
