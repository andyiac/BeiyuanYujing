package com.beiyuan.appyujing;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.beiyuan.appyujing.view.CornerListView;
import com.beiyuan.appyujing.view.TitleView;
import com.beiyuan.appyujing.view.TitleView.OnLeftButtonClickListener;
import com.beiyuan.appyujing.view.TitleView.OnRightButtonClickListener;

public class PersonalCenter extends Fragment {
	private final int REGISTER = 0;// 注册
	private final int LOGIN = 1;// 登录
	private final int UPDATE = 2;//个人信息查看与修改
	private final int HELP = 3;// 帮助
	private final int ABOUTUS = 4;//
	

	private CornerListView mListView = null;
	ArrayList<HashMap<String, String>> map_list1 = null;
	
	private TitleView mTitle;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View parentView = inflater.inflate(R.layout.fragment5, container, false);
		getDataSource1();
		// getDataSource2();

		SimpleAdapter adapter1 = new SimpleAdapter(getActivity(), map_list1,
				R.layout.simple_list_item, new String[] { "item" },
				new int[] { R.id.item_title });
		mListView = (CornerListView) parentView.findViewById(R.id.list1);
		mListView.setAdapter(adapter1);
		mListView.setOnItemClickListener(new OnItemListSelectedListener());
	
		return parentView;
	}

	public ArrayList<HashMap<String, String>> getDataSource1() {
	
		map_list1 = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map1 = new HashMap<String, String>();
		HashMap<String, String> map2 = new HashMap<String, String>();
		HashMap<String, String> map3 = new HashMap<String, String>();
		HashMap<String, String> map4 = new HashMap<String, String>();
		HashMap<String, String> map5 = new HashMap<String, String>();
	
		map1.put("item", "注册");
		map2.put("item", "登录");
		map3.put("item", "个人信息");
		map4.put("item", "帮助");
		map5.put("item", "关于我们");
	
		map_list1.add(map1);
		map_list1.add(map2);
		map_list1.add(map3);
		map_list1.add(map4);
		map_list1.add(map5);
	
		return map_list1;
	}
	class OnItemListSelectedListener implements OnItemClickListener {

		// @Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Intent intent = new Intent();
			switch (arg2) {
			case REGISTER:
				intent.setClass(getActivity(), Register.class);
				startActivity(intent);
				break;
			case LOGIN:
				intent.setClass(getActivity(), Login.class);
				startActivity(intent);
				break;
			case UPDATE:
				intent.setClass(getActivity(), UpdateInfo.class);
				startActivity(intent);
				break;
			case HELP:
				intent.setClass(getActivity(), Help.class);
				startActivity(intent);
				break;
			case ABOUTUS:
				intent.setClass(getActivity(), AboutUs.class);
				startActivity(intent);
				break;
			}

		}
	}
	
	/*
	*顶部菜单栏
	*/
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	

		mTitle = (TitleView) getView().findViewById(R.id.title);
		mTitle.setTitle("个人中心");
		mTitle.setLeftButton("返回", new OnLeftButtonClickListener(){

			@Override
			public void onClick(View button) {
				MainActivity.mTabHost.setCurrentTab(0);
				MainActivity.mTabRg.check(R.id.tab_rb_1);
			}
			
		});
		mTitle.setRightButton("帮忙", new OnRightButtonClickListener() {

			@Override
			public void onClick(View button) {
				Toast.makeText(getActivity(), "可以点击", Toast.LENGTH_SHORT).show();
			}
		});

	}
}

