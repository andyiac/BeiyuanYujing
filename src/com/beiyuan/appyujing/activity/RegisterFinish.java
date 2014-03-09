package com.beiyuan.appyujing.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.beiyuan.appyujing.R;
import com.beiyuan.appyujing.data.College;
import com.beiyuan.appyujing.data.Grade;
import com.beiyuan.appyujing.data.MyClass;
import com.beiyuan.appyujing.data.Profession;
import com.beiyuan.appyujing.data.VirtualData;
import com.beiyuan.appyujing.tools.Tools;
import com.beiyuan.appyujing.view.TitleView;
import com.beiyuan.appyujing.view.TitleView.OnLeftButtonClickListener;
import com.beiyuan.appyujing.view.TitleView.OnRightButtonClickListener;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class RegisterFinish extends MyActivity {

	private TitleView mTitle;
	private VirtualData virtualData;
	private Bundle bundle;

	private Spinner collegeSpinner;
	private Spinner gradeSpinner;
	private Spinner professionSpinner;
	private Spinner classSpinner;

	private String collegeName;
	private String gradeName;
	private String professionName;
	private String className;
	private EditText studentName, studentNickName;
	private EditText studentId, studentPhone;
	private EditText studentIdCard, studentPassword;
	private EditText studentPasswordSure;
	EditText teacherName, teacherNickName;
	EditText teacherPhone, teacherIdCard;
	EditText teacherPassword, teacherPasswordSure;
	LinearLayout collegelayout;
	private Dialog builder = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		virtualData = new VirtualData();
		// 判断身份
		bundle = getIntent().getExtras();
		String flagname = bundle.getString("flag");
		Log.e("mytag", "flagname=====" + flagname);

		if (flagname.equals("headteacher")) {
			setContentView(R.layout.register_finish_teacher);
			initViewHeadTeacher();
		} else if (flagname.equals("teacher")) {
			setContentView(R.layout.register_finish_teacher);
			initViewTeacher();
		} else {
			setContentView(R.layout.register_finish);
			initViewStudent();
		}
		
	}

	/**
	 * 辅导员界面
	 */
	private void initViewHeadTeacher() {
		mTitle = (TitleView) findViewById(R.id.title);
		mTitle.setTitle("注册");
		mTitle.setLeftButton("返回", new StudentLeftListener());
		mTitle.setRightButton("完成", new HeadTeacherRightListener());
		collegelayout = (LinearLayout) findViewById(R.id.teacher_college_layout);
		teacherName = (EditText) findViewById(R.id.teacher_username);
		teacherNickName = (EditText) findViewById(R.id.teacher_nickname);
		teacherPhone = (EditText) findViewById(R.id.teacher_usernum);
		teacherIdCard = (EditText) findViewById(R.id.teacher_useridcard);
		teacherPassword = (EditText) findViewById(R.id.teacher_password);
		teacherPasswordSure = (EditText) findViewById(R.id.teacher_password_sure);
		collegelayout.setVisibility(0);
		EditText teacherCollege = (EditText) findViewById(R.id.teacher_register_college);
		teacherCollege.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showCollegeDialog();
			}
		});

	}

	/**
	 * 普通教师界面
	 */
	private void initViewTeacher() {
		mTitle = (TitleView) findViewById(R.id.title);
		mTitle.setTitle("注册");
		mTitle.setLeftButton("返回", new StudentLeftListener());
		mTitle.setRightButton("完成", new TeacherRightListener());
		teacherName = (EditText) findViewById(R.id.teacher_username);
		teacherNickName = (EditText) findViewById(R.id.teacher_nickname);
		teacherPhone = (EditText) findViewById(R.id.teacher_usernum);
		teacherIdCard = (EditText) findViewById(R.id.teacher_useridcard);
		teacherPassword = (EditText) findViewById(R.id.teacher_password);
		teacherPasswordSure = (EditText) findViewById(R.id.teacher_password_sure);

	}

	/**
	 * 学生界面
	 */
	private void initViewStudent() {
		mTitle = (TitleView) findViewById(R.id.title);
		mTitle.setTitle("注册");
		mTitle.setLeftButton("返回", new StudentLeftListener());
		mTitle.setRightButton("完成", new StudentRightListener());
		studentName = (EditText) findViewById(R.id.student_username);
		studentNickName = (EditText) findViewById(R.id.student_usernickname);
		studentId = (EditText) findViewById(R.id.student_userid);
		studentPhone = (EditText) findViewById(R.id.student_usernum);
		studentIdCard = (EditText) findViewById(R.id.student_useridcard);
		studentPassword = (EditText) findViewById(R.id.student_password);
		studentPasswordSure = (EditText) findViewById(R.id.student_password_sure);
		collegeSpinner = (Spinner) findViewById(R.id.student_register_college);
		gradeSpinner = (Spinner) findViewById(R.id.student_register_grade);
		professionSpinner = (Spinner) findViewById(R.id.student_register_profession);
		classSpinner = (Spinner) findViewById(R.id.student_register_class);

		// 选择
		collegeCheck();
		gradeCheck();
		professionCheck();
		classCheck();

	}

	/**
	 * 学生选择学院
	 * **/

	private String collegeCheck() {
		List<College> college = virtualData.getCollegeInfo();

		List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();

		for (int i = 0; i < college.size(); i++) {

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("name", college.get(i).getCollegeName());

			data.add(map);

		}

		SimpleAdapter adapterCollege = new SimpleAdapter(this, data,
				android.R.layout.simple_spinner_item, new String[] { "name" },
				new int[] { android.R.id.text1 });
		adapterCollege
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		collegeSpinner.setAdapter(adapterCollege);
		collegeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				collegeName = collegeSpinner.getItemAtPosition(arg2).toString()
						+ "";
				Log.e("mytag", "collegeName====" + collegeName);
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		return collegeName;

	}

	/**
	 * 学生选择年级
	 * **/
	private String gradeCheck() {

		List<Grade> grade = virtualData.getGradeInfo();

		List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();

		for (int i = 0; i < grade.size(); i++) {

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("name", grade.get(i).getGradeName());

			data.add(map);

		}

		SimpleAdapter adapterGrade = new SimpleAdapter(this, data,
				android.R.layout.simple_spinner_item, new String[] { "name" },
				new int[] { android.R.id.text1 });
		adapterGrade
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		gradeSpinner.setAdapter(adapterGrade);
		gradeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				gradeName = gradeSpinner.getItemAtPosition(arg2).toString()
						+ "";
				Log.e("mytag", "gradeName====" + gradeName);

			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		return gradeName;

	}

	/**
	 * 学生选择专业
	 * **/
	private String professionCheck() {

		List<Profession> profession = virtualData.getProfessionInfo();

		List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();

		for (int i = 0; i < profession.size(); i++) {

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("name", profession.get(i).getProfessionName());

			data.add(map);

		}

		SimpleAdapter adapterProfession = new SimpleAdapter(this, data,
				android.R.layout.simple_spinner_item, new String[] { "name" },
				new int[] { android.R.id.text1 });
		adapterProfession
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		professionSpinner.setAdapter(adapterProfession);
		professionSpinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						professionName = professionSpinner.getItemAtPosition(
								arg2).toString()
								+ "";
						Log.e("mytag", "professionName====" + professionName);

					}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});
		return professionName;

	}

	/**
	 * 学生选择班级
	 * **/
	private String classCheck() {

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
		return className;

	}

	// 所有左键返回
	private class StudentLeftListener implements OnLeftButtonClickListener {

		@Override
		public void onClick(View button) {
			// TODO Auto-generated method stub
			RegisterFinish.this.finish();
		}

	}

	// 辅导员右键完成,所有数据上传到服务器
	private class HeadTeacherRightListener implements
			OnRightButtonClickListener {

		@Override
		public void onClick(View button) {
			// TODO Auto-generated method stub

		}

	}

	// 普通教师右键完成,所有数据上传到服务器
	private class TeacherRightListener implements OnRightButtonClickListener {

		@Override
		public void onClick(View button) {
			// TODO Auto-generated method stub

		}

	}

	// 学生右键完成,所有数据上传到服务器
	private class StudentRightListener implements OnRightButtonClickListener {

		@Override
		public void onClick(View button) {
			// TODO Auto-generated method stub

			String snameText = studentName.getText().toString().trim();
			String snicknameText = studentNickName.getText().toString().trim();
			String sidText = studentId.getText().toString().trim();
			String snumText = studentPhone.getText().toString().trim();
			String sidCardText = studentIdCard.getText().toString().trim();

			String spasswordText = studentPassword.getText().toString().trim();
			String spasswordSureText = studentPasswordSure.getText().toString()
					.trim();

			if ("".equals(snameText)) {
				Tools.mToast(getApplication(), "姓名不能为空");
			}

			if ("".equals(snicknameText)) {
				Tools.mToast(getApplication(), "昵称不能为空");
			}

			if ("".equals(sidText)) {
				Tools.mToast(getApplication(), "学号不能为空");

			}

			if ("".equals(snumText)) {
				Tools.mToast(getApplication(), "手机号不能为空");
			}

			if ("".equals(sidCardText)) {
				Tools.mToast(getApplication(), "身份证号不能为空");

			}
			if (spasswordText.length() < 7 || spasswordText.length() > 16) {
				Tools.mToast(getApplication(), "密码不符合格式，请重新输入");
				studentPassword.setText("");
				studentPasswordSure.setText("");
			}
			if (!spasswordSureText.equals(spasswordText)) {
				Tools.mToast(getApplication(), "密码不一致，请重新输入");
				studentPasswordSure.setText("");
			}

			if ((!"".equals(snameText)) && (!"".equals(snicknameText))
					&& (!"".equals(sidText)) && (!"".equals(snumText))
					&& (!"".equals(sidCardText)) && (!"".equals(spasswordText))
					&& (!"".equals(spasswordSureText))) {
				if ((spasswordText.length() < 17)
						&& (spasswordText.length() > 6)) {
					if ((spasswordSureText.equals(spasswordText))) {
						// 向服务端提交数据

					}
				}
			}
		}

	}
@SuppressLint("NewApi")
public void showCollegeDialog() {
		
		builder = new Dialog(this);
		builder.setTitle("学院列表");
		builder.show();

		LayoutInflater inflater = LayoutInflater.from(this);
		View viewDialog = inflater.inflate(R.layout.college_dialog, null);
		Display display = this.getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		// 设置对话框的宽高
		LayoutParams layoutParams = new LayoutParams(width * 90 / 100,
				350);
		builder.setContentView(viewDialog, layoutParams);

		ListView listView_college = (ListView) viewDialog
				.findViewById(R.id.college_listview);

		List<College> college = virtualData.getCollegeInfo();

		List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();

		for (int i = 0; i < college.size(); i++) {

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("name", college.get(i).getCollegeName());

			data.add(map);

		}

		SimpleAdapter adapterTeacherCollege = new SimpleAdapter(this,

		data, R.layout.college_dialog_item, new String[] { "name",

		}, new int[] { R.id.names});

		listView_college.setAdapter(adapterTeacherCollege);

		listView_college.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				     
				showProfessionDialog(position); //显示该学院的各个专业

			}

		});
	}

	//专业对话框
	@SuppressLint("NewApi")
	public void showProfessionDialog(int id) {
		builder = new Dialog(this);
		builder.setTitle("专业列表");
		builder.show();

		LayoutInflater inflater = LayoutInflater.from(this);
		View viewDialog = inflater.inflate(R.layout.college_dialog, null);
		Display display = this.getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		// 设置对话框的宽高
		LayoutParams layoutParams = new LayoutParams(width * 90 / 100,
				350);
		builder.setContentView(viewDialog, layoutParams);

		ListView listView_profession = (ListView) viewDialog
				.findViewById(R.id.college_listview);


		List<Profession> profession = virtualData.getProfessionInfo();

		List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();

		for (int i = 0; i < profession.size(); i++) {

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("name", profession.get(i).getProfessionName());

			data.add(map);

		}

		SimpleAdapter adapterTeacherProfession = new SimpleAdapter(this,

		data, R.layout.college_dialog_item, new String[] { "name",

		}, new int[] { R.id.names});

		listView_profession.setAdapter(adapterTeacherProfession);

		listView_profession.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				showClassesDialog(position);
				

			}

		});
	}

	//班级对话框
		@SuppressLint("NewApi")
		public void showClassesDialog(int id) {
			builder = new Dialog(this);
			builder.setTitle("班级列表");
			builder.show();

			LayoutInflater inflater = LayoutInflater.from(this);
			View viewDialog = inflater.inflate(R.layout.college_dialog, null);
			Display display = this.getWindowManager().getDefaultDisplay();
			Point size = new Point();
		    display.getSize(size);
		    int width = size.x;
			
			// 设置对话框的宽高
			LayoutParams layoutParams = new LayoutParams(width * 90 / 100,
					350);
			builder.setContentView(viewDialog, layoutParams);

			ListView listView_class = (ListView) viewDialog
					.findViewById(R.id.college_listview);

			VirtualData virtualData = new VirtualData();

			List<MyClass> myClasses = virtualData.getClassInfo();

			List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();

			for (int i = 0; i < myClasses.size(); i++) {

				HashMap<String, Object> map = new HashMap<String, Object>();

				map.put("name", myClasses.get(i).getClassName());

				data.add(map);

			}

			SimpleAdapter adapterTeacher = new SimpleAdapter(this,

			data, R.layout.college_dialog_item, new String[] { "name",

			}, new int[] { R.id.names});

			listView_class.setAdapter(adapterTeacher);

			listView_class.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					
					

				}

			});
		}

}
