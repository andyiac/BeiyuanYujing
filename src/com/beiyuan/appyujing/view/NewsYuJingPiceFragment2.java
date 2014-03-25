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

public class NewsYuJingPiceFragment2 extends Fragment implements
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
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.news_yujing_pice_frag_1, null);
		

		return view;
	}

	private void getMyView() {

		mPullDownView = (PullDownView) view
				.findViewById(R.id.lv_news_yujing_bfxy_frag1);

		mPullDownView.setOnPullDownListener(this);

		mListView = mPullDownView.getListView();

		mListView.setOnItemClickListener(this);
		mAdapter = new SimpleAdapter(
				NewsYuJingPiceFragment2.this.getActivity(), list,
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

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					doc = Jsoup.parse(new URL(
							"http://www.hebeinu.edu.cn/xxxw.asp"), 5000);
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				list.clear();
				Elements es = doc.select("span.newslist a");
				for (Element e : es) {
					Map<String, String> map = new HashMap<String, String>();
					String a = e.getElementsByTag("a").text();
					map.put("title", a);
					// 不显示链接地址
					map.put("href", "http://www.hebeinu.edu.cn/"
							+ e.getElementsByTag("a").attr("href"));
					list.add(map);
				}
				// getMyView();
				/** 关闭 刷新完毕 ***/
				mPullDownView.RefreshComplete();// 这个事线程安全的 可看源代码

				Message msg = mUIHandler.obtainMessage(WHAT_DID_REFRESH);
				msg.obj = "After refresh " + System.currentTimeMillis();
				msg.sendToTarget();
			}
		}).start();

	}

	/** 刷新事件接口 这里要注意的是获取更多完 要关闭 更多的进度条 notifyDidMore() **/
	@Override
	public void onMore() {
		new Thread(new Runnable() {

			@Override
			public void run() {

				// Toast.makeText(NewsYuJingPiceFragment1.this.getActivity(),
				// "加载更多", Toast.LENGTH_SHORT).show();
				// Log.i(TAG, "+++++++++++=======");
				// Elements mores = doc.select("DIV.jt");
				String id = null;
				// for (Element more : mores) {
				// id = "http://www.hebeinu.edu.cn/"
				// + more.getElementsByTag("a").get(1).attr("href");
				// Log.i(TAG, "id=" + id);
				// }
				page++;
				id = "http://www.hebeinu.edu.cn/xxxw.asp?page=" + page;
				// http://www.hebeinu.edu.cn/xxxw.asp?page=2
				Log.i(TAG, "1111111111111");
				Log.i(TAG, "id=" + id);
				// Document next = null;

				// 告诉它获取更多完毕 这个事线程安全的 可看源代码

				mPullDownView.notifyDidMore();
				Message msg = mUIHandler.obtainMessage(WHAT_DID_MORE);
				msg.obj = id;
				msg.sendToTarget();
			}
		}).start();
	}

	@SuppressLint("HandlerLeak")
	private Handler mUIHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case WHAT_DID_LOAD_DATA: {
				// if (msg.obj != null) {
				// List<String> strings = (List<String>) msg.obj;
				// if (!strings.isEmpty()) {
				// // mStrings.addAll(strings);
				// mAdapter.notifyDataSetChanged();
				// }
				// }
				Toast.makeText(NewsYuJingPiceFragment2.this.getActivity(),
						"" + mListView.getAdapter().getCount(),
						Toast.LENGTH_LONG).show();
				// 诉它数据加载完毕;
				break;
			}
			case WHAT_DID_REFRESH: {
				// String body = (String) msg.obj;
				// mStrings.add(0, body);
				// mAdapter.notifyDataSetChanged();

				mAdapter.notifyDataSetChanged();
				// 告诉它更新完毕
				break;
			}

			case WHAT_DID_MORE: {
				// String body = (String) msg.obj;
				// mStrings.add(body);
				// Toast.makeText(NewsYuJingPiceFragment1.this.getActivity(),
				// "加载更多", Toast.LENGTH_SHORT).show();
				// Log.i(TAG, "=====================");
				// Elements mores = doc.select("DIV.jt");
				// for (Element more : mores) {
				// String id = "http://www.hebeinu.edu.cn/"+
				// more.getElementsByTag("a").attr("href");
				// Log.i(TAG, "id="+id);
				// }
				String body = (String) msg.obj;
				// mStrings.add(body);

				try {
					Log.i(TAG, "body=" + body);
					next = Jsoup.parse(new URL(body), 5000);
					Log.i(TAG, "body2=" + body);
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Log.i(TAG, "222222222222222");
				Elements es = next.select("span.newslist a");

				Log.i(TAG, "33333333333333");
				for (Element e : es) {
					Map<String, String> map = new HashMap<String, String>();
					String a = e.getElementsByTag("a").text();
					Log.i(TAG, "a=" + a);
					map.put("title", e.getElementsByTag("a").text());
					// 不显示链接地址
					map.put("href", "http://www.hebeinu.edu.cn/"
							+ e.getElementsByTag("a").attr("href"));
					list.add(map);
				}
				mAdapter.notifyDataSetChanged();

				break;
			}
			}

		}

	};

}
