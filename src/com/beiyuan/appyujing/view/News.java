package com.beiyuan.appyujing.view;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.beiyuan.appyujing.MainActivity;
import com.beiyuan.appyujing.R;
import com.beiyuan.appyujing.view.PullDownView.OnPullDownListener;
import com.beiyuan.appyujing.view.TitleView.OnLeftButtonClickListener;


public class News extends Fragment{
	private TitleView mTitle;
	

	View view;

	List<Map<String, String>> list = new ArrayList<Map<String, String>>();;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment4, container, false);
//		init();
//		getMyView();
		return view;
		
		
	}

	/*
	*顶部菜单栏
	*/
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	

		mTitle = (TitleView) getView().findViewById(R.id.title);
		mTitle.setTitle("新闻");
		mTitle.setLeftButton("返回", new OnLeftButtonClickListener(){

			@Override
			public void onClick(View button) {
				MainActivity.mTabHost.setCurrentTab(0);
				MainActivity.mTabRg.check(R.id.tab_rb_1);
			}
			
		});
//		mTitle.setRightButton("帮忙", new OnRightButtonClickListener() {
//
//			@Override
//			public void onClick(View button) {
//				Toast.makeText(getActivity(), "可以点击", Toast.LENGTH_SHORT).show();
//			}
//		});

	}
}
