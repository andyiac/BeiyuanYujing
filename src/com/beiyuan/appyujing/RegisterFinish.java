package com.beiyuan.appyujing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.beiyuan.appyujing.data.Institute;
import com.beiyuan.appyujing.data.MyClass;
import com.beiyuan.appyujing.data.Specialty;
import com.beiyuan.appyujing.data.VirtualData;
import com.beiyuan.appyujing.tools.Tools;
import com.beiyuan.appyujing.view.TitleView;
import com.beiyuan.appyujing.view.TitleView.OnLeftButtonClickListener;
import com.beiyuan.appyujing.view.TitleView.OnRightButtonClickListener;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class RegisterFinish extends Activity {

	private TitleView mTitle;

	private EditText userName;
	private EditText userNickname;
	private EditText userNo;
	private EditText userIdCard;
	private EditText userNum;
	private EditText passward;
	private EditText passwordSure;
	
	private EditText teacherInstitute;

	private VirtualData virtualData;

	private Bundle bundle;
	private String flagname;
	private String instituteName;
	private String gradeName;
	private String specialtyName;
	private String className;
	private LinearLayout layoutTeacherInstitute;
	private LinearLayout layoutSno;
	private LinearLayout layoutInstitute;
	private LinearLayout layoutGrade;
	private LinearLayout layoutSpecialty;
	private LinearLayout layoutClass;

	private String name, nickname, sno, idCard, num;
	private String passwordText, passwordSureText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_finish);
		// 初始化总界面
		initView();
		
		// 标题按钮设置
		mTitle = (TitleView) findViewById(R.id.title);
		mTitle.setTitle("注册");
		mTitle.setLeftButton("返回", new OnLeftButtonClickListener() {

			@Override
			public void onClick(View button) {
				RegisterFinish.this.finish();
			}

		});

		mTitle.setRightButton("完成", new OnRightButtonClickListener() {
			public void onClick(View button) {

				// 所有数据上传到服务器
				name = userName.getText().toString().trim();
				nickname = userNickname.getText().toString().trim();
			    sno = userNo.getText().toString().trim();
				idCard = userIdCard.getText().toString().trim();
				num = userNum.getText().toString().trim();
				passwordText = passward.getText().toString().trim();
				passwordSureText = passwordSure.getText().toString().trim();

				if ("".equals(name)) {
					Tools.mToast(getApplication(), "姓名不能为空");
				}

				if ("".equals(nickname)) {
					Tools.mToast(getApplication(), "昵称不能为空");
				}

				if ("".equals(sno)) {
					Tools.mToast(getApplication(), "学号不能为空");

				}

				if ("".equals(idCard)) {
					Tools.mToast(getApplication(), "身份证号不能为空");

				}
				if ("".equals(num)) {
					Tools.mToast(getApplication(), "手机号不能为空");
				}
				if (passwordText.length() < 6 || passwordText.length() > 12) {
					Tools.mToast(getApplication(), "密码不符合格式，请重新输入");
					passward.setText("");
					passwordSure.setText("");
				}
				if (!passwordSureText.equals(passwordText)) {
					Tools.mToast(getApplication(), "密码不一致，请重新输入");
					passwordSure.setText("");
				}

				if ((!"".equals(name)) && (!"".equals(idCard))
						&& (!"".equals(num))) {
					if ((!("".equals(passwordText)))
							&& (passwordText.length() > 5)) {
						if ((passwordSureText.equals(passwordText))) {
							// 向服务端提交数据

						}
					}
				}
			}
		});
	}

	/**
	 * 总界面初始化
	 */
	private void initView() {

		userName = (EditText) findViewById(R.id.user_info_username);
		userNickname = (EditText) findViewById(R.id.user_info_usernickname);
	    userNo = (EditText)findViewById(R.id.user_info_userno);
		userIdCard = (EditText) findViewById(R.id.user_info_useridcard);
		userNum = (EditText) findViewById(R.id.user_info_usernum);
		passward = (EditText) findViewById(R.id.user_info_password);
		passwordSure = (EditText) findViewById(R.id.user_info_password_sure);
		teacherInstitute = (EditText)findViewById(R.id.teacher_institute);
		layoutTeacherInstitute = (LinearLayout)findViewById(R.id.teacher_institute_layout);
		layoutSno = (LinearLayout) findViewById(R.id.sno_layout);
		layoutInstitute = (LinearLayout) findViewById(R.id.institute_layout);
		layoutGrade = (LinearLayout) findViewById(R.id.grade_layout);
		layoutSpecialty = (LinearLayout) findViewById(R.id.specialty_layout);
		layoutClass = (LinearLayout) findViewById(R.id.class_layout);

		virtualData = new VirtualData();

		// 判断身份
		bundle = getIntent().getExtras();
		flagname = bundle.getString("flag");
		Log.e("mytag", "flagname=====" + flagname);

		if (flagname.equals("instructor")) {
			initViewInstructor();
		} else if (flagname.equals("teacher")) {
			initViewTeacher();
		} else {
			initViewStudent();
		}
	}

	/**
	 * 辅导员界面
	 */
	private void initViewInstructor() {
		layoutTeacherInstitute.setVisibility(0);
		layoutGrade.setVisibility(0);
		layoutSpecialty.setVisibility(0);
		layoutClass.setVisibility(0);
		// 选择学院
		instituteCheck();
		// 选择年级
		gradeCheck();
		// 选择专业
		specialtyCheck();
		// 选择班级
		classCheck();

	}

	/**
	 * 普通教师界面
	 */
	private void initViewTeacher() {

	}

	/**
	 * 学生界面
	 */
	private void initViewStudent() {
        layoutSno.setVisibility(0);
		layoutInstitute.setVisibility(0);
		layoutGrade.setVisibility(0);
		layoutSpecialty.setVisibility(0);
		layoutClass.setVisibility(0);
		// 选择
		instituteCheck();
		gradeCheck();
		specialtyCheck();
		classCheck();

	}

	/**
	 * 选择学院
	 * **/

	private void instituteCheck() {
		virtualData = new VirtualData();
		final Spinner instituteSpinner = (Spinner) findViewById(R.id.register_institute);
		List<Institute> institute = virtualData.getInstituteInfo();

		List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();

		for (int i = 0; i < institute.size(); i++) {

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("name", institute.get(i).getInstituteName());

			data.add(map);

		}

		SimpleAdapter adapterInstitute = new SimpleAdapter(this, data,
				android.R.layout.simple_spinner_item, new String[] { "name" },
				new int[] { android.R.id.text1 });
		adapterInstitute
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		instituteSpinner.setAdapter(adapterInstitute);
		instituteSpinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						instituteName = instituteSpinner
								.getItemAtPosition(arg2).toString() + "";
						Log.e("mytag", "instituteName====" + instituteName);

					}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});

	}

	/**
	 * 选择年级
	 * **/
	private void gradeCheck() {
		virtualData = new VirtualData();

		final Spinner specialtySpinner = (Spinner) findViewById(R.id.register_specialty);
		List<Specialty> specialty = virtualData.getSpecialtyInfo();

		List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();

		for (int i = 0; i < specialty.size(); i++) {

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("name", specialty.get(i).getSpecialtyName());

			data.add(map);

		}

		SimpleAdapter adapterSpecialty = new SimpleAdapter(this, data,
				android.R.layout.simple_spinner_item, new String[] { "name" },
				new int[] { android.R.id.text1 });
		adapterSpecialty
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		specialtySpinner.setAdapter(adapterSpecialty);
		specialtySpinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						specialtyName = specialtySpinner
								.getItemAtPosition(arg2).toString() + "";
						Log.e("mytag", "specialtyName====" + specialtyName);

					}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});

	}

	/**
	 * 选择专业
	 * **/
	private void specialtyCheck() {
		virtualData = new VirtualData();

		final Spinner specialtySpinner = (Spinner) findViewById(R.id.register_specialty);
		List<Specialty> specialty = virtualData.getSpecialtyInfo();

		List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();

		for (int i = 0; i < specialty.size(); i++) {

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("name", specialty.get(i).getSpecialtyName());

			data.add(map);

		}

		SimpleAdapter adapterSpecialty = new SimpleAdapter(this, data,
				android.R.layout.simple_spinner_item, new String[] { "name" },
				new int[] { android.R.id.text1 });
		adapterSpecialty
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		specialtySpinner.setAdapter(adapterSpecialty);
		specialtySpinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						specialtyName = specialtySpinner
								.getItemAtPosition(arg2).toString() + "";
						Log.e("mytag", "specialtyName====" + specialtyName);

					}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});

	}

	/**
	 * 选择班级
	 * **/
	private void classCheck() {

		final Spinner classSpinner = (Spinner) findViewById(R.id.register_class);
		List<MyClass> myClass = virtualData.getClassInfo();

		List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();

		for (int i = 0; i < myClass.size(); i++) {

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("name", myClass.get(i).getClassName());

			data.add(map);

		}

		SimpleAdapter adapterClass = new SimpleAdapter(this, data,
				android.R.layout.simple_spinner_item, new String[] { "name" },
				new int[] { android.R.id.text1 });
		adapterClass
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		classSpinner.setAdapter(adapterClass);
		classSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				className = classSpinner.getItemAtPosition(arg2).toString()
						+ "";
				Log.e("mytag", "className====" + className);

			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

}
