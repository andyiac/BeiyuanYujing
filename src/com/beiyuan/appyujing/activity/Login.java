package com.beiyuan.appyujing.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.beiyuan.appyujing.R;
import com.beiyuan.appyujing.service.UrlService;
import com.beiyuan.appyujing.service.UrlServiceImpl;
import com.beiyuan.appyujing.tools.Tools;
import com.beiyuan.appyujing.view.TitleView;
import com.beiyuan.appyujing.view.TitleView.OnLeftButtonClickListener;
import com.beiyuan.appyujing.view.TitleView.OnRightButtonClickListener;

public class Login extends MyActivity {

	private TitleView mTitle;
	private TextView tv_forget;
	private EditText et_username, et_password;
	private SharedPreferences preferences;
	private Button bt_login;
	String username;
	String password;

	private ProgressDialog pdlogin;
	
	JSONObject jsonLoginRst;
	private UrlService urlService = new UrlServiceImpl();
	Handler handler;

	JSONObject obj;
	String strLoginRst;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.login);

		initTopTitle();
		init();

		preferences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);

		username = preferences.getString("name", "").trim();
		password = preferences.getString("pswd", "").trim();
		et_username.setText(username);
		et_password.setText(password);

		// 忘记密码
		forget();

		loginOnClick();
		handler = new Handler() {

			public void handleMessage(Message msg) {

				switch (msg.what) {
				case 1:
					Toast.makeText(Login.this, "登录成功", Toast.LENGTH_SHORT)
							.show();
					Editor editor = preferences.edit();
					editor.putString("name", username);
					editor.putString("pswd", password);
					editor.commit();

					// Intent intent = new Intent();
					// intent.setClass(Login.this, AboutUs.class);
					// startActivity(intent);
					// finish();

					break;

				case -1:
					Toast.makeText(Login.this, "用户名无效", Toast.LENGTH_SHORT)
							.show();
					break;
				case 2:
					Toast.makeText(Login.this, "密码错误", Toast.LENGTH_SHORT)
							.show();
					break;
				case 5:
					Toast.makeText(Login.this, "连接服务器失败", Toast.LENGTH_SHORT)
							.show();
					break;
				}
			}
		};

	}

	private void loginOnClick() {
		bt_login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				username = et_username.getText().toString().trim();
				password = et_password.getText().toString().trim();

				if (password.equals(null) || password == "") {
					Tools.mToast(Login.this, "用户名或密码不能为空");
				} else if (password.length() < 6) {
					Tools.mToast(Login.this, "密码不能少于6个字符");
				} else {

					pdlogin = Tools.pd(Login.this);
					obj = new JSONObject();
					try {
						// 从这里将开始连接服务器

						obj.put("role", "老师");
						obj.put("password", password);
						obj.put("username", username);
						System.out.println("password============" + password);

						System.out.println("u_name============" + username);

						// 登陆连接服务器，开辟新的线程
						Thread threadLogin = new Thread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								Looper.prepare();

								// strLoginRst = "success";

								jsonLoginRst = urlService
										.sentParams2Server(obj);

								System.out.println("jsonLoginRst======"
										+ jsonLoginRst);
								Log.i("JSON", "jsonLoginRst" + jsonLoginRst);

								System.out.println("json = "
										+ jsonLoginRst.toString());
								try {
									strLoginRst = jsonLoginRst
											.getString("Status");
								} catch (JSONException e) {
									e.printStackTrace();
									Message message = new Message();
									message.what = 5;
									handler.sendMessage(message);
									pdlogin.dismiss();
									return;
								}

								if (strLoginRst.equals("Success")) {

									Message message = new Message();
									message.what = 1;
									handler.sendMessage(message);
									pdlogin.dismiss();

								} else if (strLoginRst.equals("NotHaveUser")) {
									Message message = new Message();
									message.what = -1;
									handler.sendMessage(message);
									pdlogin.dismiss();

								} else if (strLoginRst.equals("PasswordError")) {
									Message message = new Message();
									message.what = 2;
									handler.sendMessage(message);
									pdlogin.dismiss();

								} else {
									Message message = new Message();
									message.what = 5;
									handler.sendMessage(message);
									pdlogin.dismiss();
								}
							}
						});
						threadLogin.start();

					} catch (Exception e) {

						Toast.makeText(Login.this, "连接服务器失败···",
								Toast.LENGTH_SHORT).show();
						pdlogin.dismiss();
						e.printStackTrace();
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