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

		name = userName.getText().toString().trim();
		idCard = userIdCard.getText().toString().trim();
		num = userNum.getText().toString().trim();
		passwordText = passward.getText().toString().trim();
		passwordSureText = passwordSure.getText().toString().trim();


		mTitle = (TitleView) findViewById(R.id.title);
		mTitle.setTitle("注册");
		mTitle.setLeftButton("返回", new OnLeftButtonClickListener() {

			@Override
			public void onClick(View button) {
				Toast.makeText(getApplication(), "返回", Toast.LENGTH_SHORT)
						.show();
			}

		});
		
		/*-------------------------------逻辑思维-------------------------------------*/

		mTitle.setRightButton("下一步", new OnRightButtonClickListener() {

			@SuppressLint("NewApi")
			@Override
			public void onClick(View button) {

				if ("".equals(name) ||"".equals(idCard)
						|| "".equals(num)) {
					Toast.makeText(getApplication(), "不能为空", Toast.LENGTH_SHORT)
							.show();
				}
				
			   if ((passwordText.length()!= 0)&& (passwordText.length() < 6)) {
			 		Toast.makeText(getApplication(), "密码格式不正确，请重新输入！",
							Toast.LENGTH_SHORT).show();
					passward.setText("");
				}

			  if ((!("".equals(passwordSureText)))
						&& (!passwordSureText.equals(passwordText))) {
					Toast.makeText(getApplication(), "密码不一致，请重新输入！", Toast.LENGTH_SHORT)
							.show();
					passwordSure.setText("");
				}


				else if (user.getCheckedRadioButtonId() == R.id.radio_instructor) {
					Toast.makeText(getApplication(), "辅导员", Toast.LENGTH_SHORT)
							.show();
					Intent intenta = new Intent(Register.this,
							RegisterFinish.class);
					Bundle bundlea = new Bundle();
					bundlea.putString("flag", "instructor");
					intenta.putExtras(bundlea);
					Register.this.startActivity(intenta);
				} else if (user.getCheckedRadioButtonId() == R.id.radio_teacher) {
					Toast.makeText(getApplication(), "普通教师", Toast.LENGTH_SHORT)
							.show();
					Intent intentb = new Intent(Register.this,
							RegisterFinish.class);
					Bundle bundleb = new Bundle();
					bundleb.putString("flag", "teacher");
					intentb.putExtras(bundleb);
					Register.this.startActivity(intentb);
				} else {
					Toast.makeText(getApplication(), "学生", Toast.LENGTH_SHORT)
							.show();
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
