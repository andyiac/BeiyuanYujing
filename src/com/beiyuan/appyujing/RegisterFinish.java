package com.beiyuan.appyujing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.beiyuan.appyujing.data.Institute;
import com.beiyuan.appyujing.data.MyClass;
import com.beiyuan.appyujing.data.Specialty;
import com.beiyuan.appyujing.data.VirtualData;
import com.beiyuan.appyujing.view.TitleView;
import com.beiyuan.appyujing.view.TitleView.OnLeftButtonClickListener;
import com.beiyuan.appyujing.view.TitleView.OnRightButtonClickListener;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class RegisterFinish extends Activity {

	private TitleView mTitle;
	private Spinner instituteSpinner;
	private Spinner specialtySpinner;
	private Spinner classSpinner;
	private VirtualData virtualData;
	private Bundle bundle;
	private String flagname;
	private String instituteName;
	private String specialtyName;
	private String className;
	private LinearLayout layoutSpecialty;
	private LinearLayout layoutClass;

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
			//所有数据上传到服务器
			}

		});
	}

	private void initView() {

		instituteSpinner = (Spinner) findViewById(R.id.register_institute);
		layoutSpecialty = (LinearLayout) findViewById(R.id.specialty_layout);
		layoutClass = (LinearLayout) findViewById(R.id.class_layout);
		VirtualData virtualData = new VirtualData();

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
		//选择学院
		instituteCheck();
	}

	private void initViewInstructor() {
		layoutSpecialty.setVisibility(0);
		//选择专业
		specialtyCheck();

	}

	private void initViewTeacher() {

	}

	private void initViewStudent() {

		layoutSpecialty.setVisibility(0);
		layoutClass.setVisibility(0);
		//选择专业
		specialtyCheck();
		//选择班级
		classCheck();

	}
	
	
	
	/**
	 * 选择学院
	 * **/
	
	private void instituteCheck() {
		virtualData = new VirtualData();
		instituteSpinner = (Spinner) findViewById(R.id.register_institute);
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
	 * 选择专业
	 * **/
	private void specialtyCheck() {
		virtualData = new VirtualData();

		specialtySpinner = (Spinner) findViewById(R.id.register_specialty);
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
		virtualData = new VirtualData();
		classSpinner = (Spinner) findViewById(R.id.register_class);
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
		classSpinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						className = classSpinner
								.getItemAtPosition(arg2).toString() + "";
						Log.e("mytag", "className====" + className);

					}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});
		
	}

}
