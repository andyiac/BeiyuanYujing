package com.beiyuan.appyujing.view;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.annotation.SuppressLint;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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

import com.beiyuan.appyujing.R;
import com.beiyuan.appyujing.activity.Login;
import com.beiyuan.appyujing.service.UrlService;
import com.beiyuan.appyujing.service.UrlServiceImpl;
import com.beiyuan.appyujing.tools.Util;
import com.beiyuan.appyujing.view.PullDownView.OnPullDownListener;

public class NewsYuJingPiceFragment1 extends Fragment implements
		OnPullDownListener, OnItemClickListener {
	private PullDownView mPullDownView;
	private ListView mListView;
	Document doc;
	Document next;
	/** Handler What加载数据完毕 **/
	private static final int WHAT_DID_LOAD_DATA = 0;
	/** Handler What更新数据完毕 **/
	private static final int WHAT_DID_REFRESH = 1;
	/** Handler What更多数据完毕 **/
	private static final int WHAT_DID_MORE = 2;

	protected static final String TAG = "NewsYuJingPiceFragment1";

	int page = 1;
	private SimpleAdapter mAdapter;
	View view;
	Handler handler;
	List<Map<String, String>> list = new ArrayList<Map<String, String>>();;
	JSONObject obj;
	JSONArray arr;
	private UrlService urlService = new UrlServiceImpl();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		handler = new Handler() {

			public void handleMessage(Message msg) {

				switch (msg.what) {
				case -2:
					Toast.makeText(NewsYuJingPiceFragment1.this.getActivity(),
							"网络异常", Toast.LENGTH_LONG).show();
					break;
				case -1:
					Toast.makeText(NewsYuJingPiceFragment1.this.getActivity(),
							"网络状态不可用！\n请先设置网络", Toast.LENGTH_LONG).show();
					break;
				case 1:

					mAdapter.notifyDataSetChanged();

					break;
				}
			}
		};

		if (Util.isNetworkAvailable(NewsYuJingPiceFragment1.this.getActivity())) {

			Thread threadSet = new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					Looper.prepare();

					obj = new JSONObject();
					try {
						obj.put("news", 1);
						arr = urlService.sentParams2News(obj);
						Log.i(TAG, arr.toString());
						list.clear();
						for (int i = 0; i < arr.length(); i++) {
							Map<String, String> map = new HashMap<String, String>();
							JSONObject temp = (JSONObject) arr.get(i);
							map.put("Theme", temp.getString("Theme"));
							map.put("href", temp.getString("Url"));
							list.add(map);
							Log.i("JSON", "list1=" + list.toString());
						}
						Message message = new Message();
						message.what = 1;
						handler.sendMessage(message);

					} catch (JSONException e) {
						Message message = new Message();
						message.what = -2;
						handler.sendMessage(message);
						Log.i(TAG, "e=" + e.toString());
					}
				}
			});
			threadSet.start();

		} else {
			Toast.makeText(NewsYuJingPiceFragment1.this.getActivity(),
					"网络状态不可用！\n请先设置网络", Toast.LENGTH_LONG).show();
		}
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.news_yujing_pice_frag_1, null);

		//
		// if
		// (Util.isNetworkAvailable(NewsYuJingPiceFragment1.this.getActivity()))
		// {
		//
		// Thread threadSet = new Thread(new Runnable() {
		//
		// @Override
		// public void run() {
		// // TODO Auto-generated method stub
		// Looper.prepare();
		//
		// obj = new JSONObject();
		// try {
		// // obj.put("news",1 );
		// // arr = urlService.sentParams2News(obj);
		// // Log.i(TAG, arr.toString());
		// list.clear();
		// for (int i = 0; i < arr.length(); i++) {
		// Map<String, String> map = new HashMap<String, String>();
		// JSONObject temp = (JSONObject) arr.get(i);
		// map.put("Theme", temp.getString("Theme"));
		// map.put("href", temp.getString("Url"));
		// list.add(map);
		// Log.i("JSON", "list1=" + list.toString());
		// }
		// Message message = new Message();
		// message.what = 1;
		// handler.sendMessage(message);
		//
		// } catch (JSONException e) {
		// Message message = new Message();
		// message.what = -2;
		// handler.sendMessage(message);
		// Log.i(TAG,"e="+ e.toString());
		// }
		// }
		// });
		// threadSet.start();

		// } else {
		// Toast.makeText(NewsYuJingPiceFragment1.this.getActivity(),
		// "网络状态不可用！\n请先设置网络", Toast.LENGTH_LONG).show();
		// }
		getMyView();

		return view;
	}

	private void getMyView() {

		mPullDownView = (PullDownView) view
				.findViewById(R.id.lv_news_yujing_bfxy_frag1);

		mPullDownView.setOnPullDownListener(this);

		mListView = mPullDownView.getListView();

		mListView.setOnItemClickListener(this);
		mAdapter = new SimpleAdapter(
				NewsYuJingPiceFragment1.this.getActivity(), list,
				android.R.layout.simple_list_item_2, new String[] { "Theme",
						"href" }, new int[] { android.R.id.text1,
						android.R.id.text2 });
		mListView.setAdapter(mAdapter);

		// 设置可以自动获取更多 滑到最后一个自动获取 改成false将禁用自动获取更多
		mPullDownView.enableAutoFetchMore(true, 1);
		// 隐藏 并禁用尾部
		mPullDownView.setHideFooter();
		// 显示并启用自动获取更多
		mPullDownView.setShowFooter();
		// 隐藏并且禁用头部刷新
		mPullDownView.setHideHeader();
		// 显示并且可以使用头部刷新
		mPullDownView.setShowHeader();

	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		HashMap<String, Object> map = (HashMap<String, Object>) parent
				.getItemAtPosition(position);
		// Intent intent = new Intent();
		// intent.setClass(NewsYuJingPiceFragment1.this.getActivity(),
		// NewsWebView.class);
		// intent.putExtra("newsid", map.get("href").toString());
		// startActivity(intent);
		Toast.makeText(this.getActivity(), "啊，你点中我了 " + position,
				Toast.LENGTH_SHORT).show();
	}

	/** 刷新事件接口 这里要注意的是获取更多完 要关闭 刷新的进度条RefreshComplete() **/
	@Override
	public void onRefresh() {

		if (Util.isNetworkAvailable(NewsYuJingPiceFragment1.this.getActivity())) {

			Thread threadRefresh = new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					Looper.prepare();

					obj = new JSONObject();
					try {
						// 隐藏并且禁用头部刷新
						mPullDownView.setHideHeader();
						obj.put("news", 2);
						arr = urlService.sentParams2News(obj);
						Log.i(TAG, "arr2=" + arr.toString());
						list.clear();
						for (int i = 0; i < arr.length(); i++) {
							Map<String, String> map = new HashMap<String, String>();
							JSONObject temp = (JSONObject) arr.get(i);
							map.put("Theme", temp.getString("Theme"));
							map.put("href", temp.getString("Url"));
							list.add(map);
						}
						mPullDownView.RefreshComplete();// 这个事线程安全的 可看源代码
						Log.i(TAG, "list2=" + list.toString());
						Message message = new Message();
						message.what = 1;
						handler.sendMessage(message);

					} catch (JSONException e) {
						mPullDownView.RefreshComplete();// 这个事线程安全的 可看源代码
						Message message = new Message();
						message.what = -2;
						handler.sendMessage(message);
						Log.i(TAG, "e=" + e.toString());
					} finally {
						// 显示并且可以使用头部刷新
						mPullDownView.setShowHeader();
					}
				}
			});
			threadRefresh.start();

		} else {
			mPullDownView.RefreshComplete();
			Toast.makeText(NewsYuJingPiceFragment1.this.getActivity(),
					"网络状态不可用！\n请先设置网络", Toast.LENGTH_LONG).show();
		}
	}

	/** 刷新事件接口 这里要注意的是获取更多完 要关闭 更多的进度条 notifyDidMore() **/
	@Override
	public void onMore() {
		if (Util.isNetworkAvailable(NewsYuJingPiceFragment1.this.getActivity())) {

			Thread threadRefresh = new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					Looper.prepare();

					obj = new JSONObject();
					try {
						obj.put("news", 3);
						arr = urlService.sentParams2News(obj);
						Log.i(TAG, "arr2=" + arr.toString());
						for (int i = 0; i < arr.length(); i++) {
							Map<String, String> map = new HashMap<String, String>();
							JSONObject temp = (JSONObject) arr.get(i);
							map.put("Theme", temp.getString("Theme"));
							map.put("href", temp.getString("Url"));
							list.add(map);
						}
						mPullDownView.notifyDidMore();
						Log.i(TAG, "list2=" + list.toString());
						Message message = new Message();
						message.what = 1;
						handler.sendMessage(message);

					} catch (JSONException e) {
						mPullDownView.notifyDidMore();
						Message message = new Message();
						message.what = -2;
						handler.sendMessage(message);
						Log.i(TAG, "e=" + e.toString());
					}
				}
			});
			threadRefresh.start();

		} else {
			mPullDownView.notifyDidMore();
			Toast.makeText(NewsYuJingPiceFragment1.this.getActivity(),
					"网络状态不可用！\n请先设置网络", Toast.LENGTH_LONG).show();
		}
	}

}
