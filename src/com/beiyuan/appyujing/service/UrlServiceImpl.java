package com.beiyuan.appyujing.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

public class UrlServiceImpl implements UrlService {
	private static final String TAG = "UrlServiceImpl";

	String IP = "192.168.1.102:8080";

	/*
	 * 方法的优化参数封装多少参数都能用
	 */
	@Override
	public JSONObject sentParams2Server(JSONObject obj) {

		String uriAPI = "http://" + IP + "/JWGL_Server_1/LoginServlet";

		// String strResult = "FAIL";

		Log.i("JSON", obj.toString());
		HttpEntity entity = HttpUtils.getHttpEntity(uriAPI, 2, obj);
		JSONObject jo2 = new JSONObject();
		Log.i("JSON", jo2.toString());
		InputStream in = HttpUtils.getInputStream(entity);
		if (in != null) {
			try {
				BufferedReader br = new BufferedReader(
						new InputStreamReader(in));
				StringBuilder sb = new StringBuilder();
				String s = null;
				while ((s = br.readLine()) != null) {
					sb.append(s);
				}
				jo2 = new JSONObject(sb.toString());
				Log.i("JSON", jo2.toString());
				return jo2;

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Log.i("JSON", jo2.toString());
		return jo2;

	}


	/*
	 * 用于发送json数据
	 */
	@Override
	public JSONObject sentParams2RegisterServer(JSONObject obj) {
		
		String uriAPI = "http://" + IP + "/LoginDemo/register.action";

		Log.i("JSON", obj.toString());
		HttpEntity entity = HttpUtils.getHttpEntity(uriAPI, 2, obj);
		JSONObject jo2 = new JSONObject();
		Log.i("JSON", jo2.toString());
		InputStream in = HttpUtils.getInputStream(entity);
		if (in != null) {
			try {
				BufferedReader br = new BufferedReader(
						new InputStreamReader(in));
				StringBuilder sb = new StringBuilder();
				String s = null;
				while ((s = br.readLine()) != null) {
					sb.append(s);
				}
				jo2 = new JSONObject(sb.toString());
				Log.i("JSON", jo2.toString());
				return jo2;

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Log.i("JSON", jo2.toString());
		return jo2;
	}

	@Override
	public JSONObject sentParams2Complete(JSONObject obj) {
		System.out.println("用于完善信息");
		String uriAPI = "http://" + IP + "/JWGL_Server_1/LoginServlet";

		// String strResult = "FAIL";

		Log.i("JSON", obj.toString());
		HttpEntity entity = HttpUtils.getHttpEntity(uriAPI, 2, obj);
		JSONObject jo2 = new JSONObject();
		Log.i("JSON", jo2.toString());
		InputStream in = HttpUtils.getInputStream(entity);
		if (in != null) {
			try {
				BufferedReader br = new BufferedReader(
						new InputStreamReader(in));
				StringBuilder sb = new StringBuilder();
				String s = null;
				while ((s = br.readLine()) != null) {
					sb.append(s);
				}
				jo2 = new JSONObject(sb.toString());
				Log.i("JSON", jo2.toString());
				return jo2;

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Log.i("JSON", jo2.toString());
		return jo2;
	}

	/*
	 * 方法的优化参数封装多少参数都能用
	 */
	@Override
	public JSONArray sentParams2News(JSONObject obj) {

		String uriAPI = "http://" + IP + "/getClientNewsList.html";

		// String strResult = "FAIL";

		Log.i("JSON", obj.toString());
		HttpEntity entity = HttpUtils.getHttpEntity(uriAPI, 2, obj);
		JSONArray arr = new JSONArray();
		InputStream in = HttpUtils.getInputStream(entity);
		if (in != null) {
			try {
				BufferedReader br = new BufferedReader(
						new InputStreamReader(in));
				StringBuilder sb = new StringBuilder();
				String s = null;
				while ((s = br.readLine()) != null) {
					sb.append(s);
				}
				arr = new JSONArray(sb.toString());
				Log.i("JSON", arr.toString());
				return arr;

			} catch (Exception e) {

				e.printStackTrace();
				return arr;
			}
		}
		Log.i("JSON", arr.toString());
		return arr;

	}

	/**
	 * 获取学院、年级 数据
	 */
	@Override
	public List<String> getCollegeList(String key, String jsonString) {
		// TODO Auto-generated method stub
		List<String> listString = new ArrayList<String>();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			// 返回JSON的数组
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				String msg = jsonArray.getString(i);
				listString.add(msg);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listString;
	}

	public String getJsonContent() {

		String uriAPI = "http://" + IP + "/school5/getClientCollege.html";
		HttpEntity entity = HttpUtils.getHttpEntity(uriAPI, 2);
		String jsonString = "";
		InputStream in = HttpUtils.getInputStream(entity);
		if (in != null) {
			try {
				BufferedReader br = new BufferedReader(
						new InputStreamReader(in));
				StringBuilder sb = new StringBuilder();
				String s = null;
				while ((s = br.readLine()) != null) {
					sb.append(s);
				}
				jsonString = sb.toString();
				Log.i("jsonString", jsonString);
				return jsonString;

			} catch (Exception e) {

				e.printStackTrace();
				return jsonString;
			}
		}
		return jsonString;
	}
}
